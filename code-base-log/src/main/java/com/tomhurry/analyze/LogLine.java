package com.tomhurry.analyze;

/**
 * @author taozhi
 * @date 2022/5/23 16:05
 * @since 1.0.0
 */
public class LogLine {

    private String time;

    private String level;

    private String thread;

    private String className;

    private String jobId;

    private String content;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public String getThread() {
        return thread;
    }

    public void setThread(String thread) {
        this.thread = thread;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "LogLine{" +
                "time='" + time + '\'' +
                ", level='" + level + '\'' +
                ", thread='" + thread + '\'' +
                ", className='" + className + '\'' +
                ", jobId='" + jobId + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
