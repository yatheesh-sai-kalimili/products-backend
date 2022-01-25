package com.products.backend.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.products.backend.model.PriceHistory;

public interface PriceHistoryRepository extends JpaRepository<PriceHistory, BigInteger>{

	@Query(nativeQuery=true, value="select price_on_that_day from product.price_history where product_id = ?1 and history_date > current_date ORDER BY history_date LIMIT 3")
	List<BigDecimal> findByProductId(Integer productId);
	
	@Query(nativeQuery=true, value="select * from product.price_history where product_id = ?1 and history_date =?2")
	PriceHistory findByProductIdAndDate(Integer productId, Date requestDate);

}
