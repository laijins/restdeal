package com.restful.model;

import com.restful.consts.ValidatorConsts;

import javax.validation.constraints.NotBlank;

/**
 * 实体类参数
 */
public class UserInfo {
    @NotBlank(message = ValidatorConsts.NOTBLANK_ID)
    private String id;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
} 