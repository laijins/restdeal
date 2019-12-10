package com.restful.handler;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @ClassName InterceptorDemo
 * @Description TODO
 * @Author ljs
 * @Date 2019/12/4 10:10
 **/
public class InterceptorDemo2 implements HandlerInterceptor {

    private Logger logger = LoggerFactory.getLogger(InterceptorDemo2.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        StringBuffer requestURL = request.getRequestURL();
        String url = requestURL.toString();

        logger.info("前置拦截器2 preHandle： 请求的uri为={}",url);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        logger.info("拦截器2 postHandle： ");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        logger.info("拦截器2 postHandle： ");
    }
}
