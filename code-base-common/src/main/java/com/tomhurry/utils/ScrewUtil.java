package com.tomhurry.utils;

import cn.smallbun.screw.core.Configuration;
import cn.smallbun.screw.core.engine.EngineConfig;
import cn.smallbun.screw.core.engine.EngineFileType;
import cn.smallbun.screw.core.engine.EngineTemplateType;
import cn.smallbun.screw.core.execute.DocumentationExecute;
import cn.smallbun.screw.core.process.ProcessConfig;

import javax.sql.DataSource;

/**
 * 数据库表文档生成工具类
 *
 * @author taozhi
 * @date 2022/5/19 12:23
 * @since 1.0.0
 */
public class ScrewUtil {

    /**
     * 默认版本
     */
    private static final String DEFAULT_VERSION = "1.0";
    /**
     * 默认描述
     */
    private static final String DEFAULT_DESCRIPTION = "";

    public static void documentGeneration(DataSource dataSource, String outputPath, String fileName, EngineFileType fileType) {
        documentGeneration(dataSource, outputPath, fileName, fileType, null, DEFAULT_VERSION, DEFAULT_DESCRIPTION);
    }

    public static void documentGeneration(DataSource dataSource, String outputPath, String fileName, EngineFileType fileType, ProcessConfig processConfig, String version, String description) {
        // 生成文件配置
        EngineConfig engineConfig = EngineConfig.builder()
                // 生成文件路径
                .fileOutputDir(outputPath)
                .fileName(fileName)
                // 打开目录
                .openOutputDir(false)
                // 文件类型
                .fileType(fileType)
                // 生成模板实现
                .produceType(EngineTemplateType.freemarker)
                .build();

        // 生成文档配置（包含以下自定义版本号、描述等配置连接）
        Configuration config = Configuration.builder()
                .version(version)
                .description(description)
                .dataSource(dataSource)
                .engineConfig(engineConfig)
                .produceConfig(processConfig)
                .build();

        // 执行生成
        new DocumentationExecute(config).execute();
    }

    public static void main(String[] args) {

//        DataSource dataSource = DataSourceBuilder.create()
//                .driverClassName("com.mysql.cj.jdbc.Driver")
//                .url("jdbc:mysql://127.0.0.1:3306/cdm?useUnicode=true&characterEncoding=UTF-8")
//                .username("root")
//                .password("root123@eCloud")
//                .build();
//
//        String outputPath = "C:\\workspace\\github";
//        String fileName = "cdm";
//        ScrewUtil.documentGeneration(dataSource, outputPath, fileName, EngineFileType.MD);

    }
}
