package com.ebook.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ebook.springboot.ejb.OrderItem;

/**
 * 订单细项的实体类访问层
 *
 * @author lz
 * @date 2019-5-20
 */
public interface OrderItemRepository extends JpaRepository<OrderItem,Integer> {
}
