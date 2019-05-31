package com.ebook.springboot.ejb;

import javax.persistence.*;
import java.util.Objects;
import com.ebook.springboot.pojo.CartItem;

/**
 * 订单细项的实体类
 * @author lz
 * @date 2019-5-6
 */
@Entity
@Table(name = "order_items")
public class OrderItem {

    private int itemId;
    private int orderId;
    private Book book;
    private int orderNumber;

    public OrderItem(CartItem cartItem){
        book=cartItem.getBook();
        orderNumber=cartItem.getNumber();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id",insertable = false, updatable = false)
    public int getItemId(){
        return itemId;
    }

    public void setItemId(int itemId){
        this.itemId=itemId;
    }

    @Column(name = "order_id")
    public int getOrderId(){
        return orderId;
    }

    public void setOrderId(int orderId){
        this.orderId=orderId;
    }

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    @Column(name = "order_number")
    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return orderId == orderItem.orderId &&
                book == orderItem.book;
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderId, book);
    }

    public OrderItem(){}
}
