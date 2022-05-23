package log;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * AopLog注解切面
 *
 * @author taozhi
 * @date 2021/5/15
 * @since 1.0.0
 */
@EnableAspectJAutoProxy(exposeProxy = true)
@Aspect
@Component
public class AopLogAspect {

    @Resource
    private AopLogProcessor aopLogProcessor;

    /**
     * 将会切 被AopLog注解标记的方法
     */
    @Pointcut("@annotation(log.AopLog)")
    public void aopLogPointCut() {
        //ig
    }

    @Around("aopLogPointCut()")
    public Object logAround(ProceedingJoinPoint point) throws Throwable {
        return aopLog(point);
    }

    /**
     * @param point aop 切点对象
     * @return 返回执行结果
     * @throws Throwable Exceptions in AOP should be thrown out and left to the specific business to handle
     */
    private Object aopLog(ProceedingJoinPoint point) throws Throwable {
        try {
            LogData.removeCurrent();
            LogData data = LogData.getCurrent();
            return aopLogProcessor.proceed(data, point);
        } finally {
            LogData.removeCurrent();
        }
    }
}
