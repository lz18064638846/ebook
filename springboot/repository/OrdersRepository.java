package com.ebook.springboot.repository;

import java.sql.Timestamp;
import java.util.List;
import com.ebook.springboot.ejb.Orders;
import com.ebook.springboot.ejb.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单的实体类访问层
 *
 * @author lz
 * @date 2019-5-20
 */
public interface OrdersRepository extends JpaRepository<Orders,Integer> {
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
    List<Orders> findByOrderTimeBetweenAndUser(Timestamp start, Timestamp end, User user);

    /**
     * 查找特定时间内的所有订单
     * @param start 起始时间
     * @param end   结束时间
     * @return      该时间段内的所有订单
     */
    List<Orders> findByOrderTimeBetween(Timestamp start,Timestamp end);
}
