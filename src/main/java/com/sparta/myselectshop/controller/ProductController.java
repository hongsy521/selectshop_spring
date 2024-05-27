package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.ProductMypriceRequestDto;
import com.sparta.myselectshop.dto.ProductRequestDto;
import com.sparta.myselectshop.dto.ProductResponseDto;
import com.sparta.myselectshop.security.UserDetailsImpl;
import com.sparta.myselectshop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;

    // 관심상품 등록 - 사용자 정보 받음
    @PostMapping("/products")
    public ProductResponseDto createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.createProduct(requestDto,userDetails.getUser());
    }
    // 관심상품 희망 최저가 등록
    @PutMapping("/products/{id}")
    public ProductResponseDto updateProduct(@PathVariable Long id, @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.updateProduct(id,requestDto);
    }
    // 관심상품 조회 - 사용자 정보 받음
    @GetMapping("/products")
    public Page<ProductResponseDto> getProducts(
            @RequestParam("page") int page,
            @RequestParam("size") int size,
            @RequestParam("sortBy") String sortBy,
            @RequestParam("isAsc") boolean isAsc,
            @AuthenticationPrincipal UserDetailsImpl userDetails) {
        return productService.getProducts(userDetails.getUser(), page-1,size,sortBy,isAsc);
    }
    /*// 관리자 게정으로 모든 리스트 조회 가능
    @GetMapping("/admin/products")
    public List<ProductResponseDto> getAllProducts(){
        return productService.getAllProducts();
    }*/
}
