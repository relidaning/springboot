package com.lidaning.springboot.config;

import com.lidaning.springboot.user.User;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        try{
            HttpSession session = request.getSession();
            User user = (User)session.getAttribute("user");
            if(user!=null){
                return true;
            }
            //登录不成功，直接跳转到登录页面
            response.sendRedirect(request.getContextPath()+"/glance/index");
        }catch (IOException o){
            o.printStackTrace();
        }
        return false;
    }
}
