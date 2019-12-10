package com.restful.controller;

import com.restful.utils.HttpContextUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * @ClassName ServiceLogAspect
 * @Description TODO
 * @Author ljs
 * @Date 2019/12/3 14:15
 **/
@Aspect
@Component
@Order(2)
public class ServiceLogAspect {
    //另一种切面方法
    /**本地异常日志记录对象 **/

    private static final Logger logger = LoggerFactory.getLogger(ServiceLogAspect.class);

    @Pointcut("execution(public * com.restful.controller.*.*(..))")
    public void serviceAspect() {
        logger.info("我是一个切入点");
    }

    /**
     * 前置通知 用于拦截记录用户的操作
     *
     * @param joinPoint 切点
     */
    @Before("serviceAspect()")
    public void before(JoinPoint joinPoint) {

        try {
            // 接收到请求，记录请求内容

            HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
            String ipAddress = HttpContextUtils.getIpAddress();

            // 记录下请求内容
            logger.info("URL : " + request.getRequestURL().toString());
            logger.info("HTTP_METHOD : " + request.getMethod());
            logger.info("IP : " + request.getRemoteAddr());
            logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
            logger.info("ip:"+ipAddress);
            logger.info("doBefore enter internal requestID={},method ={},param ={}" , 123,joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()",joinPoint.getArgs());
            //日志存入数据库

        } catch (Exception e) {
            logger.error("doBefore exception");
            logger.error("exceptionMsg={}", e.getMessage());
        }
    }

    /**
     * 后通知（After advice） ：当某连接点退出的时候执行的通知（不论是正常返回还是异常退出）。
     *
     * @param joinPoint
     */
    @After("serviceAspect()")
    public void after(JoinPoint joinPoint) {
        logger.info("after  executed. 无论连接点正常退出还是异常退出都调用");
    }

    /**
     * 后通知（After advice） ：当某连接点退出的时候执行的通知。
     *
     * @param joinPoint
     */
    @AfterReturning(pointcut = "serviceAspect()")
    public void AfterReturnning(JoinPoint joinPoint) {
        logger.info("AfterReturning executed。只有当连接点正常退出时才调用");
        Object[] objs = joinPoint.getArgs();
    }

    /**
     * 异常通知 用于拦截层记录异常日志
     *
     * @param joinPoint
     * @param e
     */
    @AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Throwable e) {

//        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
//        HttpSession session = request.getSession();

//        String ip = request.getRemoteAddr();
        String params = "";
        if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
            for (int i = 0; i < joinPoint.getArgs().length; i++) {
                params += (joinPoint.getArgs()[i]) + "; ";
            }
        }
        try {
            logger.info("doAfterThrowing enter。 只有当连接点异常退出时才调用");
            logger.info("exception class:" + e.getClass().getName());
            logger.info("exception msg:" + e.getMessage());
            logger.info("exception method:" + (joinPoint.getTarget().getClass().getName() + "." + joinPoint.getSignature().getName() + "()"));
//            logger.info("remote ip:" + ip);
            logger.info("method parameters:" + params);
            //日志存入数据库
            logger.info("doAfterThrowing end");
        } catch (Exception ex) {
            logger.error("doAfterThrowing exception");
            logger.error("exception msg={}", ex.getMessage());
        }

        logger.error("method={}, code={}, msg={}, params={}",
                joinPoint.getTarget().getClass().getName() + joinPoint.getSignature().getName(), e.getClass().getName(), e.getMessage(), params);
    }


}
