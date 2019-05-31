package com.ebook.springboot.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.ebook.springboot.ejb.Book;

/**
 * 书的实体访问层
 *
 * @author lz
 * @date 2019-5-20
 */
public interface BookRepository extends JpaRepository<Book,Integer> {
    /**
     * 根据类型查找书目
     * @param type  查找的类型
     * @return      该类型的所有书
     */
    List<Book> findBookByTypeLike(String type);

    /**
     * 根据id来查找特定书目
     *
     * @param id id of book
     * @return book with selected id
     */
    Book findBookById(int id);
}
