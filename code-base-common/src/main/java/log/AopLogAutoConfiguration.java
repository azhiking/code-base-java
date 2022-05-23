package log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * AopLog自动配置类
 *
 * @author taozhi
 * @date 2021/5/15
 * @since 1.0.0
 */
@ComponentScan
@Configuration
public class AopLogAutoConfiguration {

    private static final Logger log = LoggerFactory.getLogger(AopLogAutoConfiguration.class);

    /**
     * 默认配置异步收集器线程池
     *
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "logCollectorAsyncExecutor")
    public Executor logCollectorAsyncExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(5);
        executor.setMaxPoolSize(10);
        executor.setQueueCapacity(256);
        executor.setThreadNamePrefix("logCollectorAsyncExecutor-");
        executor.setRejectedExecutionHandler((r, exec) -> log.error("LogCollectorAsyncExecutor thread queue is full,activeCount:{},Subsequent collection tasks will be rejected,please check your LogCollector or config your Executor", exec.getActiveCount()));
        executor.initialize();
        return executor;
    }
}
