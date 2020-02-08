package me.danght.npulibrary.controller;

import me.danght.npulibrary.entities.Book;
import me.danght.npulibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 图书控制器
 * @author DangHT
 * @date 04/02/2020
 */
@Controller
public class BookController {

    @Autowired
    BookRepository bookRepository;

    /**
     * 获取全部图书数据
     * @return 图书数据
     */
    @GetMapping("/books")
    public String getAll(Model model) {
        List<Book> books = bookRepository.findAll();
        model.addAttribute("books", books);
        return "dashboard/bookList";
    }

    /**
     * 跳转到图书修改页面
     * @param id BOOK_ID
     * @param model data model
     * @return 图书修改页
     */
    @GetMapping("/book/{id}")
    public String toEditPage(@PathVariable("id") Integer id,
                             Model model) {
        Book book = bookRepository.getOne(id);
        model.addAttribute("book", book);
        return "dashboard/bookEdit";
    }

    /**
     * 修改图书
     * @param id BOOK_ID
     * @param title BOOK_TITLE
     * @param author BOOK_AUTHOR
     * @param press BOOK_PRESS
     * @param theme BOOK_THEME
     * @param stock BOOK_STOCK
     * @return 图书列表页
     */
    @PutMapping("/book")
    public String editBook(@RequestParam("id") Integer id,
                           @RequestParam("title") String title,
                           @RequestParam("author") String author,
                           @RequestParam("press") String press,
                           @RequestParam("theme") String theme,
                           @RequestParam("stock") Integer stock) {
        Book book = bookRepository.getOne(id);

        book.setTitle(title);
        book.setAuthor(author);
        book.setPress(press);
        book.setTheme(theme);
        book.setStock(stock);

        bookRepository.save(book);

        return "redirect:/books";
    }

    /**
     * 跳转到添加新书页面
     * @return 添加新书页面
     */
    @GetMapping("/book")
    public String toAddPage() {
        return "dashboard/bookAdd";
    }

    /**
     * 添加新书
     * @param book 图书数据
     * @return 图书列表页
     */
    @PostMapping("/book")
    public String addBook(Book book) {
        bookRepository.save(book);
        return "redirect:/books";
    }

    /**
     * 删除图书
     * @param id BOOK_ID
     * @return 图书列表页
     */
    @DeleteMapping("/book/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        bookRepository.deleteById(id);
        return "redirect:/books";
    }

}
