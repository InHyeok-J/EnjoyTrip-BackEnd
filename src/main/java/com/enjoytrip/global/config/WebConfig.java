package com.enjoytrip.global.config;


import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.CommonsRequestLoggingFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins("http://localhost:3000", "https://web-tripfy-frontend-f69b2mlhc41lrz.sel4.cloudtype.app")
            .allowedMethods("OPTIONS", "GET", "POST", "PUT", "DELETE", "PATCH")
            .allowCredentials(true);
    }

    @Bean
    public FilterRegistrationBean requestLoggingFilter() {
        CommonsRequestLoggingFilter loggingFilter = new CommonsRequestLoggingFilter();
        loggingFilter.setIncludeClientInfo(true);
        loggingFilter.setIncludeQueryString(true);
        loggingFilter.setIncludePayload(true);
        loggingFilter.setIncludeHeaders(false);
        loggingFilter.setMaxPayloadLength(1024 * 1024);
        FilterRegistrationBean bean = new FilterRegistrationBean(loggingFilter);
        bean.setOrder(Integer.MIN_VALUE);
        return bean;
    }
}