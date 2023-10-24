package com.favmark.service;

import com.favmark.exception.CartitemException;
import com.favmark.exception.UserException;
import com.favmark.model.Cart;
import com.favmark.model.Cartitem;
import com.favmark.model.Product;

public interface CartitemService {
	
	public Cartitem createCartItem(Cartitem cartitem);
	
	public Cartitem updateCartItem(Long userId, Long id, Cartitem cartitem)throws CartitemException, UserException;
	
	public Cartitem isCartitemExist(Cart cart, Product product, String size, Long userId);
	
	public void removeCartitem(Long userId, Long cartItemId)throws CartitemException, UserException;
	
	public Cartitem findCartItemById(Long CartItemId)throws CartitemException;
}
