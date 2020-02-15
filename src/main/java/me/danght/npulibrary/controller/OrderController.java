package me.danght.npulibrary.controller;

import me.danght.npulibrary.entities.Book;
import me.danght.npulibrary.entities.Order;
import me.danght.npulibrary.entities.User;
import me.danght.npulibrary.repository.BookRepository;
import me.danght.npulibrary.repository.OrderRepository;
import me.danght.npulibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Date;
import java.util.List;

/**
 * 订单控制器
 * @author DangHT
 * @date 12/02/2020
 */
@Controller
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    /**
     * 获取全部订单数据
     * @return 订单列表页面
     */
    @GetMapping("/orders")
    public String getAll(Model model) {
        List<Order> orders = orderRepository.findAll();
        model.addAttribute("orders", orders);
        return "dashboard/orderList";
    }

    /**
     * 通过用户名获取指定用户的全部订单数据
     * @param username USER_NAME
     * @param model
     * @return 我的订单页面
     */
    @GetMapping("/order/{username}")
    public String getOrdersByUsername(@PathVariable("username") String username,
                                      Model model) {
        User user = userRepository.findByName(username);
        List<Order> orders = orderRepository.findByUser(user);
        model.addAttribute("orders", orders);
        return "myOrders";
    }

    /**
     * 提交新订单
     * @param bookId BOOK_ID
     * @param username USER_NAME
     * @param model
     * @return 我的订单页面
     */
    @PostMapping("/order")
    public String addOrder(@RequestParam("bookId") Integer bookId,
                           @RequestParam("username") String username,
                           Model model) {
        Book book = bookRepository.getOne(bookId);
        User user = userRepository.findByName(username);

        book.setStock(book.getStock() - 1);
        bookRepository.save(book);

        Order order = new Order();
        order.setUser(user);
        order.setBook(book);
        // 默认借阅期限30天
        order.setTerm(30);
        Date date = new Date();
        order.setLendDate(date);
        order.setFinished(false);

        orderRepository.save(order);
        return "redirect:/order/" + username;
    }

    /**
     * 还书，完成订单
     * @param id BOOK_ORDER_ID
     * @return 我的订单页面
     */
    @GetMapping("/finishOrder/{id}")
    public String finishOrder(@PathVariable("id") Integer id) {
        Order order = orderRepository.getOne(id);
        order.setFinished(true);
        orderRepository.save(order);

        Book book = order.getBook();
        book.setStock(book.getStock() + 1);
        bookRepository.save(book);

        String username = order.getUser().getName();
        return "redirect:/order/" + username;
    }

}
