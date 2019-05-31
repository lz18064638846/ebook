package com.ebook.springboot.pojo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.ebook.springboot.ejb.Book;

/**
 * 用于前端呈现购物车的辅助类
 * @author lz
 * @date 2019-5-6
 */
public class Cart implements Serializable {
    private static final long serialVersionUID = 1L;

    private List<CartItem> items;

    private double cost;

    public Cart(){
        items=new ArrayList<>();
        cost=0;
    }

    private CartItem search(Book book){
        for(CartItem item:items){
            if(item.getBook().equals(book)){
                return item;
            }
        }
        return null;
    }

    public void addItem(Book book){
        if(search(book)!=null){
            search(book).addNumber();
        }
        else {
            CartItem newItem = new CartItem(book);
            items.add(newItem);
        }
        cost+=book.getPrice();
    }

    public void removeItem(Book book){
        if(search(book).getNumber()>1){
            search(book).minusNumber();
        }
        else{
            items.remove(search(book));
        }
        cost-=book.getPrice();
    }

    public double getCost(){
        return cost;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public int getItemNumber(){
        return items.size();
    }
}

