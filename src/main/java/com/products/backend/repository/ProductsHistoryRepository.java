package com.products.backend.repository;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.products.backend.model.ProductsHistory;

public interface ProductsHistoryRepository extends JpaRepository<ProductsHistory, BigInteger>{

	
}
