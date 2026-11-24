package com.shivendra.mini_erp_backend.dto;


import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Builder
public class ProductResponse {

    private Long id;

    private String productCode;

    private String productName;

    private String productType;

    private String category;

    private String subCategory;

    private String uom;

    private BigDecimal mrpPrice;

    private BigDecimal sellingPrice;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}