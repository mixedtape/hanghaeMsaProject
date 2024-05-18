package com.example.order.domain.order.service;

import com.example.order.domain.order.dto.WishListResponseDTO;
import com.example.order.domain.order.dto.product.BookPriceRequestDTO;
import com.example.order.domain.order.dto.product.BookPriceResponseDTO;
import java.util.List;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "product-service", url = "http://localhost:7070/api/books")
public interface ProductFeignClient {

    @GetMapping("/price")
    Long getBooksPrice(
            @RequestParam("productId") Long productId,
            @RequestParam("quantities") Long quantities
    );
    @PostMapping("/ordered")
    Void productDeduction(@RequestBody List<WishListResponseDTO> productList);

}
