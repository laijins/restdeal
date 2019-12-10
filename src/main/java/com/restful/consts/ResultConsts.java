package com.restful.consts;

/**
 * 接口返回结果，错误提示
 * 参考 https://blog.csdn.net/alisonyu/article/details/82833413
 */
public enum ResultConsts {
    SYSERR(-1,"系统错误"),
    OK(0,"请求成功"),
    ILLGAL_PARAM(40001,"参数无效"),
    ;

    private Integer code;
    private String msg;

    ResultConsts(Integer code, String msg){
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