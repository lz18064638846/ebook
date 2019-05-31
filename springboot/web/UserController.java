package com.ebook.springboot.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import com.ebook.springboot.ejb.User;
import com.ebook.springboot.pojo.Alert;
import com.ebook.springboot.pojo.LoginState;
import com.ebook.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 用于实现前端发起的与用户相关的请求的控制层
 *
 * @author lz
 * @date 2019-5-13
 */
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @RequestMapping("/Registry")
    public Alert registry(@RequestParam("userName")String userName,
                          @RequestParam("password")String password,
                          @RequestParam("Email")String Email,
                          @RequestParam("tel")String tel,
                          HttpServletRequest request){
        return userService.registry(userName,password,Email,tel,request);
    }

    @PostMapping("/Login")
    public Alert login(@RequestParam("userName")String userName,
                       @RequestParam("password")String password,
                       HttpServletRequest request){
        return userService.login(userName,password,request);
    }

    @GetMapping("/Login")
    public LoginState getLoginState(HttpServletRequest request){
        return userService.getLoginState(request);
    }

    @DeleteMapping("/Login")
    public void cancelLogin(HttpServletRequest request){
        HttpSession session=request.getSession();
        session.invalidate();
    }

    @GetMapping("/User")
    public List<User> getAllUser(){
        return userService.getAllUser();
    }

    @DeleteMapping("/User/{id}")
    public void banUser(@PathVariable("id")int id){
        userService.banUser(id);
    }

    @PostMapping("/User/{id}")
    public void unBanUser(@PathVariable("id")int id){
        userService.unBanUser(id);
    }

}
