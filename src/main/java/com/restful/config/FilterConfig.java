package com.restful.config;

import com.restful.filter.MyFilter;
import com.restful.filter.MyFilter2;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName FilterConfig
 * @Description TODO
 * @Author ljs
 * @Date 2019/12/4 9:31
 **/
@Configuration
public class FilterConfig {

    //会按照order值的大小，从小到大的顺序来依次过滤

    /**
     * 配置过滤器
     * @return
     */
//    @Bean
//    public FilterRegistrationBean myFilterRegistration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//      //  registration.setFilter(new MyFilter());
//        //registration.addUrlPatterns("/demo/ok");
//        registration.addInitParameter("paramName", "paramValue");
//        registration.setName("myFilter");
//        registration.setOrder(1);
//        return registration;
//    }
    /**
     * 配置过滤器
     * @return
     */
//    @Bean
//    public FilterRegistrationBean myFilter2Registration() {
//        FilterRegistrationBean registration = new FilterRegistrationBean();
//        registration.setFilter(new MyFilter2());
//        registration.addUrlPatterns("/demo/err");
//        registration.addInitParameter("paramName", "paramValue");
//        registration.setName("myFilter2");
//        registration.setOrder(1);
//        return registration;
//    }
}
