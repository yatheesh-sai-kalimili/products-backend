package com.products.backend.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.products.backend.helper.ExcelHelper;
import com.products.backend.model.ProductsDetails;
import com.products.backend.model.ProductsHistory;
import com.products.backend.repository.ProductsHistoryRepository;
import com.products.backend.repository.ProductsInformationRepository;
import com.products.backend.request.productsInfoRequest;
import com.products.backend.response.productsInfoResponse;

@Service
public class ProductHistoryService {
  @Autowired
  ProductsHistoryRepository repository;
  
  @Autowired
  ProductsInformationRepository productInfoRepo;

  public void save(MultipartFile file) throws ParseException {
    try {
      List<ProductsHistory> productHistory = ExcelHelper.excelToProductsHistory(file.getInputStream());
      repository.saveAll(productHistory);
    } catch (IOException e) {
      throw new RuntimeException("fail to store excel data: " + e.getMessage());
    }
  }
  public List<BigDecimal> getAllProductsHistoryByProductId(Integer productId) {
	  return repository.findByProductId(productId);
  }


}