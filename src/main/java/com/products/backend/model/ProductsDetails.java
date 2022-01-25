package com.products.backend.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "product_details", schema = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductsDetails {
	  
	  @Id
	  @Column(name = "product_id")
	  private Integer productId;
	  
	  @Column(name = "interest_rate")
	  private BigDecimal interestRate;
	 
	  @Column(name = "start_date")
	  private Date startDate;
	  
	  @Column(name = "maturity_date")
	  private Date maturityDate;

	  @Column(name = "product_name")
	  private String productName;

	
}
