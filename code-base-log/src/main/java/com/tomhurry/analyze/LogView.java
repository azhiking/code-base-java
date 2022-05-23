package com.tomhurry.analyze;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 日志视图
 *
 * @author taozhi
 * @date 2022/5/23 15:18
 * @since 1.0.0
 */
public class LogView {

    private List<LogObject> logObjectList = new ArrayList<>();

    public void put(LogObject logObject) {
        logObjectList.add(logObject);
    }

    public String filterByThread(Set<String> threadIds) {
        StringBuilder sb = new StringBuilder();
        for (LogObject logObject : logObjectList) {
            if (threadIds.contains(logObject.getLogLine().getThread())) {
                sb.append(logObject.toLogLineString());
            }
        }
        return sb.toString();
    }

    public String filterByJobId(String jobId) {
        StringBuilder sb = new StringBuilder();
        for (LogObject logObject : logObjectList) {
            if (jobId.equals(logObject.getLogLine().getJobId())) {
                sb.append(logObject.toLogLineString());
            }
        }
        return sb.toString();
    }

    public String filterByLevel(String level) {
        StringBuilder sb = new StringBuilder();
        for (LogObject logObject : logObjectList) {
            if (level.equals(logObject.getLogLine().getLevel())) {
                sb.append(logObject.toLogLineString());
            }
        }
        return sb.toString();
    }

    public Set<String> getThreadIds() {
        return logObjectList.stream().map(o -> o.getLogLine().getThread()).collect(Collectors.toSet());
    }

    public Set<String> getJobIds() {
        return logObjectList.stream()
                .filter(o -> null != o.getLogLine().getJobId() && o.getLogLine().getJobId().length() > 0)
                .map(o -> o.getLogLine().getJobId())
                .collect(Collectors.toSet());
    }

    public Set<String> getClassNames() {
        return logObjectList.stream().map(o -> o.getLogLine().getClassName()).collect(Collectors.toSet());
    }

    public Set<String> getLevels() {
        return logObjectList.stream().map(o -> o.getLogLine().getLevel()).collect(Collectors.toSet());
    }

    public Set<String> getThreadIdsByJobId(String jobId) {
        return logObjectList.stream().filter(o -> jobId.equals(o.getLogLine().getJobId())).map(o -> o.getLogLine().getThread()).collect(Collectors.toSet());
    }
}
