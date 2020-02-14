package me.danght.npulibrary.repository;

import me.danght.npulibrary.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 用户数据映射接口
 * @author DangHT
 * @date 04/02/2020
 */
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * 通过 USER_EMAIL 获取用户数据
     * @param email USER_EMAIL
     * @return 用户数据
     */
    User findByEmail(String email);

    /**
     * 通过 USER_NAME 获取用户数据
     * @param name USER_NAME
     * @return 用户数据
     */
    User findByName(String name);

}
