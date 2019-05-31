package com.ebook.springboot.dao;

import java.util.List;
import com.ebook.springboot.ejb.Book;

/**
 * 书本的接口层
 *
 * @author lz
 * @date 2019-5-6
 */
public interface BookDAO{
    /**
     * 返回特定类别的书
     *
     * @param type type of book
     * @return bookList of selected type
     */
    List<Book> findBooksByTypeLike(String type);

    /**
     * 根据id来查找特定书目
     *
     * @param id id of book
     * @return book with selected id
     */
    Book findBookById(int id);

    /**
     * 获取库里的所有书本的信息
     * @return 所有书本信息
     */
    List<Book> findAll();

    /**
     * 删除id对应的书本
     * @param id    待删除的id
     */
    void deleteById(int id);

    /**
     * 将一本书的信息存入数据库，用于添加或修改
     * @param book  待存储的书
     */
    void save(Book book);

}
