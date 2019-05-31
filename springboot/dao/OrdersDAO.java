package com.ebook.springboot.dao;

import java.sql.Timestamp;
import java.util.List;
import com.ebook.springboot.ejb.Orders;
import com.ebook.springboot.ejb.User;

/**
 * 订单的接口层
 * @author lz
 * @date 2019-5-7
 */
public interface OrdersDAO{
    /**
     * 查找某个用户的所有订单
     * @param user 发出请求的用户
     * @return      该用户的所有订单
     */
    List<Orders> findByUser(User user);

    /**
     * 根据订单id查找特定的订单
     * @param id    查找的id
     * @return      查找的订单
     */
    Orders findByOrderId(int id);

    /**
     * 查找特定时间段内用户的所有订单
     * @param start 起始时间
     * @param end   结束时间
     * @param user  发出请求的用户
     * @return      该时间段内的用户订单
     */
    List<Orders> findByOrderTimeBetweenAndUser(Timestamp start, Timestamp end,User user);

    /**
     * 查找特定时间内的所有订单
     * @param start 起始时间
     * @param end   结束时间
     * @return      该时间段内的所有订单
     */
    List<Orders> findByOrderTimeBetween(Timestamp start,Timestamp end);

    /**
     * 查询数据库所有的订单的信息
     * @return 所有订单的信息
     */
    List<Orders> findAll();

    /**
     * 将订单信息存入数据库
     * @param orders 储存的订单信息类
     */
    void save(Orders orders);
}
