package com.ebook.springboot.service;

import java.util.List;
import java.util.Map;
import com.ebook.springboot.ejb.Book;
import com.ebook.springboot.pojo.Alert;

/**
 * 书本的服务接口
 *
 * @author lz
 * @date 2019-5-17
 */
public interface BookService {
    /**
     * 获取所有书本
     *
     * @return 所有书本的信息
     */
    List<Book> getAllBook();

    /**
     * 按照分类来查询书本
     * @param type 特定的分类
     * @return 该分类的所有书本
     */
    List<Book> getBookByType(String type);

    /**
     * 根据id来查询书本
     * @param id  查询的id
     * @return    该id对应书本的信息
     */
    Book getBookByID(int id);

    /**
     * 修改特定id的书本
     * @param id    修改的书本的id
     * @param map   一个需修改属性与修改后值的键值对
     */
    void alterBook(int id,Map<String,String> map);

    /**
     * 删除特定id的书本
     * @param id 待删除书本的id
     **/
    void deleteBook(int id);

    /**
     * 添加一本书
     * @param map   添加的属性
     * @return  一个提示窗口,反馈书本添加情况
     */
    Alert addBook(Map<String,String> map);

}
