package com.ebook.springboot.pojo;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * 用于呈现统计数据的辅助类
 * @author lz
 * @date 2019-5-17
 */
public class CountItem {
    private String bookName;

    private String author;

    private String bookType;

    private int number;

    private Timestamp orderTime;

    private int userID;

    private String userName;

    private double userCost;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Timestamp getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Timestamp orderTime) {
        this.orderTime = orderTime;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getUserCost() {
        return userCost;
    }

    public void setUserCost(double userCost) {
        this.userCost = userCost;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CountItem countItem = (CountItem) o;
        return number == countItem.number &&
                userID == countItem.userID &&
                Double.compare(countItem.userCost, userCost) == 0 &&
                Objects.equals(bookName, countItem.bookName) &&
                Objects.equals(author, countItem.author) &&
                Objects.equals(bookType, countItem.bookType) &&
                Objects.equals(orderTime, countItem.orderTime) &&
                Objects.equals(userName, countItem.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookName, author, bookType, number, orderTime, userID, userName, userCost);
    }

    /**
     * 按购买情况统计时的构造函数
     */
    public CountItem(String bookName,String bookType,int number,Timestamp orderTime){
        this.bookName=bookName;
        this.bookType=bookType;
        this.number=number;
        this.orderTime=orderTime;
    }
    /**
     * 按作者统计时的构造函数
     */
    public CountItem(String author,int number){
        this.author=author;
        this.number=number;
    }

    /**
     * 按类型统计时的构造函数
     */
    public CountItem(String bookType){
        this.bookType=bookType;
    }

    /**
     * 统计用户消费情况时的构造函数
     */
    public CountItem(String userName,int userID,int number,double userCost){
        this.userName=userName;
        this.userID=userID;
        this.number=number;
        this.userCost=userCost;
    }
}
