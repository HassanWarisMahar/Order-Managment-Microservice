package com.systems.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systems.inventory.model.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory, Integer>{
	
	List<Inventory> findByProductIdIn(List<String> productIdList);

}
