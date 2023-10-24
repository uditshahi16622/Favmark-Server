package com.favmark.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.favmark.model.Orderitem;
import com.favmark.repository.OrderItemRepository;

@Service
public class OrderItemServiceImpl implements OrderItemService{
	
	@Autowired
	private OrderItemRepository orderItemRepository;
	
	@Override
	public Orderitem createOrderItem(Orderitem orderitem) {
		
		return orderItemRepository.save(orderitem);
	}

}
