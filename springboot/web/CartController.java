package com.ebook.springboot.web;

import javax.servlet.http.HttpServletRequest;
import com.ebook.springboot.pojo.Cart;
import com.ebook.springboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 购物车的控制层
 * @author lz
 * @date 2019-5-6
 */
@RestController
public class CartController {
    @Autowired
    CartService cartService;

    @PostMapping("/Cart/{id}")
    public int addItem(HttpServletRequest request,
                        @PathVariable("id")int bookID){
        return cartService.addItem(request,bookID);
    }

    @DeleteMapping("/Cart/{id}")
    public int removeItem(@PathVariable("id")int bookID,
                          HttpServletRequest request){
        return cartService.removeItem(bookID,request);
    }

    @GetMapping("/Cart")
    public Cart getCart(HttpServletRequest request){
        return cartService.getCart(request);
    }
}
