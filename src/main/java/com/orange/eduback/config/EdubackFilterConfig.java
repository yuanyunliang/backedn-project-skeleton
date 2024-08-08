package com.orange.eduback.config;

import com.orange.eduback.filter.EdubackFilter;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//注册过滤器
@Configuration
public class EdubackFilterConfig {

    @Bean
    public FilterRegistrationBean<Filter> filterRegistrationBean() {
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new EdubackFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setName("EdubackFilter");
        filterRegistrationBean.setOrder(1);
        return filterRegistrationBean;
    }
}
