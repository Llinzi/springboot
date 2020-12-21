package com.eekj.health.im.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

/**
 * @description: swagger2文档接口配置
 * @author: linzi
 * @create: 2020-06-30 16:53
 **/
@EnableSwagger2WebMvc
@Configuration
public class Swagger2Config {

    /**
     * api信息
     *
     * @param name        标题
     * @param description 描述
     * @param version     版本
     * @return
     */
    private ApiInfo apiInfo(String name, String description, String version) {
        return new ApiInfoBuilder()
                .title(name)
                .description(description)
                .version(version)
                .build();
    }

    @Bean
    public Docket nshApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo("咨询聊天后台 API 文档","im-api","1.0"))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.eekj.health.im.controller"))//扫码包下的接口类
                .paths(PathSelectors.any())
                .build()
                .groupName("咨询聊天后台接口"); //配置多个路径，不加这个会报错

    }

    // http://localhost:9001/swagger-ui.html
    //http://47.95.216.148:9001/swagger-ui.html


}
