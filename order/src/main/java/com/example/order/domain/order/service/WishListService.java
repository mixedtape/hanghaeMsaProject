package com.example.order.domain.order.service;

import com.example.order.domain.order.dto.WishListRequestDTO;
import com.example.order.domain.order.dto.WishListResponseDTO;
import com.example.order.domain.order.dto.product.BookPriceRequestDTO;
import com.example.order.domain.order.dto.product.BookPriceResponseDTO;
import com.example.order.domain.order.entity.WishList;
import com.example.order.domain.order.entity.WishListProduct;
import com.example.order.domain.order.repository.WishListProductRepository;
import com.example.order.domain.order.repository.WishListRepository;
import com.example.order.global.exception.order.AlreadyExistWishListException;
import com.example.order.global.exception.order.WishListNotFoundException;
import com.example.user.domain.user.dto.UserInfoResponseDTO;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final WishListRepository wishListRepository;
    private final MemberFeignClient memberFeignClient;
    private final ProductFeignClient productFeignClient;
    private final WishListProductRepository wishListProductRepository;

    @Transactional
    public void createWishlist(Long userId) {
        if (wishListRepository.existsByUserId(userId)) {
            throw new AlreadyExistWishListException();

        }
        UserInfoResponseDTO userInfoResponseDTO = memberFeignClient.getProfile(userId);
        Long l = userInfoResponseDTO.getUserId();
        WishList wishList = WishList.builder()
                .userId(l)
                .build();
        wishListRepository.save(wishList);
    }

    @Transactional
    public void addWishList(Long wishListId, WishListRequestDTO wishListRequestDTO) {

        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(WishListNotFoundException::new);

        WishListProduct wishListProduct = wishListProductRepository.findByProductIdAndWishListId(
                wishListRequestDTO.getProductId(), wishListId);

        if (wishListProduct == null) {
            WishListProduct newWishListProduct = WishListProduct.builder()
                    .productId(wishListRequestDTO.getProductId())
                    .quantities(wishListRequestDTO.getQuantities())
                    .wishList(wishList)
                    .build();
            wishListProductRepository.save(newWishListProduct);
        } else {
            wishListProduct.updateQuantities(wishListRequestDTO.getQuantities());
            wishListProductRepository.save(wishListProduct);
        }
    }

    public List<WishListResponseDTO> getProductQuantities(Long userId) {
        WishList wishList = wishListRepository.findByUserId(userId);
        if (wishList == null) {
            throw new WishListNotFoundException();
        }
        List<WishListProduct> wishListProducts = wishListProductRepository.findByWishListId(
                wishList.getId());
        if (wishListProducts.isEmpty()) {
            throw new WishListNotFoundException();
        }
        return wishListProducts.stream()
                .map(WishListResponseDTO::fromEntity)
                .collect(Collectors.toList());


    }

    @Transactional
    public void deleteWishListProduct(Long wishListProductId) {
        wishListRepository.deleteById(wishListProductId);
    }

    public Long getTotalPrice(Long wishListId) {
        List<WishListProduct> wishListProducts = wishListProductRepository.findByWishListId(
                wishListId);
        Long totalPrice = 0L;
        for (WishListProduct product : wishListProducts) {
            Long productId = product.getProductId();
            Long quantities = product.getQuantities();

            Long bookPriceResponseDTO = productFeignClient.getBooksPrice(productId, quantities);

            totalPrice += bookPriceResponseDTO;
        }

        return totalPrice;

    }

}
