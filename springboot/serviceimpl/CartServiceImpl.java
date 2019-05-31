package com.ebook.springboot.serviceimpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import com.ebook.springboot.dao.BookDAO;
import com.ebook.springboot.pojo.Cart;
import com.ebook.springboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * 用于实现购物车请求的业务实现层
 *
 * @author lz
 * @date 2019-5-13
 */
@Service
public class CartServiceImpl implements CartService {
    private BookDAO bookDAO;

    @Autowired
    public CartServiceImpl(BookDAO bookDAO){
        this.bookDAO=bookDAO;
    }

    @Value("${name.cart}")
    String cartName;

    @Override
    public int addItem(HttpServletRequest request,int bookID){
        HttpSession session=request.getSession();
        //若用户未创建购物车，则创建一个购物车给用户的session
        if(session.getAttribute(cartName)==null){
            Cart userCart = new Cart();
            session.setAttribute(cartName,userCart);
        }
        Cart cart=(Cart)session.getAttribute(cartName);
        cart.addItem(bookDAO.findBookById(bookID));
        session.setAttribute(cartName,cart);
        return cart.getItemNumber();
    }

    @Override
    public int removeItem(int bookID,HttpServletRequest request){
        HttpSession session=request.getSession();
        Cart cart=(Cart)session.getAttribute(cartName);
        cart.removeItem(bookDAO.findBookById(bookID));
        session.setAttribute(cartName,cart);
        return cart.getItemNumber();
    }

    @Override
    public Cart getCart(HttpServletRequest request){
        HttpSession session=request.getSession();
        Cart cart=(Cart)session.getAttribute(cartName);
        if(cart==null){
            cart=new Cart();
            session.setAttribute(cartName,cart);
        }
        return cart;
    }
}
