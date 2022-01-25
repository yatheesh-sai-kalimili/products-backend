package com.products.backend.response;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class productsInfoResponse {
private BigDecimal price;
private String name;
private BigDecimal interestRate;
}
