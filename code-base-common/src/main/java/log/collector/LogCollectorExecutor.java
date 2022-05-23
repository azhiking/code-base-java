package log.collector;

import log.LogData;
import org.springframework.beans.BeanUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * 日志收集执行器
 *
 * @author taozhi
 * @date 2021/5/15
 * @since 1.0.0
 */
@Component
@EnableAsync
@ComponentScan
public class LogCollectorExecutor {

    private final Map<Class<? extends LogCollector>, LogCollector> collectors = new HashMap<>();

    @Resource(name = "fileLogCollector")
    private LogCollector logCollector;

    @Resource
    private ApplicationContext applicationContext;


    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * AsyncMode log collection 异步模式日志收集
     *
     * @param clzs    日志收集器Class对象
     * @param logData 日志数据
     */
    @Async("logCollectorAsyncExecutor")
    public void asyncExecute(Class<? extends LogCollector>[] clzs, LogData logData) {
        execute(clzs, logData);
    }

    /**
     * 同步模式收集日志
     *
     * @param clzs    日志收集器Class对象
     * @param logData 日志数据
     */
    public void execute(Class<? extends LogCollector>[] clzs, LogData logData) {
        for (Class<? extends LogCollector> clz : clzs) {
            getExecuteLogCollector(clz).collect(logData);
        }
    }

    /**
     * Get the specified log collector 获取指定的日志收集器
     *
     * @param clz 日志收集器Class对象
     * @return 获取指定的日志收集器
     */
    private LogCollector getExecuteLogCollector(Class<? extends LogCollector> clz) {
        if (clz != null) {
            LogCollector c;
            try {
                c = applicationContext.getBean(clz);
            } catch (Exception e) {
                c = collectors.get(clz);
                if (c == null) {
                    c = BeanUtils.instantiateClass(clz);
                    collectors.put(clz, c);
                }
            }
            return c;
        } else {
            return logCollector;
        }
    }
}
