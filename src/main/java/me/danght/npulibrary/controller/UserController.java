package me.danght.npulibrary.controller;

import me.danght.npulibrary.entities.User;
import me.danght.npulibrary.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author DangHT
 * @date 04/02/2020
 */
@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    /**
     * 获取所有用户信息
     * @param model
     * @return
     */
    @GetMapping("/users")
    public String getAll(Model model) {
        List<User> users = userRepository.findAll();
        model.addAttribute("users", users);
        return "dashboard/userList";
    }

    /**
     * 跳转到用户修改页面
     * @param id USER_ID
     * @param model
     * @return 用户修改页面
     */
    @GetMapping("/editUser/{id}")
    public String toEditPage(@PathVariable("id") Integer id,
                             Model model) {
        User user = userRepository.getOne(id);
        model.addAttribute("user", user);
        return "dashboard/userEdit";
    }

    /**
     * 修改用户
     * @param name USER_NAME
     * @param email USER_EMAIL
     * @param isAdmin USER_IS_ADMIN
     * @param password USER_PASSWORD
     * @return 用户列表页面
     */
    @PutMapping("/user")
    public String editUser(@RequestParam("id") Integer id,
                           @RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("isAdmin") boolean isAdmin,
                           @RequestParam("password") String password) {
        User user = userRepository.getOne(id);

        user.setName(name);
        user.setEmail(email);
        user.setAdmin(isAdmin);
        user.setPassword(password);

        userRepository.save(user);
        return "redirect:/users";
    }

    /**
     * 根据id删除指定用户
     * @param id USER_ID
     * @return 用户列表页面
     */
    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userRepository.deleteById(id);
        return "redirect:/users";
    }

}
