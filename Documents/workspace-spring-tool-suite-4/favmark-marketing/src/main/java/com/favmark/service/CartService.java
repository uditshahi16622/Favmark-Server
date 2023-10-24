package com.favmark.service;

import com.favmark.exception.ProductException;
import com.favmark.model.Cart;
import com.favmark.model.User;
import com.favmark.request.AdditemRequest;

public interface CartService {
public Cart createCart(User user);
	
	public String addCartItem(Long userId,AdditemRequest req)throws ProductException;
	
	public Cart findUserCart(Long userId);
}
