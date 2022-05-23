package log.collector;

import log.LogData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 日志收集器 保存到日志文件中
 *
 * @author taozhi
 * @date 2021/5/15
 * @since 1.0.0
 */
@Component
public class FileLogCollector implements LogCollector {

    private final Logger log = LoggerFactory.getLogger(FileLogCollector.class);

    @Override
    public void collect(LogData data) {
        log.info(data.toString());
    }
}
