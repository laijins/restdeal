package com.restful.consts;

import lombok.Data;

/**
 * @ClassName Result
 * @Description TODO
 * @Author ljs
 * @Date 2019/12/2 16:36
 **/
@Data
public class Result<T> {

    private Integer code = 0;

    private String msg = "";

    private T resultBody= null;

    public Result() {
    }

    public Result(T resultBody) {
        this.resultBody = resultBody;
    }
    public Result(Integer code,String msg,T resultBody) {
        this.code = code;
        this.msg = msg;
        this.resultBody = resultBody;
    }

    public Result(Integer code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
