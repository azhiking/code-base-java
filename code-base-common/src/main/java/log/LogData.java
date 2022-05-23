package log;

import java.util.Date;

/**
 * 自定义日志数据对象
 * 不提供对外构造方法，每个线程仅有一个此对象
 *
 * @author taozhi
 * @date 2021/5/15
 * @since 1.0.0
 */
public class LogData {

    private LogData() {
    }

    /**
     * 线程LogData对象
     */
    private static final ThreadLocal<LogData> LOG_DATA = new ThreadLocal<>();
    /**
     * 线程StringBuilder对象 主要用于追加字段到最终的content
     */
    private static final ThreadLocal<StringBuilder> CONTENT_BUILDER = new ThreadLocal<>();

    /**
     * 线程名
     */
    private String threadName = Thread.currentThread().getName();
    /**
     * 线程Id
     */
    private long threadId = Thread.currentThread().getId();

    /**
     * 操作类型
     */
    private String type;
    /**
     * 操作方法
     */
    private String method;
    /**
     * 参数
     */
    private Object args;
    /**
     * 响应体
     */
    private Object result;
    /**
     * 操作日期(调用日期)
     */
    private Long logTime;
    /**
     * 业务处理耗时
     */
    private long costTime;

    /**
     * 执行状态 成功(true)/异常(false)  默认失败false
     */
    private boolean success = false;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public Object getArgs() {
        return args;
    }

    public void setArgs(Object args) {
        this.args = args;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public Long getLogTime() {
        return logTime;
    }

    public void setLogTime(Long logTime) {
        this.logTime = logTime;
    }

    public long getCostTime() {
        return costTime;
    }

    public void setCostTime(long costTime) {
        this.costTime = costTime;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * 获取当前线程中的操作日志对象
     *
     * @return Gets the LogData in the current thread
     */
    public static LogData getCurrent() {
        if (LOG_DATA.get() == null) {
            LogData logData = new LogData();
            logData.setLogTime(System.currentTimeMillis());
            StringBuilder sb = CONTENT_BUILDER.get();
            if (sb == null) {
                CONTENT_BUILDER.set(new StringBuilder());
            }
            LOG_DATA.set(logData);
        }
        return LOG_DATA.get();
    }

    /**
     * 移除当前线程AopLog日志对象
     */
    public static void removeCurrent() {
        CONTENT_BUILDER.remove();
        LOG_DATA.remove();
    }

    /**
     * 转换时间戳
     *
     * @param logTime
     * @return
     */
    private Date parseDate(Long logTime) {
        return new Date(logTime);
    }

    @Override
    public String toString() {
        return "LogData{" +
                "threadName='" + threadName + '\'' +
                ", threadId=" + threadId +
                ", type='" + type + '\'' +
                ", method='" + method + '\'' +
                ", args=" + args +
                ", result=" + result +
                ", logTime=" + parseDate(logTime) +
                ", costTime=" + costTime +
                ", success=" + success +
                '}';
    }
}
