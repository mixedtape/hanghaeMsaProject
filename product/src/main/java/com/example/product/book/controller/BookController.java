package com.example.product.book.controller;


import com.example.product.book.dto.BookInfoRequestDTO;
import com.example.product.book.dto.BookInfoResponseDTO;
import com.example.product.book.dto.BookPriceRequestDTO;
import com.example.product.book.dto.BookPriceResponseDTO;
import com.example.product.book.dto.NewBookRequestDTO;
import com.example.product.book.dto.UpdateBookInfoRequestDTO;
import com.example.product.book.dto.client.WishListResponseDTO;
import com.example.product.book.service.BookService;
import com.example.product.global.common.CommonResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    @PostMapping
    public ResponseEntity<CommonResponse<Void>> addNewBook(
            @RequestBody NewBookRequestDTO newBookRequestDTO
    ) {
        bookService.newBook(newBookRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.of("책 추가 성공", null));
    }

    @PatchMapping("/{bookId}")
    public ResponseEntity<CommonResponse<BookInfoResponseDTO>> updateBookInfo(
            @RequestBody UpdateBookInfoRequestDTO updateBookInfoRequestDTO,
            @PathVariable(name = "bookId") Long bookId
    ) {
        BookInfoResponseDTO bookInfo = bookService.updateBookInfo(updateBookInfoRequestDTO, bookId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("정보 수정 성공", bookInfo));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<CommonResponse<BookInfoResponseDTO>> getBookById(
            @PathVariable(name = "bookId") Long bookId
    ) {
        BookInfoResponseDTO bookInfo = bookService.getBookInfo(bookId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("책 조회 성공", bookInfo));
    }

    @GetMapping("/authors/{authorName}")
    public ResponseEntity<CommonResponse<List<BookInfoResponseDTO>>> getBooksByAuthor(
            @PathVariable(name = "authorName") String name
    ) {
        List<BookInfoResponseDTO> books = bookService.getBookByAuthor(name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("책 조회 성공", books));
    }

    @GetMapping("/price")
    public Long getBooksPrice(
            @RequestParam("productId") Long productId,
            @RequestParam("quantities") Long quantities
    ) {
        BookPriceResponseDTO bookPriceResponseDTO = bookService.getBookPrice(productId, quantities);
        return bookPriceResponseDTO.getBookPrice();
    }

    @GetMapping("/publisher/{publisherName}")
    public ResponseEntity<CommonResponse<List<BookInfoResponseDTO>>> getBooksByPublisher(
            @PathVariable(name = "publisherName") String name
    ) {
        List<BookInfoResponseDTO> books = bookService.getBookByPublisher(name);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("책 조회 성공", books));
    }

    @GetMapping
    public ResponseEntity<CommonResponse<BookInfoResponseDTO>> getBookByName(
            @RequestBody BookInfoRequestDTO bookInfoRequestDTO
    ) {
        BookInfoResponseDTO bookInfoResponseDTO = bookService.getByBookName(
                bookInfoRequestDTO.getBookName());
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("책 조회 성공", bookInfoResponseDTO));
    }

    @PatchMapping("/stock/{bookId}")
    public ResponseEntity<CommonResponse<Void>> updateStock(
            @PathVariable(name = "bookId") Long bookId,
            @RequestParam("quantities") Long quantities
    ) {
        bookService.changeStock(bookId, quantities);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("책 수량 변경 성공", null));


    }

    @PostMapping("/ordered")
    public void productDeduction(
            @RequestBody List<WishListResponseDTO> productList
    ) {
        bookService.subProduct(productList);
    }


}
