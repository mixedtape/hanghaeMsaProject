package com.example.order.domain.order.dto.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@AllArgsConstructor
@Builder
public class BookPriceRequestDTO {

    private Long productId;
    private Long quantities;

}
