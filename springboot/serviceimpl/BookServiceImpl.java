package com.ebook.springboot.serviceimpl;

import java.util.List;
import java.util.Map;
import com.ebook.springboot.dao.BookDAO;
import com.ebook.springboot.ejb.Book;
import com.ebook.springboot.pojo.Alert;
import com.ebook.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 书本服务的实现层
 * @author lz
 * @date 2019-5-17
 */
@Service
public class BookServiceImpl implements BookService {
    private BookDAO bookDAO;

    @Autowired
    public BookServiceImpl(BookDAO bookDAO){
        this.bookDAO=bookDAO;
    }

    @Override
    public List<Book> getAllBook(){
        return bookDAO.findAll();
    }

    @Override
    public List<Book> getBookByType(String type){
        return bookDAO.findBooksByTypeLike('%'+type+'%');
    }

    @Override
    public Book getBookByID(int id){
        return bookDAO.findBookById(id);
    }

    @Override
    public void alterBook(int id,Map<String,String> map){
        Book book=bookDAO.findBookById(id);
        for(Map.Entry<String,String> entry:map.entrySet()){
            if(!"".equals(entry.getValue())){
                switch(entry.getKey()){
                    case "name":
                        book.setName(entry.getValue());
                        break;
                    case "author":
                        book.setAuthor(entry.getValue());
                        break;
                    case "isbn":
                        book.setIsbn(entry.getValue());
                        break;
                    case "image":
                        book.setImage(entry.getValue());
                        break;
                    case "storage":
                        book.setStorage(Integer.parseInt(entry.getValue()));
                        break;
                    case "type":
                        book.setType(entry.getValue());
                        break;
                    case"publisher":
                        book.setPublisher(entry.getValue());
                        break;
                    case "price":
                        book.setPrice(Double.parseDouble(entry.getValue()));
                        break;
                    default:break;
                }
            }
        }
        bookDAO.save(book);
    }

    @Override
    public void deleteBook(int id){
        bookDAO.deleteById(id);
    }

    @Override
    public Alert addBook(Map<String,String> map){
        Alert alert=new Alert("添加");
        for(Map.Entry<String,String> entry:map.entrySet()){
            if("".equals(entry.getValue())){
                alert.setTips(entry.getKey());
                alert.setAlertType("null");
                return alert;
            }
        }
        Book book=new Book();
        book.setName(map.get("bookName"));
        book.setAuthor(map.get("author"));
        book.setImage(map.get("image"));
        book.setIsbn(map.get("isbn"));
        book.setPrice(Double.parseDouble(map.get("price")));
        book.setPublisher(map.get("publisher"));
        book.setStorage(Integer.parseInt(map.get("storage")));
        book.setType(map.get("type"));
        bookDAO.save(book);
        alert.setAlertType("success");
        return alert;
    }

}
