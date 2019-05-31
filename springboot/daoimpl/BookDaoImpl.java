package com.ebook.springboot.daoimpl;

import java.util.List;
import com.ebook.springboot.dao.BookDAO;
import com.ebook.springboot.ejb.Book;
import com.ebook.springboot.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * 书本实体持久访问的实现层
 *
 * @author lz
 * @date 2019-5-20
 */
@Repository
public class BookDaoImpl implements BookDAO {
    @Autowired
    BookRepository bookRepository;

    @Override
    public List<Book> findBooksByTypeLike(String type){
        return bookRepository.findBookByTypeLike(type);
    }

    @Override
    public Book findBookById(int id){
        return bookRepository.findBookById(id);
    }

    @Override
    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @Override
    public void deleteById(int id){
        bookRepository.deleteById(id);
    }

    @Override
    public void save(Book book){
        bookRepository.save(book);
    }
}
