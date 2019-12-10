package com.restful.validator;

import org.apache.commons.lang3.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 实现ConstraintValidator接口，第一个泛型指定用于标注验证的注解，第二个泛型指定倍标注值得类型
 * 不需要用@component等注解将验证类加入容器。spring会自动将此类加入容器
 */
public class PwdValidator implements ConstraintValidator<PwdCheck,String> {

    //在这个类中可以使用Spring @Autowire注解注入任何需要的对象

    private final static String regExp = "^(?=.*[a-zA-Z0-9].*)(?=.*[a-zA-Z\\W].*)(?=.*[0-9\\W].*).{8,20}$";

    /**
     * 校验器初始化
     * @param pwdCheck
     */
    @Override
    public void initialize(PwdCheck pwdCheck) {

    }

    /**
     * 校验方法
     * @param str 待校验的值
     * @param constraintValidatorContext
     * @return 返回true代表校验成功,false代表校验失败
     */
    @Override
    public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
        if(StringUtils.isBlank(str)){
            return false;
        }
        Pattern p = Pattern.compile(regExp);
        Matcher m = p.matcher(str);
        return m.matches();
    }
} 