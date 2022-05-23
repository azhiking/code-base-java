package log;

import log.collector.LogCollectorExecutor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import utils.StringUtil;

import javax.annotation.Resource;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * AopLog切面处理器
 *
 * @author taozhi
 * @date 2021/5/15
 * @since 1.0.0
 */
@Component
public class AopLogProcessor {

    private final Logger log = LoggerFactory.getLogger(AopLogProcessor.class);

    @Resource
    private LogCollectorExecutor logCollectorExecutor;

    public Object proceed(LogData data, ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        AopLog aopLog = signature.getMethod().getAnnotation(AopLog.class);
        if (aopLog == null) {
            aopLog = point.getTarget().getClass().getAnnotation(AopLog.class);
        }
        if (aopLog != null) {
            return proceed(aopLog, data, point);
        }
        return point.proceed();
    }

    private Object proceed(AopLog aopLog, LogData data, ProceedingJoinPoint point) throws Throwable {
        log.info("开始处理");
        Object result = null;
        boolean success = false;
        try {
            result = point.proceed();
            success = true;
            return result;
        } catch (Throwable throwable) {
            if (aopLog.stackTraceOnError()) {
                try (StringWriter sw = new StringWriter(); PrintWriter writer = new PrintWriter(sw, true)) {
                    throwable.printStackTrace(writer);
                }
            }
            throw throwable;
        } finally {
            if (!aopLog.logOnError() || (aopLog.logOnError() && !data.isSuccess())) {
                MethodSignature signature = (MethodSignature) point.getSignature();
                data.setCostTime(System.currentTimeMillis() - data.getLogTime());
                data.setType(aopLog.tag());
                data.setMethod(signature.getDeclaringTypeName() + "#" + signature.getName());
                if (aopLog.args()) {
                    data.setArgs(StringUtil.getKeyValue(signature.getParameterNames(), point.getArgs()));
                }
                if (aopLog.result()) {
                    data.setResult(result);
                }
                data.setSuccess(success);
                if (aopLog.async()) {
                    logCollectorExecutor.asyncExecute(aopLog.collector(), LogData.getCurrent());
                } else {
                    logCollectorExecutor.execute(aopLog.collector(), LogData.getCurrent());
                }
            }
        }
    }
}
