package me.danght.npulibrary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 订单实体类
 * @author DangHT
 * @date 03/02/2020
 */
@Entity
@Table(name = "BOOK_ORDER")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "LEND_DATE")
    @Temporal(TemporalType.TIME)
    private Date lendDate;

    @JoinColumn(name = "USER_ID")
    @ManyToOne
    private User user;

    @JoinColumn(name = "BOOK_ID")
    @ManyToOne
    private Book book;

    private Integer term;

}
