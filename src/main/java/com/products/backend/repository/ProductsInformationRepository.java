package com.products.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.products.backend.model.ProductsDetails;


/**
 * @author yatheesh sai
 * ProductInformationRepository which handle findByProductId queries 
 *
 */
public interface ProductsInformationRepository extends JpaRepository<ProductsDetails, Integer>{

}
