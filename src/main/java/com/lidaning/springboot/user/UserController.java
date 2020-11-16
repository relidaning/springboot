package com.lidaning.springboot.user;

import com.lidaning.springboot.common.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RestController
public class UserController {

    Logger log= LoggerFactory.getLogger(getClass());
    @PostMapping("/user/login")
    public CommonResult login(@RequestParam(value="user") User user, HttpServletRequest request){

        HttpSession session = request.getSession();
        session.setAttribute("user", user);
        log.info("当前登录用户为："+user.getUsername());

        return new CommonResult(200, "success", user);
    }
}
