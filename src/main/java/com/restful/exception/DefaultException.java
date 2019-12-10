package com.restful.exception;

/**
 * 自定义异常，对应ResultConsts
 */
public class DefaultException extends RuntimeException{
    //异常码
    private Integer code;
    //异常信息
    private String msg;

    public DefaultException(Integer code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode(){
        return code;
    }

    public void setCode(Integer code){
        this.code = code;
    }

    public String getMsg(){
        return msg;
    }

    public void setMsg(String msg){
        this.msg = msg;
    }
} 