package com.ebook.springboot.web;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import com.ebook.springboot.ejb.Orders;
import com.ebook.springboot.pojo.Alert;
import com.ebook.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 订单请求的控制层
 * @author lz
 * @date 2019-5-6
 */
@RestController
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/Order")
    public Alert produceOrder(HttpServletRequest request){
        return orderService.produceOrder(request);
    }

    @GetMapping("/Order")
    public List<Orders> getUserOrders(HttpServletRequest request){
        return orderService.getUserOrders(request);
    }

    @GetMapping("Order/Detail/{id}")
    public Orders getOrderDetail(@PathVariable("id")int id){
        return orderService.getOrderDetail(id);
    }
}
