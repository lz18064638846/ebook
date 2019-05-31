package com.ebook.springboot.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.ebook.springboot.ejb.User;
import com.ebook.springboot.pojo.Alert;
import com.ebook.springboot.pojo.LoginState;

/**
 * 实现用户注册，登录等功能的业务接口层
 *
 * @author lz
 * @date 2019-5-13
 */
public interface UserService {
    /**
     * 实现用户注册的接口
     * @param userName 用户提交的用户名
     * @param password 用户提交的密码
     * @param Email    用户提交的邮箱地址
     * @param tel      用户提交的手机号码
     * @param request  用户发起的http请求，用于生成session
     * @return         一个用于前端呈现的提示bean
     */
    Alert registry(String userName, String password, String Email, String tel, HttpServletRequest request);

    /**
     * 实现用户登录的接口
     * @param userName  用户登录的用户名
     * @param password  用户登录的密码
     * @param request   用户发起的http请求，用于生成session
     * @return          一个用于前端呈现的提示bean
     */
    Alert login(String userName,String password,HttpServletRequest request);

    /**
     * 前端获取用户登录状态的接口
     * @param request   用户发起的请求
     * @return          用户的登录状态
     */
    LoginState getLoginState(HttpServletRequest request);

    /**
     * 处理前端请求所有用户数据的接口
     * @return  所有用户的数据
     */
    List<User> getAllUser();

    /**
     * 处理管理员禁用用户的请求
     * @param id    请求禁用的用户id
     */
    void banUser(int id);

    /**
     * 处理管理员解禁用户的请求
     * @param id     请求解禁的用户id
     */
    void unBanUser(int id);
}
