package com.favmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favmark.model.Cart;

public interface CartRepository extends JpaRepository<Cart, Long>{
	
	@Query("SELECT c from Cart c WHERE c.user.id= :userId")
	public Cart findByUserId(@Param("userId")Long userId);
}
