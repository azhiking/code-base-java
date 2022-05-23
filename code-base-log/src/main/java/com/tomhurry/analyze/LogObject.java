package com.tomhurry.analyze;

import java.util.ArrayList;
import java.util.List;

/**
 * @author taozhi
 * @date 2022/5/23 16:10
 * @since 1.0.0
 */
public class LogObject {

    private final LogLine logLine;
    private final List<String> logLines;


    public LogObject(LogLine logLine) {
        this.logLine = logLine;
        this.logLines = new ArrayList<>();
    }

    public void put(String line) {
        logLines.add(line);
    }

    public LogLine getLogLine() {
        return logLine;
    }

    public String toLogLineString() {
        StringBuilder sb = new StringBuilder();
        for (String line : logLines) {
            sb.append(line).append("\r\n");
        }
        return sb.toString();
    }
}
