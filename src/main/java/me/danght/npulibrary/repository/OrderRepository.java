package me.danght.npulibrary.repository;

import me.danght.npulibrary.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 订单数据映射接口
 * @author DangHT
 * @date 04/02/2020
 */
public interface OrderRepository extends JpaRepository<Order, Integer> {

}
