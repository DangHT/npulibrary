package me.danght.npulibrary.repository;

import me.danght.npulibrary.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * 图书数据映射接口
 * @author DangHT
 * @date 04/02/2020
 */
public interface BookRepository extends JpaRepository<Book, Integer> {

    /**
     * 通过书名查找图书
     * @param title 书名
     * @return 所有符合条件的图书
     */
    List<Book> findBooksByTitle(String title);

    /**
     * 通过学科查找图书
     * @param theme 学科
     * @return 所有符合条件的图书
     */
    List<Book> findBooksByTheme(String theme);

}
