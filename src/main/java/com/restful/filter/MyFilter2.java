package com.restful.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import java.io.IOException;

/**
 * @ClassName MyFilter
 * @Description TODO
 * @Author ljs
 * @Date 2019/12/4 9:33
 **/
public class MyFilter2 implements Filter {
    private Logger logger = LoggerFactory.getLogger(MyFilter2.class);
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        logger.info("request is filter by myfilter2");
    }

    @Override
    public void destroy() {
       logger.info("过滤器开始销毁2");
    }
}
