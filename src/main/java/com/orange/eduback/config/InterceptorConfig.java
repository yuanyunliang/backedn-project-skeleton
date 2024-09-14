package com.orange.eduback.config;

import com.orange.eduback.interceptor.AuthAndTokenInterceptor;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Configuration
public class InterceptorConfig implements WebMvcConfigurer {

    @Resource
    private AuthAndTokenInterceptor authAndTokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authAndTokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/auth/**");
                //.excludePathPatterns("/auth/login")
                //.excludePathPatterns("/auth/register");
    }
    /*注册多个拦截器,只要是实现了HandlerInterceptor接口的类都可以注册为拦截器，这里是注册了一个数组，然后遍历注册，通过@Autowire注解注入所有
    实现了HandlerInterceptor接口的类，然后遍历注册
    @Override
    public void addInterceptors(@Autowired HandlerInterceptor[] interceptors, InterceptorRegistry registry) {
        for (HandlerInterceptor interceptor : interceptors) {
            registry.addInterceptor(interceptor);
        }
    }
     */

}
