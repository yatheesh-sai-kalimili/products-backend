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
import com.products.backend.model.PriceHistory;
import com.products.backend.repository.PriceHistoryRepository;
import com.products.backend.repository.ProductsInformationRepository;
import com.products.backend.request.productsInfoRequest;
import com.products.backend.response.productsInfoResponse;

@Service
public class ProductService {
	@Autowired
	PriceHistoryRepository repository;

	@Autowired
	ProductsInformationRepository productInfoRepo;

	public void save(MultipartFile file) throws ParseException {
		try {
			List<PriceHistory> productHistory = ExcelHelper.excelToProductsHistory(file.getInputStream());
			repository.saveAll(productHistory);
		} catch (IOException e) {
			throw new RuntimeException("fail to store excel data: " + e.getMessage());
		}
	}
	public productsInfoResponse getProductsInformation(productsInfoRequest request) {

		Optional<ProductsDetails> pd = productInfoRepo.findById(request.getProductId());
		PriceHistory pdHistory = repository.findByProductIdAndDate(request.getProductId(),request.getRequestDate());
		productsInfoResponse productsInfo = new productsInfoResponse();
		productsInfo.setName(pd.get().getProductName());
		productsInfo.setPrice(pdHistory.getPriceOnThatDay());
		productsInfo.setInterestRate(pd.get().getInterestRate());

		return productsInfo;
	}
	public List<BigDecimal> getAllPriceHistoryByProductId(Integer productId) {
		return repository.findByProductId(productId);
	}

	public List<PriceHistory> getAllPriceHistory() {

		return repository.findAll();
	}


}