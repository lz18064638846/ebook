package com.ebook.springboot.repository;

import com.ebook.springboot.ejb.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户基本信息的实体类访问层
 *
 * @author lz
 * @date 2019-5-20
 */
public interface UserRepository extends JpaRepository<User,Integer> {
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
}

