package com.shivendra.mini_erp_backend.controller;

import com.shivendra.mini_erp_backend.dto.CreateProductRequest;
import com.shivendra.mini_erp_backend.dto.ProductResponse;
import com.shivendra.mini_erp_backend.dto.UpdateProductRequest;
import com.shivendra.mini_erp_backend.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ProductResponse> createProduct(
            @Valid @RequestBody CreateProductRequest request) {

        ProductResponse response = productService.createProduct(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getProductById(
            @PathVariable Long id) {

        ProductResponse response =
                productService.getProductById(id);

        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAllProducts(

            @RequestParam(defaultValue = "0") int page,

            @RequestParam(defaultValue = "10") int size,

            @RequestParam(defaultValue = "id") String sortBy,

            @RequestParam(defaultValue = "asc") String sortDir,

            @RequestParam(defaultValue = "") String search) {

        Page<ProductResponse> response =
                productService.getAllProducts(
                        page,
                        size,
                        sortBy,
                        sortDir,
                        search
                );

        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequest request) {

        ProductResponse response =
                productService.updateProduct(id, request);

        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(
            @PathVariable Long id) {

        productService.deleteProduct(id);

        return ResponseEntity.noContent().build();
    }
}