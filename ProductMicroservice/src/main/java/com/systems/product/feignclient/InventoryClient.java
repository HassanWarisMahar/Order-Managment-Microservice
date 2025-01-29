package com.systems.product.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.systems.product.dto.InventoryRequest;
import com.systems.product.dto.InventoryResponse;

@FeignClient(name = "inventory-service")
public interface InventoryClient {

	@PostMapping(path = "api/v1/inventory")
	public ResponseEntity<InventoryResponse> addToInventory(@RequestBody InventoryRequest inventoryRequest);
}
