package com.ebook.springboot.service;

import java.sql.Timestamp;
import java.util.List;
import com.ebook.springboot.ejb.User;
import com.ebook.springboot.pojo.CountItem;

/**
 * 用户与管理员进行统计的业务层接口
 *
 * @author lz
 * @date 2019-5-13
 */
public interface CountService {
    /**
     * 统计用户一段时间内购买情况
     *
     * @param start 统计开始日期
     * @param end   统计终止日期
     * @param user  发出请求的用户
     * @return 一个统计类型的bean，用于前端呈现
     */
    List<CountItem> getCountByBuy(Timestamp start, Timestamp end, User user);

    /**
     * 统计用户一段时间购买某作者书籍的情况
     *
     * @param start 统计开始日期
     * @param end   统计结束日期
     * @param user  发出请求的用户
     * @return  一个统计类型的bean，用于前端呈现
     */
    List<CountItem> getCountByAuthor(Timestamp start,Timestamp end,User user);

    /**
     * 统计用户一段时间购买某种书籍的情况
     *
     * @param start 统计开始时间
     * @param end   统计结束时间
     * @param user  发出请求的用户
     * @return  一个统计类型的bean，用于前端呈现
     */
    List<CountItem> getUserCountByType(Timestamp start,Timestamp end,User user);

    /**
     * 统计一段时间内购买某种书籍的情况
     * @param start 起始时间
     * @param end   结束时间
     * @return      一个统计类型的bean，用于前端呈现
     */
    List<CountItem> getAdminCountByType(Timestamp start,Timestamp end);

    /**
     * 统计一段时间内某用户购买书籍的情况
     * @param start 起始时间
     * @param end   结束时间
     * @return      一个统计类型的bean，用于前端呈现
     */
    List<CountItem> getAdminCountByUser(Timestamp start,Timestamp end);
}
