package com.ebook.springboot.daoimpl;

import java.sql.Timestamp;
import java.util.List;
import com.ebook.springboot.dao.OrdersDAO;
import com.ebook.springboot.ejb.Orders;
import com.ebook.springboot.ejb.User;
import com.ebook.springboot.repository.OrdersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 订单实体持久访问层的实现类
 * @author lz
 * @date 2019-5-20
 */
@Repository
public class OrdersDaoImpl implements OrdersDAO {
    @Autowired
    OrdersRepository ordersRepository;

    @Override
    public List<Orders> findByUser(User user){
        return ordersRepository.findByUser(user);
    }

    @Override
    public Orders findByOrderId(int id){
        return ordersRepository.findByOrderId(id);
    }

    @Override
    public List<Orders> findByOrderTimeBetweenAndUser(Timestamp start, Timestamp end, User user){
        return ordersRepository.findByOrderTimeBetweenAndUser(start,end,user);
    }

    @Override
    public List<Orders> findByOrderTimeBetween(Timestamp start,Timestamp end){
        return ordersRepository.findByOrderTimeBetween(start, end);
    }

    @Override
    public List<Orders> findAll(){
        return ordersRepository.findAll();
    }

    @Override
    public void save(Orders orders){
        ordersRepository.save(orders);
    }
}
