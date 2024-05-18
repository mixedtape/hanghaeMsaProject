package com.example.product.book.repository;


import com.example.product.book.entity.Book;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    boolean existsByName(String bookName);

    List<Book> findByAuthor(String author);

    List<Book> findByPublisher(String publisher);

    Optional<Book> findByName(String bookName);

}
