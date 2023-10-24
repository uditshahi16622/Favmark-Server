package com.favmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.favmark.model.Cart;
import com.favmark.model.Cartitem;
import com.favmark.model.Product;

public interface CartitemRepository extends JpaRepository<Cartitem, Long>{
	
	
	@Query("SELECT ci FROM Cartitem ci WHERE ci.cart= :cart AND ci.product= :product AND ci.size= :size AND ci.userId= :userId")
	public Cartitem isCartitemExist(@Param("cart")Cart cart, @Param("product")Product product, @Param("size")String size,@Param("userId")Long userId);
}
