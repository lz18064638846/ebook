package com.ebook.springboot.daoimpl;

import com.ebook.springboot.dao.OrderItemDAO;
import com.ebook.springboot.ejb.OrderItem;
import com.ebook.springboot.repository.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 订单细项持久访问层实现类
 * @author lz
 * @date 2019-5-20
 */
@Repository
public class OrderItemDaoImpl implements OrderItemDAO {
    @Autowired
    OrderItemRepository orderItemRepository;

    @Override
    public void save(OrderItem orderItem){
        orderItemRepository.save(orderItem);
    }
}
