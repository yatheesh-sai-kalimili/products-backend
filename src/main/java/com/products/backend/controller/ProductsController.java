package com.products.backend.controller;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.products.backend.helper.ExcelHelper;
import com.products.backend.model.PriceHistory;
import com.products.backend.request.productsInfoRequest;
import com.products.backend.response.ResponseMessage;
import com.products.backend.response.productsInfoResponse;
import com.products.backend.service.ProductService;

@CrossOrigin("http://localhost:4200")
@Controller
@RequestMapping("/api/products")
public class ProductsController {
	 @Autowired
	  ProductService productService;

	  @RequestMapping(value ="/upload", method = RequestMethod.POST,
			    consumes = {"multipart/form-data"})
	  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
	    String message = "";

	    if (ExcelHelper.hasExcelFormat(file)) {
	      try {
	    	  productService.save(file);

	        message = "Uploaded the file successfully: " + file.getOriginalFilename();
	        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
	      } catch (Exception e) {
	        message = "Could not upload the file: " + file.getOriginalFilename() +  e.getMessage() + "!";
	        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
	      }
	    }

	    message = "Please upload an excel file!";
	    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	  }

	  @PostMapping("/productsInformation")
	  public ResponseEntity<productsInfoResponse> getProductInformation(@RequestBody productsInfoRequest request) {
	    try {
	    	productsInfoResponse ProductsHistorys = productService.getProductsInformation(request);

	      return new ResponseEntity<>(ProductsHistorys, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @GetMapping("/priceHistory/{productid}")
	  public ResponseEntity<List<BigDecimal>> getAllPriceHistoryByProductId(@PathVariable(value="productid") Integer productId) {
	    try {
	      List<BigDecimal> priceHistory = productService.getAllPriceHistoryByProductId(productId);

	      if (priceHistory.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(priceHistory, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }
	  
	  @GetMapping("/priceHistory")
	  public ResponseEntity<List<PriceHistory>> getAllProductsHistory() {
	    try {
	      List<PriceHistory> priceHistory = productService.getAllPriceHistory();

	      if (priceHistory.isEmpty()) {
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	      }

	      return new ResponseEntity<>(priceHistory, HttpStatus.OK);
	    } catch (Exception e) {
	      return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
	    }
	  }

}
