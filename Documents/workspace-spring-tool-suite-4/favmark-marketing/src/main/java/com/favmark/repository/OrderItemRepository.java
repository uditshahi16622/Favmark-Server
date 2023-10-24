package com.favmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.favmark.model.Orderitem;

public interface OrderItemRepository extends JpaRepository<Orderitem, Long>{
	
}
