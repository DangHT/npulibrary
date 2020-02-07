package me.danght.npulibrary.controller;

import me.danght.npulibrary.entities.User;
import me.danght.npulibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * 注册控制器
 * @author DangHT
 * @date 06/02/2020
 */
@Controller
public class SignupController {

    @Autowired
    UserRepository userRepository;

    /**
     * 用户注册
     * @param username USER_NAME
     * @param email USER_EMAIL
     * @param password USER_PASSWORD
     * @param isAdmin IS_ADMIN
     * @param map
     * @param session
     * @return
     */
    @PostMapping("/signup")
    public String signup(@RequestParam("username") String username,
                        @RequestParam("email") String email,
                        @RequestParam("password") String password,
                        @RequestParam("isAdmin") boolean isAdmin,
                        Map<String, Object> map,
                        HttpSession session) {
        User user = userRepository.findByEmail(email);
        if (user != null) {
            map.put("msg", "邮箱已被注册!");
            return "signup";
        }
        user = new User();
        user.setEmail(email);
        user.setName(username);
        user.setPassword(password);
        user.setAdmin(isAdmin);

        userRepository.save(user);

        if (isAdmin) {
            session.setAttribute("loginUser", user.getName());
            return "redirect:/admin";
        }
        return "books";
    }

}
