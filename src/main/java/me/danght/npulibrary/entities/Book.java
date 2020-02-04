package me.danght.npulibrary.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

/**
 * 图书实体类
 * @author DangHT
 * @date 02/02/2020
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "BOOK_TITLE", length = 100)
    private String title;

    @Column(name = "BOOK_AUTHOR", length = 100)
    private String author;

    @Column(name = "BOOK_PRESS", length = 100)
    private String press;

    @Column(name = "PUBLISH_DATE")
    @Temporal(TemporalType.DATE)
    private Date publishDate;

    @Column(name = "BOOK_ISBN", length = 50)
    private String isbn;

    @Column(name = "BOOK_THEME", length = 20)
    private String theme;

    @Column(name = "BOOK_STOCK")
    private Integer stock;

}
