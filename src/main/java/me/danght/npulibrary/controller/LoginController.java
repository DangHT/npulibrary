package me.danght.npulibrary.controller;

import me.danght.npulibrary.entities.User;
import me.danght.npulibrary.repository.BookRepository;
import me.danght.npulibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 登录控制器
 * @author DangHT
 * @date 05/02/2020
 */
@Controller
public class LoginController {

    @Autowired
    UserRepository userRepository;

    @Autowired
    BookRepository bookRepository;

    /**
     * 用户登录
     * @param email USER_EMAIL
     * @param password USER_PASSWORD
     * @param map
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/login")
    public String login(@RequestParam("email") String email,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        Model model,
                        HttpSession session) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            map.put("msg", "用户不存在");
            return "login";
        }
        if (!password.equals(user.getPassword())) {
            map.put("msg", "密码错误");
            return "login";
        }
        session.setAttribute("loginUser", user.getName());
        model.addAttribute("books", bookRepository.findAll());
        if (user.isAdmin()) {
            //登录成功，重定向到admin页面
            return "dashboard/bookList";
        }
        return "books";
    }

}
