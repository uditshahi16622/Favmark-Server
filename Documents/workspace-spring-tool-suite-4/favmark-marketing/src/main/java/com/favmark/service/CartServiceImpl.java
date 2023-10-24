package com.favmark.service;

import org.springframework.stereotype.Service;

import com.favmark.exception.ProductException;
import com.favmark.model.Cart;
import com.favmark.model.Cartitem;
import com.favmark.model.Product;
import com.favmark.model.User;
import com.favmark.repository.CartRepository;
import com.favmark.request.AdditemRequest;

@Service
public class CartServiceImpl implements CartService{
	
	private CartRepository cartRepository;
	
	private CartitemService cartitemService;
	
	private ProductService productService;
	
	public CartServiceImpl(CartRepository cartRepository,CartitemService cartitemService,ProductService productService) {
		this.cartRepository=cartRepository;
		this.cartitemService=cartitemService;
		this.productService=productService;
	}
	

	@Override
	public Cart createCart(User user) {
		Cart cart = new Cart();
		cart.setUser(user);
		return cartRepository.save(cart);
	}

	@Override
	public String addCartItem(Long userId, AdditemRequest req) throws ProductException {
		
		
		Cart cart = cartRepository.findByUserId(userId);
		Product product = productService.findProductById(req.getProductId());
		Cartitem isPresent  = cartitemService.isCartitemExist(cart, product, req.getSize(), userId);
		if(isPresent==null) {
			Cartitem cartitem = new Cartitem();
			cartitem.setProduct(product);
			cartitem.setCart(cart);
			cartitem.setQuantity(req.getQuantity());
			cartitem.setUserId(userId);
			
			int price = req.getQuantity()*product.getDiscountedPrice();
			cartitem.setPrice(price);
			cartitem.setSize(req.getSize());
			
			Cartitem createdcartCartitem = cartitemService.createCartItem(cartitem);
			cart.getCartitems().add(createdcartCartitem);
		}
		
		return "item Add to Cart";
	}

	@Override
	public Cart findUserCart(Long userId) {
		Cart cart = cartRepository.findByUserId(userId);
		
		int totalPrice = 0;
		int totalDiscountedPrice = 0;
		int totalItem = 0;
		
		for(Cartitem cartitem :cart.getCartitems()) {
			totalPrice= totalPrice+ cartitem.getPrice();
			totalDiscountedPrice=totalDiscountedPrice+cartitem.getDiscountedPrice();
			totalItem=totalItem+cartitem.getQuantity();
		}
		cart.setTotalitem(totalItem);
		cart.setDiscount(totalPrice-totalDiscountedPrice);
		cart.setTotalDiscountedPrice(totalDiscountedPrice);
		cart.setTotalPrice(totalPrice);
		return cartRepository.save(cart);
	}
	

}

