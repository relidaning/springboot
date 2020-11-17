package com.lidaning.springboot.user;

import com.lidaning.springboot.common.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@RestController
public class UserController {

    Logger log= LoggerFactory.getLogger(getClass());
    @Autowired
    JdbcTemplate jdbcTemplate;
    @PostMapping("/user/login")
    public CommonResult login(@RequestParam("username") String username, @RequestParam("password") String password,
                              HttpServletRequest request){
        User user=null;
        List users=jdbcTemplate.query(" select * from user where username = '"+username+"'  "+
                " and password = '"+password+"' ", new RowMapper<User>(){

            @Override
            public User mapRow(ResultSet resultSet, int i) throws SQLException {
                User user=new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setPassword(resultSet.getString("password"));
                return user;
            }
        });
        if(users!=null&&users.size()>0){
            user= (User) users.get(0);
            HttpSession session = request.getSession();
            session.setAttribute("user", user);
            log.info("当前登录用户为："+user.getUsername());
            return new CommonResult(200, "success", user);
        }else{
            return new CommonResult(500, "user not matched", null);
        }

    }
}
