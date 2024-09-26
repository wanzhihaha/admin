package com.cellosquare.adminapp;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.converts.PostgreSqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.querys.PostgreSqlQuery;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.baomidou.mybatisplus.generator.keywords.PostgreSqlKeyWordsHandler;

public class MyBatisPlusGenerator {
    public static void main(String[] args) {
        // 数据源配置
        DataSourceConfig.Builder dataSourceConfig = new DataSourceConfig
                .Builder(
                "jdbc:postgresql://localhost:5445/CSMDEV?currentSchema=cellomxadm",
                "MARKETINGDEV",
                "marketingdev123!")
                .dbQuery(new PostgreSqlQuery())
                .schema("cellomxadm")
                .typeConvert(new PostgreSqlTypeConvert())
                .keyWordsHandler(new PostgreSqlKeyWordsHandler());

        FastAutoGenerator.create(dataSourceConfig)
                .globalConfig(builder -> {
                    builder.author("walker") //设置作者
                            .commentDate("YYYY-MM-DD HH:mm:ss")//注释日期
                            .outputDir("C:\\Users\\wanzhi.liu\\dev"); //指定输出目录

                })
                .packageConfig(builder -> {
                    builder.parent("com.cellosquare.adminapp.admin.videoUploadHistory"); // 设置父包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude("mk_mv_video_upload_history") // 设置需要生成的表名
                            .addTablePrefix("mk_"); // 设置过滤表前缀
                    builder.entityBuilder().enableLombok();//开启 lombok 模型
                    builder.entityBuilder().enableTableFieldAnnotation();//开启生成实体时生成字段注解
                    builder.controllerBuilder().enableRestStyle();//开启生成@RestController 控制器

                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}
