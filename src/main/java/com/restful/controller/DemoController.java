package com.restful.controller;

import com.restful.exception.DefaultException;
import com.restful.model.UserInfo;
import com.restful.validator.PwdCheck;
import org.jasig.cas.client.util.AbstractCasFilter;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.validation.constraints.Email;

@Validated
@Controller
@RequestMapping(value = "/demo")
public class DemoController {

    @GetMapping(value = "/email")
    public String email(@Email(message = "邮件格式错误") String email) {
        return "ok";
    }

    @GetMapping(value = "/pwd")
    public String pwd(@PwdCheck(message = "密码格式错误") String pwd) {
        return "ok";
    }

    @GetMapping(value = "/err")
    public String err() {
        throw new DefaultException(0, "Error");
    }

    @GetMapping(value = "/error")
    public Integer error() {
        return 0 / 0;
    }

    @RequestMapping("welcome")
    public String welcome() {
        return "demo/welcome";
    }

    @RequestMapping("index")
    public String index() {
        return "demo/index";
    }

    @GetMapping(value = "/ok")
    public String ok() {
        return "demo/ok";
    }

    @GetMapping(value = "/login")
    public void login() {
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest req) {

        HttpSession session = req.getSession();
        if (session != null) {
            System.out.println("999999999999");
            session.removeAttribute(AbstractCasFilter.CONST_CAS_ASSERTION);
            session.invalidate();//触发LogoutListener
        }
        return "redirect:https://auth.fmcc.cldc.test.com/authui/logout?service=http://10.47.181.202:8081/demo/logout/success";
    }

    @RequestMapping("logout/success")
    public String logoutsuccess(HttpSession session) {
        return "demo/logoutsuccess";
    }

    @GetMapping(value = "/getUserInfo")
    public UserInfo getUserInfo() {
        UserInfo userInfo = new UserInfo();
        userInfo.setId("123");
        userInfo.setName("ljs");
        return userInfo;
    }

    @PostMapping(value = "post")
    public Object post(@PwdCheck(message = "密码格式错误") String pwd) {
        return "ok";
    }

    @PostMapping(value = "/userinfo")
    public String userInfo(@Valid UserInfo userInfo, BindingResult result) {
        return "ok";
    }



}
