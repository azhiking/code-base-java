package com.tomhurry.analyze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author taozhi
 * @date 2022/5/23 16:12
 * @since 1.0.0
 */
public class LogLineParser {

    // [2022-05-21 13:41:48.211] [grpc-default-executor-190] [] INFO  c.e.c.s.r.r.h.openstack.OpenStackBackupJobHandler jobId_1c64209a3e4b4eda9bf9eaae8b741d2e 接收备份任务返回信息，开始处理
    private static final String DEFAULT_PATTERN_EXPRESSION = "\\[([^\\[\\]]*)\\]" +
            " \\[([^\\[\\]]*)\\]" +
            " \\[([^\\[\\]]*)\\]" +
            " ([^\\[\\]]*)" +
            "  ([^\\[\\]]*)" +
            "  ([^\\[\\]]*)";

    private static Pattern pattern;

    static {
        pattern = Pattern.compile(DEFAULT_PATTERN_EXPRESSION);
    }

    public static LogLine parse(String line) {
        return parse(line, pattern);
    }

    public static LogLine parse(String line, Pattern pattern) {
        Matcher matcher = pattern.matcher(line);
        if (matcher.matches()) {
            LogLine logLine = new LogLine();
            logLine.setTime(matcher.group(1));
            logLine.setThread(matcher.group(2));
            logLine.setJobId(matcher.group(3));
            logLine.setLevel(matcher.group(4));
            logLine.setClassName(matcher.group(5));
            logLine.setContent(line);
            return logLine;
        }
        return null;
    }

    public static boolean isMatch(String line, Pattern pattern) {
        return pattern.matcher(line).matches();
    }

    public static boolean isMatch(String line) {
        return isMatch(line, pattern);
    }

    public static void main(String[] args) {
        String str = "[2022-05-21 12:37:00.041] [Thread-7] [] INFO  c.e.c.d.s.s.StorageHeartbeatScheduleJobHandler 【状态检测】刷新控制器1d4befecd05111ecb234000c29fdbf80下所有块存储状态";

        LogLine logLine = LogLineParser.parse(str);
        System.out.println(logLine);

    }
}
