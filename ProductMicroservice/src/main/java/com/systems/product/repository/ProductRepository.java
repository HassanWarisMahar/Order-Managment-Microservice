package com.systems.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systems.product.model.Product;


public interface ProductRepository extends JpaRepository<Product, String>{

}
