package com.favmark.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Orderitem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@ManyToOne
	private Product product;
	
	@JsonIgnore
	@ManyToOne
	private Order order;
	
	private String size;
	
	private int quantity;
	
	private Integer price;
	
	
	private Integer discountedPrice;
	
	private Long userId;
	
	private LocalDateTime deliveryDate;
	
	public Orderitem() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	public Integer getDiscountedPrice() {
		return discountedPrice;
	}

	public void setDiscountedPrice(Integer discountedPrice) {
		this.discountedPrice = discountedPrice;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public LocalDateTime getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDateTime deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public Orderitem(Long id, Product product, Order order, String size, int quantity, Integer price,
			Integer discountedPrice, Long userId, LocalDateTime deliveryDate) {
		super();
		this.id = id;
		this.product = product;
		this.order = order;
		this.size = size;
		this.quantity = quantity;
		this.price = price;
		this.discountedPrice = discountedPrice;
		this.userId = userId;
		this.deliveryDate = deliveryDate;
	}
	
}
