package com.example.product.book.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BookPriceRequestDTO {

    private Long productId;
    private Long quantities;

}
