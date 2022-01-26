package com.products.backend.response;

import java.math.BigDecimal;

import lombok.Data;

/**
 * @author yatheesh sai
 * productInfoResponse fetching price, name and interest rate of product
 *
 */
@Data
public class productsInfoResponse {
private BigDecimal price;
private String name;
private BigDecimal interestRate;
}
