package com.ebook.springboot.service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.ebook.springboot.ejb.Orders;
import com.ebook.springboot.pojo.Alert;

/**
 * 订单功能的后端接口层
 * @author lz
 * @date 2019-5-17
 */
public interface OrderService {
    /**
     * 根据订单id来获取订单的详细信息
     * @param id    欲获取详细信息的订单
     * @return      欲获取的订单
     */
    Orders getOrderDetail(int id);

    /**
     * 查找发出请求的用户的所有订单
     * @param request   前端发出的查询订单请求
     * @return          该用户的所有订单
     */
    List<Orders> getUserOrders(HttpServletRequest request);

    /**
     * 用户购买书籍后生成订单
     * @param request   前端发出的购买请求
     * @return          一个辅助类用于前端反馈信息
     */
    Alert produceOrder(HttpServletRequest request);
}
