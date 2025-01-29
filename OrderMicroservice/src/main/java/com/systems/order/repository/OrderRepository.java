package com.systems.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.systems.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, String>{

}
