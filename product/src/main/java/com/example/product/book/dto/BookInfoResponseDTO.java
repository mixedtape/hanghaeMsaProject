package com.example.product.book.dto;


import com.example.product.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookInfoResponseDTO {

    private String Name;
    private Long Price;
    private String publisher;
    private String author;
    private String description;
    private Long stock;

    public static BookInfoResponseDTO fromEntity(Book book) {
        return new BookInfoResponseDTO(
                book.getName(),
                book.getPrice(),
                book.getPublisher(),
                book.getAuthor(),
                book.getDescription(),
                book.getStock()
        );

    }
}
