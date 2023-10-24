package com.favmark.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.favmark.exception.OrderException;
import com.favmark.model.Address;
import com.favmark.model.Cart;
import com.favmark.model.Cartitem;
import com.favmark.model.Order;
import com.favmark.model.Orderitem;
import com.favmark.model.User;
import com.favmark.repository.AddressRepository;
import com.favmark.repository.CartRepository;
import com.favmark.repository.OrderItemRepository;
import com.favmark.repository.OrderRepository;
import com.favmark.repository.UserRepository;

@Service
public class OrderServiceImpl implements OrderService{
	
	private OrderRepository orderRepository;
	private CartService cartService;
	private AddressRepository addressRepository;
	private UserRepository userRepository;
	private OrderItemService orderItemService;
	private OrderItemRepository orderItemRepository;
	
	
	
	
	public OrderServiceImpl(OrderRepository orderRepository, CartService cartService, AddressRepository addressRepository,UserRepository userRepository,OrderItemService orderItemService,OrderItemRepository orderItemRepository) {
		// TODO Auto-generated constructor stub
		this.orderRepository = orderRepository;
		this.addressRepository = addressRepository;
		this.userRepository = userRepository;
		this.orderItemRepository = orderItemRepository;
		this.orderItemService = orderItemService;
		this.cartService = cartService;
	}
	@Override
	public Order createOrder(User user, Address shippingAddress) {
		shippingAddress.setUser(user);
		Address address = addressRepository.save(shippingAddress);
		user.getAddress().add(address);
		userRepository.save(user);
		
		Cart cart = cartService.findUserCart(user.getId());
		List<Orderitem> orderItems = new ArrayList<>();
		for(Cartitem item:cart.getCartitems()) {
			Orderitem orderItem = new Orderitem();
			
			orderItem.setPrice(item.getPrice());
			orderItem.setProduct(item.getProduct());
			orderItem.setQuantity(item.getQuantity());
			orderItem.setSize(item.getSize());
			orderItem.setUserId(item.getUserId());
			orderItem.setDiscountedPrice(item.getDiscountedPrice());
			
			Orderitem createOrderitem = orderItemRepository.save(orderItem);
			
			orderItems.add(createOrderitem);
		}
		
		Order createOrder = new Order();
		createOrder.setUser(user);
		createOrder.setOrderitems(orderItems);
		createOrder.setTotalPrice(cart.getTotalPrice());
		createOrder.setTotalDiscountedPrice(cart.getTotalDiscountedPrice());
		createOrder.setDiscount(cart.getDiscount());
		createOrder.setTotalItem(cart.getTotalitem());
		createOrder.setShippingAddress(address);
		createOrder.setOrderDate(LocalDateTime.now());
		createOrder.setOrderStatus("PENDING");
		createOrder.getPaymentDetails().setStatus("PENDING");
		createOrder.setCreateAt(LocalDateTime.now());
		
		Order savedOrder = orderRepository.save(createOrder);
		
		for(Orderitem item:orderItems) {
			item.setOrder(savedOrder);
			orderItemRepository.save(item);
		}
		return savedOrder;
	}

	@Override
	public Order findOrderById(Long orderId) throws OrderException {
		Optional<Order> order = orderRepository.findById(orderId);
		if(order.isPresent()) {
			return order.get();
		}
		throw new OrderException("order Not exist with id "+orderId);
	}
	@Override
	public List<Order> userOrderHistory(Long userId) {
		List<Order> orders = orderRepository.getUsersOrders(userId);
		return orders;
	}

	@Override
	public Order placedOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("PLACED");
		order.getPaymentDetails().setStatus("COMPLETED");
		return order;
	}

	@Override
	public Order confirmedOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("CONFIRMED");
		return orderRepository.save(order);
	}

	@Override
	public Order shippedOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("SHIPPED");
		return orderRepository.save(order);
	}

	@Override
	public Order deliveredOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("DILVERED");
		return orderRepository.save(order);
	}

	@Override
	public Order canceledOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		order.setOrderStatus("CANCELLED");
		return orderRepository.save(order);
	}

	@Override
	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Long orderId) throws OrderException {
		Order order = findOrderById(orderId);
		orderRepository.deleteById(orderId);
		
	}

}
