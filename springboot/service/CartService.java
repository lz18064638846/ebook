package com.ebook.springboot.service;

import javax.servlet.http.HttpServletRequest;
import com.ebook.springboot.pojo.Cart;

/**
 * 购物车相关的服务的后端接口
 * @author lz
 * @date 2019-5-17
 */
public interface CartService {
    /**
     * 获取用户当前的购物车
     * @param request  前端发送的获取请求
     * @return         该用户的购物车
     */
    Cart getCart(HttpServletRequest request);

    /**
     * 移除购物车的谋一本书
     * @param bookID    移除的书的id
     * @param request   前端发送的移除请求
     * @return          移除后的购物车仍有多少项
     */
    int removeItem(int bookID,HttpServletRequest request);

    /**
     * 添加一本书进入购物车
     * @param request   前端发送的添加请求
     * @param bookID    添加的书的id
     * @return          添加后购物车有多少项
     */
    int addItem(HttpServletRequest request,int bookID);
}
