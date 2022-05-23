package log.collector;

/**
 * 收集器接口
 *
 * @author taozhi
 * @date 2021/5/15
 * @since 1.0.0
 */
@FunctionalInterface
public interface Collector<T> {

    /**
     * 数据数据对象
     *
     * @param data 数据对象
     */
    void collect(T data);
}
