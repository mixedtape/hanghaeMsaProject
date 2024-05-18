package com.example.product.book.dto.client;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WishListResponseDTO {
    private Long productId;
    private Long quantities;


}
