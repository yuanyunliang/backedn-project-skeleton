package com.orange.eduback.config;

import com.orange.eduback.interceptor.AuthAndTokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private AuthAndTokenInterceptor authAndTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authAndTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/login")
                .excludePathPatterns("/auth/register");
    }
}
