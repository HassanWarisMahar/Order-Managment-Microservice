package com.systems.inventory.service;

import java.util.List;

import com.systems.inventory.dto.InventoryRequest;
import com.systems.inventory.dto.InventoryResponse;
import com.systems.inventory.dto.UpdateInventoryRequest;

public interface InventoryService {

	InventoryResponse addToInventory(InventoryRequest inventoryRequest);
	
	List<InventoryResponse> getStockAvailability(List<String> productId);
	
	void updateInventory(UpdateInventoryRequest updateInventoryRequest);
}
