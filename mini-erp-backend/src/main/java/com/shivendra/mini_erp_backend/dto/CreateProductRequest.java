package com.shivendra.mini_erp_backend.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CreateProductRequest {

    @NotBlank(message = "Product code is required")
    private String productCode;

    @NotBlank(message = "Product name is required")
    private String productName;

    private String productType;

    private String category;

    private String subCategory;

    private String uom;

    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "MRP price cannot be negative"
    )
    private BigDecimal mrpPrice;

    @DecimalMin(
            value = "0.0",
            inclusive = true,
            message = "Selling price cannot be negative"
    )
    private BigDecimal sellingPrice;
}