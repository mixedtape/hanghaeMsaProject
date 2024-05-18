package com.example.order.domain.order.dto;

import javax.validation.constraints.NotEmpty;
import lombok.Getter;

@Getter
public class WishListRequestDTO {

    @NotEmpty
    private Long productId;
    @NotEmpty
    private Long quantities;

}
