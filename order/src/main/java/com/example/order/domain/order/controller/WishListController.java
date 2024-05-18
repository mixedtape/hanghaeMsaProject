package com.example.order.domain.order.controller;

import com.example.order.domain.order.dto.WishListRequestDTO;
import com.example.order.domain.order.dto.WishListResponseDTO;
import com.example.order.domain.order.service.WishListService;
import com.example.order.global.common.CommonResponse;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/wishlist")
public class WishListController {
    private final WishListService wishListService;

    @PostMapping("/{userId}")
    public ResponseEntity<CommonResponse<Void>> createWishList(
            @PathVariable Long userId
    ){
        wishListService.createWishlist(userId);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(CommonResponse.of("회ㅇ",null));
    }
    @GetMapping("/{userId}")
    public ResponseEntity<CommonResponse<List<WishListResponseDTO>>> getWishList(
            @PathVariable Long userId
    ){
        List<WishListResponseDTO> wishListResponseDTO= wishListService.getProductQuantities(userId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("조회완료",wishListResponseDTO));
    }
    @DeleteMapping("/{wishListProductId}")
    public ResponseEntity<CommonResponse<Void>>deleteWishListProduct(
            @PathVariable Long wishListProductId
    ){
        wishListService.deleteWishListProduct(wishListProductId);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("위시리스트 아이템 삭제 완료",null));
    }
    @PatchMapping("/{wishListId}")
    public ResponseEntity<CommonResponse<Void>> addWishList(
            @PathVariable Long wishListId,
            @RequestBody WishListRequestDTO wishListRequestDTO

    ){
        wishListService.addWishList(wishListId,wishListRequestDTO);
        return ResponseEntity.status(HttpStatus.OK)
                .body(CommonResponse.of("위시리스트 아이템 추가 완료",null));
    }

}
