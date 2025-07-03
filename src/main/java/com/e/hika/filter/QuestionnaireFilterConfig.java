//package com.e.hika.filter;
//
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//
//@Configuration
//public class QuestionnaireFilterConfig {
//
//    @Bean
//    public FilterRegistrationBean<LoggingFilter> loggingFilter() {
//        FilterRegistrationBean<LoggingFilter> registrationBean = new FilterRegistrationBean();
//        registrationBean.setFilter(new LoggingFilter());
//        registrationBean.addUrlPatterns("/*");
//        registrationBean.setOrder(Integer.MIN_VALUE);
////        registrationBean.setOrder(Ordered.LOWEST_PRECEDENCE);
//
//        return registrationBean;
//    }
//}
