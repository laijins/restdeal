package com.restful.model;

import lombok.Data;
import lombok.ToString;

/**
 * @ClassName UserDetailInfo
 * @Description TODO
 * @Author ljs
 * @Date 2019/12/6 17:16
 **/
@Data
@ToString
public class UserDetailInfo {
    private String switchId;
    private String projectName;
    private String userName;
    private String locale;
    private String userId;
    private String domainId;
    private String projectId;
    private String token;

}
