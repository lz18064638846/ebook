package com.ebook.springboot.dao;

import com.ebook.springboot.ejb.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单细项的接口层
 *
 * @author lz
 * @date 2019-5-7
 */

public interface OrderItemDAO {
    /**
     * 在数据库中存储订单细项
     * @param orderItem 存储的订单细项类
     */
    void save(OrderItem orderItem);
}
