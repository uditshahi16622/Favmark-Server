package com.favmark.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.favmark.exception.CartitemException;
import com.favmark.exception.UserException;
import com.favmark.model.Cart;
import com.favmark.model.Cartitem;
import com.favmark.model.Product;
import com.favmark.model.User;
import com.favmark.repository.CartRepository;
import com.favmark.repository.CartitemRepository;

@Service
public class CartItemServiceImpl implements CartitemService{
	
	private CartitemRepository cartitemRepository;
	
	private UserService userService;
	private CartRepository cartRepository;
	
	public CartItemServiceImpl(CartitemRepository cartitemRepository,UserService userService, CartRepository cartRepository) {
		this.cartitemRepository=cartitemRepository;
		this.cartRepository=cartRepository;
		this.userService=userService;
	}

	@Override
	public Cartitem createCartItem(Cartitem cartitem) {
		cartitem.setQuantity(1);
		cartitem.setPrice(cartitem.getProduct().getPrice()*cartitem.getQuantity());
		cartitem.setDiscountedPrice(cartitem.getProduct().getDiscountedPrice()* cartitem.getQuantity());
		
		Cartitem createdCartItem = cartitemRepository.save(cartitem);
		
		return createdCartItem;
		
	}

	@Override
	public Cartitem updateCartItem(Long userId, Long id, Cartitem cartitem) throws CartitemException, UserException {
		Cartitem item = findCartItemById(id);
		User user = userService.findUserById(item.getUserId());
		
		if(user.getId().equals(userId)) {
			item.setQuantity(cartitem.getQuantity());
			item.setPrice(item.getQuantity()*item.getProduct().getPrice());
			item.setDiscountedPrice(item.getProduct().getDiscountedPrice()*item.getQuantity());
		}
		
		return cartitemRepository.save(item);
	}

	@Override
	public Cartitem isCartitemExist(Cart cart, Product product, String size, Long userId) {
		Cartitem item = cartitemRepository.isCartitemExist(cart, product, size, userId);
		
		return item;
	}

	@Override
	public void removeCartitem(Long userId, Long cartItemId) throws CartitemException, UserException {
		Cartitem item = findCartItemById(cartItemId);
		
		User user = userService.findUserById(item.getUserId());
		
		User reqUser = userService.findUserById(userId);
		
		if(user.getId().equals(reqUser.getId())) {
			cartitemRepository.deleteById(cartItemId);
		}
		else {
			throw new UserException("You cant remove another users item");
		}
		
		
		
	}

	@Override
	public Cartitem findCartItemById(Long CartItemId) throws CartitemException {
		Optional<Cartitem>opt = cartitemRepository.findById(CartItemId);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new CartitemException("cartitem not found");
	}

}
