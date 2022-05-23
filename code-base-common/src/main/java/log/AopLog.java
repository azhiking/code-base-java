package log;

import log.collector.FileLogCollector;
import log.collector.LogCollector;

import java.lang.annotation.*;

/**
 * AopLog注解
 *
 * @author taozhi
 * @date 2021/5/15
 * @since 1.0.0
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface AopLog {

    /**
     * 操作分类
     *
     * @return
     */
    String tag() default "";

    /**
     * 是否记录入参
     *
     * @return
     */
    boolean args() default true;

    /**
     * 是否记录结果
     *
     * @return
     */
    boolean result() default true;

    /**
     * 异步模式
     *
     * @return
     */
    boolean async() default true;

    /**
     * 仅当发生异常时才记录
     *
     * @return
     */
    boolean logOnError() default false;

    /**
     * 当发生异常时,AOP是否追加异常堆栈信息到content
     *
     * @return
     */
    boolean stackTraceOnError() default false;

    /**
     * 指定专门的收集器
     *
     * @return
     */
    Class<? extends LogCollector>[] collector() default FileLogCollector.class;
}
