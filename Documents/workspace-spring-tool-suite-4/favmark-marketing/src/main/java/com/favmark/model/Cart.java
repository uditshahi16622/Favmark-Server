package com.favmark.model;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Cart {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="user_id",nullable = false)
	private User user;
	
	@OneToMany(mappedBy = "cart", cascade = CascadeType.ALL,orphanRemoval = true)
	@Column(name="cart_items")
	private Set<Cartitem> cartitems = new HashSet<>();
	
	@Column(name="total_price")
	private double totalPrice;
	
	@Column(name="total_item")
	private int totalitem;
	
	private int discount;
	
	private int totalDiscountedPrice;
	
	public Cart() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Set<Cartitem> getCartitems() {
		return cartitems;
	}

	public void setCartitems(Set<Cartitem> cartitems) {
		this.cartitems = cartitems;
	}

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public int getTotalitem() {
		return totalitem;
	}

	public void setTotalitem(int totalitem) {
		this.totalitem = totalitem;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getTotalDiscountedPrice() {
		return totalDiscountedPrice;
	}

	public void setTotalDiscountedPrice(int totalDiscountedPrice) {
		this.totalDiscountedPrice = totalDiscountedPrice;
	}
	
}
