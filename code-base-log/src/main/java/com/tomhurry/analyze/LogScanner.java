package com.tomhurry.analyze;

import cn.hutool.core.io.FileUtil;

import java.io.*;

/**
 * 日志扫描器
 *
 * @author taozhi
 * @date 2022/5/23 15:00
 * @since 1.0.0
 */
public class LogScanner {


    public LogView scan(String filePath) throws IOException {

        LogView view = new LogView();

        if (FileUtil.exist(filePath)) {

            File file = new File(filePath);
            if (file.canRead()) {
                try (InputStream inputStream = new FileInputStream(file); BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {

                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        System.out.println(line);
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

        String filePath = "C:\\tmp\\dispatcher.2022-05-21.10.log";
        LogScanner scanner = new LogScanner();
        scanner.scan(filePath);

    }
}
