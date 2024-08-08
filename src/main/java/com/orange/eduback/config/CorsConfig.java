package com.orange.eduback.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
//@EnableWebSecurity
public class CorsConfig implements WebMvcConfigurer {
    private static final Logger log = LoggerFactory.getLogger(CorsConfig.class);

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        log.info("*************跨域配置加载CorsConfig addCorsMappings*****************");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*");
    }

}
