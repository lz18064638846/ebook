package com.ebook.springboot.pojo;

import java.io.Serializable;
import com.ebook.springboot.ejb.Book;

/**
 * 购物车细项
 *
 * @author lz
 * @date 2019-5-6
 */
public class CartItem implements Serializable {
    private static final long serialVersionUID = 1L;

    private Book book;

    private int number;

    public int getNumber() {
        return number;
    }
    public CartItem(Book book){
        this.book = book;
        this.number=1;
    }

    public void addNumber(){
        this.number++;
    }

    public void minusNumber(){
        this.number--;
    }

    public Book getBook(){
        return book;
    }
}
