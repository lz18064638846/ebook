package com.ebook.springboot.serviceimpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import com.ebook.springboot.dao.BookDAO;
import com.ebook.springboot.dao.OrderItemDAO;
import com.ebook.springboot.dao.OrdersDAO;
import com.ebook.springboot.ejb.OrderItem;
import com.ebook.springboot.ejb.Orders;
import com.ebook.springboot.ejb.User;
import com.ebook.springboot.pojo.Alert;
import com.ebook.springboot.pojo.Cart;
import com.ebook.springboot.pojo.CartItem;
import com.ebook.springboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 用于处理用户与管理员订单请求的业务实现层
 *
 * @author lz
 * @date 2019-5-13
 */
@Service
public class OrderServiceImpl implements OrderService {
    private OrdersDAO ordersDAO;

    private BookDAO bookDAO;

    private OrderItemDAO orderItemDAO;

    @Autowired
    public OrderServiceImpl(OrdersDAO ordersDAO,BookDAO bookDAO,OrderItemDAO orderItemDAO){
        this.bookDAO=bookDAO;
        this.ordersDAO=ordersDAO;
        this.orderItemDAO=orderItemDAO;
    }

    @Value("${name.user}")
    String userAttribute;

    @Value("${name.cart}")
    String cartAttribute;

    @Value("${root.user}")
    String rootOfUser;

    @Value("${root.admin}")
    String rootOfAdmin;

    @Override
    public Alert produceOrder(HttpServletRequest request){
        Alert alert=new Alert("buy");
        HttpSession session=request.getSession();
        Cart cart=(Cart)session.getAttribute(cartAttribute);
        session.setAttribute(cartAttribute,null);
        if(cart.getItemNumber()==0){
            alert.setAlertType("null");
            return alert;
        }
        for(CartItem item:cart.getItems()){
            if(item.getBook().getStorage()<item.getNumber()){
                alert.setAlertType("notEnough");
                return alert;
            }
        }
        Orders order = new Orders(cart);
        order.setUser((User)session.getAttribute(userAttribute));
        ordersDAO.save(order);
        for(CartItem cartItem:cart.getItems()){
            OrderItem item = new OrderItem(cartItem);
            item.setOrderId(order.getOrderId());
            orderItemDAO.save(item);
        }
        for(CartItem item:cart.getItems()){
            int newStorage=item.getBook().getStorage()-item.getNumber();
            item.getBook().setStorage(newStorage);
            bookDAO.save(item.getBook());
        }
        alert.setAlertType("buy");
        return alert;
    }

    @Override
    public List<Orders> getUserOrders(HttpServletRequest request){
        HttpSession session=request.getSession();
        User user =(User)session.getAttribute(userAttribute);
        if(rootOfAdmin.equals(user.getRoot())){
            return ordersDAO.findAll();
        }
        return ordersDAO.findByUser(user);
    }

    @Override
    public Orders getOrderDetail(int id){
        return ordersDAO.findByOrderId(id);
    }
}
