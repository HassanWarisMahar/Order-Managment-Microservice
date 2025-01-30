package com.systems.product.controller;

import java.util.List;

import com.systems.product.model.Product;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.systems.product.dto.ProductRequest;
import com.systems.product.dto.ProductResponse;
import com.systems.product.service.ProductService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "/api/v1")
@AllArgsConstructor
public class ProductController {

	private final ProductService productService;
	
	@PostMapping(path = "/products")
	public ResponseEntity<ProductResponse> createProduct(@RequestBody ProductRequest productRequest) throws Exception{
		ProductResponse response = this.productService.createProduct(productRequest);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	@Operation(summary = "Get a product by its id")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Found the product",
					content = { @Content(mediaType = "application/json",
							schema = @Schema(implementation = Product.class)) }),
			@ApiResponse(responseCode = "400", description = "Invalid id supplied",
					content = @Content),
			@ApiResponse(responseCode = "404", description = "Product not found",
					content = @Content) })
	@GetMapping(path = "/products")
	public ResponseEntity<List<ProductResponse>> getAllProducts(){
		List<ProductResponse> allProducts = this.productService.getAllProducts();
		return ResponseEntity.status(HttpStatus.OK).body(allProducts);
	}
}
