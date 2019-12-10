package com.restful.handler;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.restful.consts.Result;
import com.restful.consts.ResultConsts;
import com.restful.model.UserInfo;
import com.restful.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.reflect.AnnotatedElement;
import java.util.Arrays;

/**
 * 捕获所有Rest接口返回值
 * springboot-Rest接口返回统一格式数据
 * 参考 https://blog.csdn.net/alisonyu/article/details/82833413
 */
//@RestControllerAdvice
//@Configuration
public class ResultHandler implements ResponseBodyAdvice<Object> {

    private Logger log = LoggerFactory.getLogger(ResultHandler.class);

    private ThreadLocal<ObjectMapper> mapperThreadLocal = ThreadLocal.withInitial(ObjectMapper::new);

    private static final Class[] annos = {
            RequestMapping.class,
            GetMapping.class,
            PostMapping.class,
            DeleteMapping.class,
            PutMapping.class
    };

    /**
     * 对所有RestController的接口方法进行拦截
     *
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
//        return true;
        AnnotatedElement element = returnType.getAnnotatedElement();
        return Arrays.stream(annos).anyMatch(anno -> anno.isAnnotation() && element.isAnnotationPresent(anno));
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        log.info("beforeBodyWrite body={}", body);
        Object out;
        ObjectMapper mapper = mapperThreadLocal.get();
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);

        /*根据返回数据类型，封装返回值*/
        if (body instanceof ResultConsts) {
            log.info("beforeBodyWrite ret body={}", body);

            return body;
        } else if (body == null) {
            log.info("beforeBodyWrite ret body={}", new Result<Object>(ResultConsts.OK.getCode(),ResultConsts.OK.getMsg()));

            return new Result<Object>(ResultConsts.OK.getCode(),ResultConsts.OK.getMsg());
        } else {
            log.info("beforeBodyWrite ret body={}", new Result<Object>(ResultConsts.OK.getCode(),ResultConsts.OK.getMsg(), body));
            if(body instanceof String) {//针对String类型做特殊处理
                return JSON.toJSONString(new Result<Object>(ResultConsts.OK.getCode(),ResultConsts.OK.getMsg(), body));
            }
//            if(body instanceof UserInfo){
//                return (UserInfo) body;
//            }
            //接口返回类型转换问题
            return new Result<Object>(ResultConsts.OK.getCode(),ResultConsts.OK.getMsg(), body);
        }
    }

} 