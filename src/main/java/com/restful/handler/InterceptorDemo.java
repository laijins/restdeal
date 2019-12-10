package com.restful.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

/**
 * @ClassName InterceptorDemo
 * @Description TODO
 * @Author ljs
 * @Date 2019/12/4 10:10
 **/
public class InterceptorDemo implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(InterceptorDemo.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer requestURL = request.getRequestURL();
        String url = requestURL.toString();
        String param = request.getParameter("email");
        logger.info("前置拦截器1 preHandle： 请求的uri为={},param={}",url,param);

        //判断邮箱为710921398@qq.com的才放行，否则就拦截
        if(Optional.ofNullable(param).isPresent()){
            if("710921398@qq.com".equals(param)){
                return true;
            }
        }

        return false;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        logger.info("拦截器1 postHandle： ");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        logger.info("拦截器1 postHandle： ");
    }
}
