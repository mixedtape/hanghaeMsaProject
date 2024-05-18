package com.example.product.book.service;


import com.example.product.book.dto.BookInfoResponseDTO;
import com.example.product.book.dto.BookPriceResponseDTO;
import com.example.product.book.dto.NewBookRequestDTO;
import com.example.product.book.dto.UpdateBookInfoRequestDTO;
import com.example.product.book.dto.client.WishListResponseDTO;
import com.example.product.book.entity.Book;
import com.example.product.book.repository.BookRepository;
import com.example.product.global.exception.book.AlreadyExistBookException;
import com.example.product.global.exception.book.BookNotFoundException;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    @Transactional
    public void newBook(NewBookRequestDTO newBookRequestDTO) {

        if (bookRepository.existsByName(newBookRequestDTO.getName())) {
            throw new AlreadyExistBookException();
        }

        Book book = Book.builder()
                .bookName(newBookRequestDTO.getName())
                .bookPrice(newBookRequestDTO.getPrice())
                .description(newBookRequestDTO.getDescription())
                .publisher(newBookRequestDTO.getPublisher())
                .author(newBookRequestDTO.getAuthor())
                .build();
        bookRepository.save(book);
    }

    public BookInfoResponseDTO getByBookName(String name) {
        Book book = bookRepository.findByName(name).orElseThrow(BookNotFoundException::new);
        return BookInfoResponseDTO.fromEntity(book);
    }

    public BookPriceResponseDTO getBookPrice(Long bookId, Long quantities) {

        long totalPrice = 0L;
        // 주어진 책 ID 목록을 사용하여 데이터베이스에서 한 번에 책의 가격을 조회
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);

        // 조회된 책들의 가격을 합산
        totalPrice = book.getPrice() * quantities;

        // 총 가격을 BookPriceResponseDTO에 설정하여 반환
        return new BookPriceResponseDTO(totalPrice);
    }

    public BookInfoResponseDTO getBookInfo(Long bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        return BookInfoResponseDTO.fromEntity(book);

    }

    public List<BookInfoResponseDTO> getBookByAuthor(String author) {
        List<Book> books = bookRepository.findByAuthor(author);
        return books.stream().map(BookInfoResponseDTO::fromEntity).toList();
    }

    public List<BookInfoResponseDTO> getBookByPublisher(String publisher) {
        List<Book> books = bookRepository.findByPublisher(publisher);
        return books.stream().map(BookInfoResponseDTO::fromEntity).toList();
    }

    @Transactional
    public BookInfoResponseDTO updateBookInfo(
            UpdateBookInfoRequestDTO updateBookInfoRequestDTO,
            Long bookId
    ) {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        book.updateBookInfo(
                updateBookInfoRequestDTO.getName(),
                updateBookInfoRequestDTO.getPrice(),
                updateBookInfoRequestDTO.getDescription(),
                updateBookInfoRequestDTO.getAuthor(),
                updateBookInfoRequestDTO.getPublisher(),
                updateBookInfoRequestDTO.getStock());
        return BookInfoResponseDTO.fromEntity(book);

    }

    @Transactional
    public void subProduct(
            List<WishListResponseDTO> productList
    ) {
        for (WishListResponseDTO wish : productList) {
            Long productId = wish.getProductId();
            Book book = bookRepository.findById(productId).orElseThrow(BookNotFoundException::new);
            book.subBookStock(wish.getQuantities());
        }
    }

    @Transactional
    public void changeStock(Long bookId, Long quantities) {
        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        book.addBookStock(quantities);
    }


}
