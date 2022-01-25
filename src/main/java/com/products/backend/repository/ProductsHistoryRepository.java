package com.products.backend.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.products.backend.model.ProductsHistory;

public interface ProductsHistoryRepository extends JpaRepository<ProductsHistory, BigInteger>{

	@Query(nativeQuery=true, value="select price_on_that_day from product.product_history where product_id = ?1 and history_date > current_date ORDER BY history_date LIMIT 3")
	List<BigDecimal> findByProductId(Integer productId);
}
