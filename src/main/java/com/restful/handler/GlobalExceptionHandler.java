package com.restful.handler;

import com.restful.consts.Result;
import com.restful.consts.ResultConsts;
import com.restful.exception.DefaultException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * 全局异常捕获
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    /**
     * Exception，默认异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = Exception.class)
    public Object ExceptionHandler(Exception ex) {
        return new Result<>(ResultConsts.SYSERR.getCode(),ResultConsts.SYSERR.getMsg());
    }

    /**
     * DefaultException，自定义异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = DefaultException.class)
    public Object ExceptionHandler(DefaultException ex) {
        logger.info("DefaultException={}",ex.getMsg());
        return new Result<>(ex.getCode(), ex.getMsg());
    }

    /**
     * ValidationException，参数校验异常
     *
     * @param ex
     * @return
     */
    @ResponseBody
    @ExceptionHandler(value = ValidationException.class)
    public Object validationExceptionHandler(ValidationException ex) {
        ConstraintViolationException ex1 = (ConstraintViolationException) ex;
        Set<ConstraintViolation<?>> violations = ex1.getConstraintViolations();
        String errorInfo = "";
        for (ConstraintViolation<?> item : violations) {
            errorInfo = errorInfo + " " + item.getMessage();
        }
        return new Result<>(ResultConsts.ILLGAL_PARAM.getCode(), ResultConsts.ILLGAL_PARAM.getMsg() + errorInfo);
    }
} 