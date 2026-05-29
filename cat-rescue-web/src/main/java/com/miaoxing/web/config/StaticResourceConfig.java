package com.miaoxing.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class StaticResourceConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        Path imagePath = Paths.get(System.getProperty("user.dir"), "图片").toAbsolutePath().normalize();
        registry.addResourceHandler("/myImages/**")
            .addResourceLocations(imagePath.toUri().toString());
    }
}
