package com.example.product.book.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class NewBookRequestDTO {

    @NotEmpty(message = "책 제목을 입력해 주세요")
    private String name;
    @NotEmpty(message = "가격을 입력해 주세요")
    private Long price;
    @NotEmpty(message = "출판사를 입력해 주세요")
    private String publisher;
    @NotEmpty(message = "저자를 입력해 주세요")
    private String author;

    private String description;
}
