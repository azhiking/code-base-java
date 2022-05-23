package com.tomhurry.analyze;

import cn.hutool.core.io.FileUtil;

import java.io.*;
import java.nio.file.Files;

/**
 * 日志扫描器
 *
 * @author taozhi
 * @date 2022/5/23 15:00
 * @since 1.0.0
 */
public class LogScanner {


    public LogView scan(String filePath) {

        LogView view = new LogView();

        if (FileUtil.exist(filePath)) {

            File file = new File(filePath);
            if (file.canRead()) {
                try (InputStream inputStream = Files.newInputStream(file.toPath()); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

                    String line;
                    LogObject logObject = null;
                    while ((line = bufferedReader.readLine()) != null) {
                        if (LogLineParser.isMatch(line)) {
                            if (logObject != null) {
                                view.put(logObject);
                            }
                            LogLine logLine = LogLineParser.parse(line);
                            logObject = new LogObject(logLine);
                            logObject.put(line);
                        } else {
                            if (logObject != null) {
                                logObject.put(line);
                            }
                        }
                    }
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        } else {
            System.out.println("文件[" + filePath + "]不存在");
        }

        return view;
    }


    public static void main(String[] args) throws IOException {

        String filePath = "C:\\Users\\TaoZhi\\Downloads\\service (1).log";
        LogScanner scanner = new LogScanner();
        LogView logView = scanner.scan(filePath);

        System.out.println(logView.filterByLevel("ERROR"));

        System.out.println(logView.getClassNames());

        System.out.println(logView.getLevels());

    }
}
