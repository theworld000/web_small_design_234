package com.example.demo.config;

import org.springframework.boot.autoconfigure.info.ProjectInfoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

//@Configuration
@EnableSwagger2 //开启swagger
public class SwaggerConfig {

    //配置了Swagger的Docket的bean实例
    @Bean
    public Docket docket(){
        return new Docket(DocumentationType.SWAGGER_2)
                .select()//获取Docket中的选择器,返回apiSelectorBuilder构造选择器 如扫描什么包的注解
                .apis(RequestHandlerSelectors.basePackage("com.example.demo.controller"))//设定扫描那个包（包含子包）
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo2());
    }

    private  ApiInfo apiInfo(){
        Contact contact=new Contact("dyk","https://blog.csdn.net/qq_44866153","1106649325@qq.com");
        return new ApiInfo(
                "dyk的swagger文档",
                "我的 API文档",
                "v1.0",
                "https://blog.csdn.net/qq_44866153",
                contact,
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>()


        );
    }

    private ApiInfo apiInfo2(){

        return new ApiInfoBuilder()
                .title("dyk的swagger文档")
                .description("我的 API文档")
                .version("v1.0")
                .contact(new Contact("dyk","https://blog.csdn.net/qq_44866153","1106649325@qq.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0")
                .build();
    }
}




