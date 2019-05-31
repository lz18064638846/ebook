package com.ebook.springboot.dao;

import java.util.List;
import com.ebook.springboot.ejb.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户的接口层
 *
 * @author lz
 * @date 2019-5-7
 */
public interface UserDAO{
    /**
     * 根据用户名查找特定用户
     * @param userName 用户名
     * @return user with selected userName
     */
    User findByUserName(String userName);

    /**
     * 根据email地址查找特定用户
     * @param Email 用户邮箱地址
     * @return user with selected email
     */
    User findByEmailAddress(String Email);

    /**
     * 根据手机号码查找特定用户
     * @param tel 用户手机号码
     * @return user with selected telephone number
     */
    User findByUserTel(String tel);

    /**
     * 根据用户id寻找用户
     * @param id  用户id
     * @return    用户
     */
    User findByUserId(int id);

    /**
     * 查询所有注册用户的信息
     * @return  所有注册用户的信息
     */
    List<User> findAll();

    /**
     * 将一个用户的信息存入数据库
     * @param user 存储的用户信息类
     */
    void save(User user);
}
