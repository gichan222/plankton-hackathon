package com.example.planktonhackathon.global.config;

import com.example.planktonhackathon.global.auth.filter.ThreadLocalCleanerFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<ThreadLocalCleanerFilter> contextCleanerFilterRegistrationBean() {
        FilterRegistrationBean<ThreadLocalCleanerFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new ThreadLocalCleanerFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}

