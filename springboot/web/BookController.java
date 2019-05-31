package com.ebook.springboot.web;

import java.util.List;
import java.util.Map;
import com.ebook.springboot.ejb.Book;
import com.ebook.springboot.pojo.Alert;
import com.ebook.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 书本crud的实控制层
 *
 * @author lz
 * @date 2019-5-5
 */
@RestController
public class BookController {
    @Autowired
    BookService bookService;

    @GetMapping("/Book")
    public List<Book> getAllBook() throws Exception{
        return bookService.getAllBook();
    }

    @GetMapping("/Book/type/{type}")
    public List<Book> getBookByType(@PathVariable("type")String type) throws Exception{
        return bookService.getBookByType(type);
    }

    @GetMapping("/Book/id/{id}")
    public Book getBookByID(@PathVariable("id")int id)throws Exception{
        return bookService.getBookByID(id);
    }

    @PutMapping("/Book/{id}")
    public void alterBook(@PathVariable("id")int id,@RequestBody Map<String,String> map){
        bookService.alterBook(id,map);
    }

    @DeleteMapping("/Book/{id}")
    public void deleteBook(@PathVariable("id")int id) {
        bookService.deleteBook(id);
    }

    @PostMapping("Book")
    public Alert addBook(@RequestBody Map<String,String> map) {
        return bookService.addBook(map);
    }
}
