package com.products.backend.request;

import java.util.Date;

import lombok.Data;
/**
 * @author yatheesh sai
 * productInfo request which has productId and requestDate for fetching price, name and interest rate of product
 *
 */
@Data
public class productsInfoRequest {
	 
	  private Date requestDate;

	  private Integer productId;

}
