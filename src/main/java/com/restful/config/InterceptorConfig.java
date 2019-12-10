package com.restful.config;

import com.restful.handler.InterceptorDemo;
import com.restful.handler.InterceptorDemo2;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @ClassName InterceptorConfig
 * @Description TODO
 * @Author ljs
 * @Date 2019/12/4 10:17
 **/
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //注意这里注册了两个拦截器。这两个拦截器的执行顺序和配置顺序有关系，即先配置顺序就在前
        // 和Filter共存时的执行顺序
        /*Filter->Interceptor.preHandle->Handler->Interceptor.postHandle->Interceptor.afterCompletion->Filter */


        registry.addInterceptor(new InterceptorDemo()).addPathPatterns("/demo/email");
//        registry.addInterceptor(new InterceptorDemo2()).addPathPatterns("/demo/ok");

    }
}
