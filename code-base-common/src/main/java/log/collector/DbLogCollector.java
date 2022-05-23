package log.collector;

import log.LogData;
import org.springframework.stereotype.Component;

/**
 * 日志收集器 保存到数据库中
 *
 * @author taozhi
 * @date 2021/5/15
 * @since 1.0.0
 */
@Component
public class DbLogCollector implements LogCollector{
    @Override
    public void collect(LogData data) {

    }
}
