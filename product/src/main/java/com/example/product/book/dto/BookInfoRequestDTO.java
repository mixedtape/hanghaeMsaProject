package com.example.product.book.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class BookInfoRequestDTO {

    @NotEmpty(message = "책 제목을 입력해 주세요")
    private String bookName;
}
