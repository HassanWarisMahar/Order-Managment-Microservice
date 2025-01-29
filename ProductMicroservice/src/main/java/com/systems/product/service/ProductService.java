package com.systems.product.service;

import java.util.List;

import com.systems.product.dto.ProductRequest;
import com.systems.product.dto.ProductResponse;

public interface ProductService {

	ProductResponse createProduct(ProductRequest productRequest) throws Exception;
	
	List<ProductResponse> getAllProducts();
}
