package com.shivendra.mini_erp_backend.repository;


import com.shivendra.mini_erp_backend.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByProductCode(String productCode);
    Page<Product> findByProductNameContainingIgnoreCaseOrProductCodeContainingIgnoreCase(
            String productName,
            String productCode,
            Pageable pageable
    );
}