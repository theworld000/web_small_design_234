package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MyConfig implements WebMvcConfigurer {
    /*
     *addResourceHandler:访问映射路径
     *addResourceLocations:资源绝对路径
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/product/**").addResourceLocations("file:D:/desk/javaweb/codes/spring_vue_codes/vue/src/assets/Images");
    }
}

