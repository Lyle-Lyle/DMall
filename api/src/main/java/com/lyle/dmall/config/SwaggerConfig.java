package com.lyle.dmall.config;


import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.servlet.mvc.method.RequestMappingInfoHandlerMapping;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.spring.web.plugins.WebMvcRequestHandlerProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;


@Configuration
@EnableSwagger2
public class SwaggerConfig {

    /*swagger会帮助我们生成接口文档
     * 1：配置生成的文档信息
     * 2: 配置生成规则*/

    /*Docket封装接口文档信息*/
    @Bean
    public Docket getDocket(){

        //创建封面信息对象
        ApiInfoBuilder apiInfoBuilder = new ApiInfoBuilder();
        apiInfoBuilder.title("《DMall》后端接口说明")
                .description("此文档详细说明了锋迷商城项目后端接口规范....")
                .version("v 2.0.1")
                .contact( new Contact("Lyle","www.Lye-Lyle.github.io","XiongjinYue.lyle@gmail.com") );
        ApiInfo apiInfo =  apiInfoBuilder.build();

        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo) //指定生成的文档中的封面信息：文档标题、版本、作者
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lyle.dmall.controller"))
//                这里可以指定哪些请求路径
                .paths(PathSelectors.any())
                .build();

        return docket;
    }

}