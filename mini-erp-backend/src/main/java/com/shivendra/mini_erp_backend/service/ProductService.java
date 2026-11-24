package com.shivendra.mini_erp_backend.service;


import com.shivendra.mini_erp_backend.dto.CreateProductRequest;
import com.shivendra.mini_erp_backend.dto.ProductResponse;
import com.shivendra.mini_erp_backend.dto.UpdateProductRequest;
import org.springframework.data.domain.Page;
import org.springframework.transaction.annotation.Transactional;

public interface ProductService {

    ProductResponse createProduct(CreateProductRequest request);
    ProductResponse getProductById(Long id);
    Page<ProductResponse> getAllProducts(
            int page,
            int size,
            String sortBy,
            String sortDir,
            String search
    );

    @Transactional(readOnly = true)
    Page<ProductResponse> getAllProducts(
            int page,
            int size,
            String sortBy,
            String sortDir);

    ProductResponse updateProduct(
            Long id,
            UpdateProductRequest request
    );
    void deleteProduct(Long id);
}