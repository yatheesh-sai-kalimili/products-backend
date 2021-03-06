package com.products.backend.model;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @author yatheesh sai
 * Entity class for table price_history and schema product
 *
 */
@Entity
@Table(name = "price_history", schema = "product")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PriceHistory {
	 
	  @Id
	  @Column(name = "history_id")
	  private BigInteger historyId;
	 
	  @Column(name = "history_date")
	  private Date historyDate;

	  @Column(name = "price_on_that_day")
	  private BigDecimal priceOnThatDay;

	  @Column(name = "product_id")
	  private Integer productId;

}
