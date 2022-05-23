package com.tomhurry.analyze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author taozhi
 * @date 2022/5/23 16:12
 * @since 1.0.0
 */
public class LogLineParser {

    private static final String DEFAULT_PATTERN_EXPRESSION = "\\[([^\\[\\]]*)\\]\\u0020\\[([^\\[\\]]*)\\]\\u0020\\[([^\\[\\]]*)\\]\\u0020([^\\[\\]]*)\\s([^\\[\\]]*)";

    private static Pattern pattern;

    static {
        pattern = Pattern.compile(DEFAULT_PATTERN_EXPRESSION);
    }

    public static LogLine parse(String line) {
        return parse(line, pattern);
    }

    public static LogLine parse(String line, Pattern pattern) {
        Matcher matcher = pattern.matcher(format(line));
        if (matcher.find()) {
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
        return pattern.matcher(line).find();
    }

    public static boolean isMatch(String line) {
        return isMatch(line, pattern);
    }

    public static String format(String line) {
        String[] characters = line.split(" ");
        String result = "";
        for (String str : characters) {
            if (null != str && str.trim().length() > 0) {
                result = result.concat(str).concat(" ");
            }
        }
        return result;
    }

    public static void main(String[] args) {
        String str = "[2022-05-05 17:52:34.074] [main] [] INFO  o.s.c.s.PostProcessorRegistrationDelegate$BeanPostProcessorChecker Bean 'dataSource' of type [com.zaxxer.hikari.HikariDataSource] is not eligible for getting processed by all BeanPostProcessors (for example: not eligible for auto-proxying)";
        LogLine logLine = LogLineParser.parse(str);
        System.out.println(logLine);
    }
}
