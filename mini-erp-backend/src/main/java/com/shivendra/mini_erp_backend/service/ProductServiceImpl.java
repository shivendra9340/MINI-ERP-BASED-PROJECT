package com.shivendra.mini_erp_backend.service;

import com.shivendra.mini_erp_backend.dto.CreateProductRequest;
import com.shivendra.mini_erp_backend.dto.ProductResponse;
import com.shivendra.mini_erp_backend.dto.UpdateProductRequest;
import com.shivendra.mini_erp_backend.entity.Product;
import com.shivendra.mini_erp_backend.exception.ResourceConflictException;
import com.shivendra.mini_erp_backend.exception.ResourceNotFoundException;
import com.shivendra.mini_erp_backend.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    @Transactional
    public ProductResponse createProduct(CreateProductRequest request) {

        if (productRepository.existsByProductCode(request.getProductCode())) {
            throw new ResourceConflictException(
                    "Product code already exists: " + request.getProductCode()
            );
        }

        Product product = Product.builder()
                .productCode(request.getProductCode())
                .productName(request.getProductName())
                .productType(request.getProductType())
                .category(request.getCategory())
                .subCategory(request.getSubCategory())
                .uom(request.getUom())
                .mrpPrice(request.getMrpPrice())
                .sellingPrice(request.getSellingPrice())
                .build();

        Product savedProduct = productRepository.save(product);

        return ProductResponse.builder()
                .id(savedProduct.getId())
                .productCode(savedProduct.getProductCode())
                .productName(savedProduct.getProductName())
                .productType(savedProduct.getProductType())
                .category(savedProduct.getCategory())
                .subCategory(savedProduct.getSubCategory())
                .uom(savedProduct.getUom())
                .mrpPrice(savedProduct.getMrpPrice())
                .sellingPrice(savedProduct.getSellingPrice())
                .createdAt(savedProduct.getCreatedAt())
                .updatedAt(savedProduct.getUpdatedAt())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public ProductResponse getProductById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id
                        )
                );

        return ProductResponse.builder()
                .id(product.getId())
                .productCode(product.getProductCode())
                .productName(product.getProductName())
                .productType(product.getProductType())
                .category(product.getCategory())
                .subCategory(product.getSubCategory())
                .uom(product.getUom())
                .mrpPrice(product.getMrpPrice())
                .sellingPrice(product.getSellingPrice())
                .createdAt(product.getCreatedAt())
                .updatedAt(product.getUpdatedAt())
                .build();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductResponse> getAllProducts(
            int page,
            int size,
            String sortBy,
            String sortDir,
            String search) {

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable =
                PageRequest.of(page, size, sort);

        Page<Product> productPage;

        if (search == null || search.isBlank()) {

            productPage =
                    productRepository.findAll(pageable);

        } else {

            productPage =
                    productRepository
                            .findByProductNameContainingIgnoreCaseOrProductCodeContainingIgnoreCase(
                                    search,
                                    search,
                                    pageable
                            );
        }

        return productPage.map(product ->
                ProductResponse.builder()
                        .id(product.getId())
                        .productCode(product.getProductCode())
                        .productName(product.getProductName())
                        .productType(product.getProductType())
                        .category(product.getCategory())
                        .subCategory(product.getSubCategory())
                        .uom(product.getUom())
                        .mrpPrice(product.getMrpPrice())
                        .sellingPrice(product.getSellingPrice())
                        .createdAt(product.getCreatedAt())
                        .updatedAt(product.getUpdatedAt())
                        .build()
        );
    }
    @Transactional(readOnly = true)
    @Override
    public Page<ProductResponse> getAllProducts(
            int page,
            int size,
            String sortBy,
            String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase("desc")
                ? Sort.by(sortBy).descending()
                : Sort.by(sortBy).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        Page<Product> productPage =
                productRepository.findAll(pageable);

        return productPage.map(product ->
                ProductResponse.builder()
                        .id(product.getId())
                        .productCode(product.getProductCode())
                        .productName(product.getProductName())
                        .productType(product.getProductType())
                        .category(product.getCategory())
                        .subCategory(product.getSubCategory())
                        .uom(product.getUom())
                        .mrpPrice(product.getMrpPrice())
                        .sellingPrice(product.getSellingPrice())
                        .createdAt(product.getCreatedAt())
                        .updatedAt(product.getUpdatedAt())
                        .build()
        );
    }

    @Override
    @Transactional
    public ProductResponse updateProduct(
            Long id,
            UpdateProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id
                        )
                );

        product.setProductName(request.getProductName());
        product.setProductType(request.getProductType());
        product.setCategory(request.getCategory());
        product.setSubCategory(request.getSubCategory());
        product.setUom(request.getUom());
        product.setMrpPrice(request.getMrpPrice());
        product.setSellingPrice(request.getSellingPrice());

        Product updatedProduct = productRepository.save(product);

        return ProductResponse.builder()
                .id(updatedProduct.getId())
                .productCode(updatedProduct.getProductCode())
                .productName(updatedProduct.getProductName())
                .productType(updatedProduct.getProductType())
                .category(updatedProduct.getCategory())
                .subCategory(updatedProduct.getSubCategory())
                .uom(updatedProduct.getUom())
                .mrpPrice(updatedProduct.getMrpPrice())
                .sellingPrice(updatedProduct.getSellingPrice())
                .createdAt(updatedProduct.getCreatedAt())
                .updatedAt(updatedProduct.getUpdatedAt())
                .build();
    }

    @Override
    @Transactional
    public void deleteProduct(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Product not found with id: " + id
                        )
                );

        productRepository.delete(product);
    }
}