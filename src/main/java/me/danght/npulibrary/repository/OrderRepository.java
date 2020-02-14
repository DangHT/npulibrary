package me.danght.npulibrary.repository;

import me.danght.npulibrary.entities.Order;
import me.danght.npulibrary.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 订单数据映射接口
 * @author DangHT
 * @date 04/02/2020
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

    /**
     * 查找指定用户的所有订单
     * @param user USER
     * @return 订单数据
     */
    List<Order> findByUser(User user);

}
