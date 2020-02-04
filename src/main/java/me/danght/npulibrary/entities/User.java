package me.danght.npulibrary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * 系统用户实体类
 * @author DangHT
 * @date 02/02/2020
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Integer id;

   @Column(name = "USER_NAME", length = 20)
   private String name;

   @Column(name = "USER_EMAIL", length = 100)
   private String email;

   @Column(name = "IS_ADMIN")
   private boolean isAdmin;

}
