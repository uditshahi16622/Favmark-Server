package com.favmark.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.favmark.exception.CartitemException;
import com.favmark.exception.UserException;
import com.favmark.model.ApiResponse;
import com.favmark.model.Cartitem;
import com.favmark.model.User;
import com.favmark.service.CartitemService;
import com.favmark.service.UserService;

@CrossOrigin(origins = "http://localhost:3000/")
@RestController
@RequestMapping("/api/cart_item")
public class CartItemController {
	
	@Autowired
	private CartitemService cartitemService;
	
	@Autowired
	private UserService userService;
	
	@DeleteMapping("/{cartitemId}")
	public ResponseEntity<ApiResponse> deleteCartitem(@PathVariable Long cartitemId,@RequestHeader("Authorization")String jwt )throws UserException, CartitemException{
		User user = userService.findUserProfileByJwt(jwt);
		cartitemService.removeCartitem(user.getId(), cartitemId);
		
		ApiResponse res = new ApiResponse();
		res.setMessage("item is deleted from cart");
		res.setStatus(true);
		
		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	@PutMapping("/{cartitemId}")
	public ResponseEntity<Cartitem> updateCartitem(@RequestBody Cartitem cartitem,@PathVariable Long cartitemId,@RequestHeader("Authorization")String jwt)throws UserException,CartitemException{
		User user = userService.findUserProfileByJwt(jwt);
		
		Cartitem updateCartitem = cartitemService.updateCartItem(user.getId(), cartitemId, cartitem);
		
		return new ResponseEntity<>(updateCartitem,HttpStatus.OK);
	}
	
}
