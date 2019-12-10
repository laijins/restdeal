package com.restful.utils;

import com.alibaba.fastjson.JSONObject;
import com.restful.consts.ResultConsts;


/**
 * 封装返回类
 */
public class ResultUtils {

    public static Object getResult(ResultConsts resultConsts){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", resultConsts.getCode());
        jsonObject.put("msg", resultConsts.getMsg());
        return jsonObject;
    }

    public static Object getResult(ResultConsts resultConsts, Object data){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", resultConsts.getCode());
        jsonObject.put("msg", resultConsts.getMsg());
        jsonObject.put("data", data);
        return jsonObject;
    }

    public static Object getResult(Integer code, String msg){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        return jsonObject;
    }

    public static Object getResult(Integer code, String msg, Object data){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", code);
        jsonObject.put("msg", msg);
        jsonObject.put("data", data);
        return jsonObject;
    }
} 