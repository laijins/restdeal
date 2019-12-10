package com.restful.validator;

import com.restful.consts.ValidatorConsts;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * 自定义校验注解
 * 参考 https://www.jianshu.com/p/320cee90391f
 */
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.PARAMETER,ElementType.ANNOTATION_TYPE })
@Retention(RetentionPolicy.RUNTIME)
//java标准校验注解，validateBy指定校验的类
@Constraint(validatedBy = PwdValidator.class)
@Documented
public @interface PwdCheck {

    //校验注解中必须实现以下三个属性
    String message() default ValidatorConsts.ILLGAL_PWD;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
} 