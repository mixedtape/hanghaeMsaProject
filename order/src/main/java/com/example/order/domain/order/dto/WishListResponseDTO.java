package com.example.order.domain.order.dto;

import com.example.order.domain.order.entity.WishList;
import com.example.order.domain.order.entity.WishListProduct;
import java.util.Map;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class WishListResponseDTO {
    private Long productId;
    private Long quantities;

    public static WishListResponseDTO fromEntity(WishListProduct wishListProduct){
        return new WishListResponseDTO(
                wishListProduct.getProductId(),
                wishListProduct.getQuantities()
        );
    }

}
