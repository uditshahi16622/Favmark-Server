package com.favmark.service;

import java.util.List;

import com.favmark.exception.OrderException;
import com.favmark.model.Address;
import com.favmark.model.Order;
import com.favmark.model.User;

public interface OrderService {
	
	public Order createOrder(User user,Address shippingAddress);
	
	public Order findOrderById(Long orderId) throws OrderException;
	
	public List<Order> userOrderHistory(Long userId);
	
	public Order placedOrder(Long orderId) throws OrderException;
	
	public Order confirmedOrder(Long orderId) throws OrderException;
	
	public Order shippedOrder(Long orderId)throws OrderException;
	
	public Order deliveredOrder(Long orderId)throws OrderException;
	
	public Order canceledOrder(Long orderId) throws OrderException;
	
	public List<Order> getAllOrders();
	
	public void deleteOrder(Long orderId)throws OrderException;
	
}
