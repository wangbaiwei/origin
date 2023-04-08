package com.wbw.serviceprice.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.Collections;

/**
 * 自动生成代码工具类
 */
public class MysqlGenerator {

    public static void main(String[] args) {
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/service-price?characterEncoding=utf8&allowPublicKeyRetrieval=true&connectTimeout=1000&socketTimeout=3000&autoReconnect=true&useUnicode=true&useSSL=false&serverTimezone=UTC", "youth", "youth")
                .globalConfig(builder -> {
                    builder.author("王佰伟").fileOverride().outputDir("D:\\Study\\online-taxi-public\\service-price\\src\\main\\java");

                }).packageConfig(builder -> {
                    builder.parent("com.wbw.serviceprice").pathInfo(Collections.singletonMap(OutputFile.mapperXml, "D:\\Study\\online-taxi-public\\service-price\\src\\main\\java\\com\\wbw\\serviceprice\\mapper"));
                }).strategyConfig(builder -> {
                    builder.addInclude("price_rule");
                }).templateEngine(new FreemarkerTemplateEngine())
                .execute();
    }
}
