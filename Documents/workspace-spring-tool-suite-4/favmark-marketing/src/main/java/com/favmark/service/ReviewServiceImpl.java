package com.favmark.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.favmark.exception.ProductException;
import com.favmark.model.Product;
import com.favmark.model.Review;
import com.favmark.model.User;
import com.favmark.repository.ProductRepository;
import com.favmark.repository.ReviewRepository;
import com.favmark.request.ReviewRequest;

@Service
public class ReviewServiceImpl implements ReviewService{

	private ReviewRepository reviewRepository;
	private ProductService productService;
	private ProductRepository productRepository;
	
	public ReviewServiceImpl(ReviewRepository reviewRepository,ProductService productService,ProductRepository productRepository) {
		this.reviewRepository=reviewRepository;
		this.productRepository=productRepository;
		this.productService=productService;
	}
	@Override
	public Review createReview(ReviewRequest req, User user) throws ProductException {
		Product product = productService.findProductById(req.getProductId());
		
		Review review = new Review();
		review.setUser(user);
		review.setProduct(product);
		review.setReview(req.getReview());
		review.setCreatedAt(LocalDateTime.now());
		
		return reviewRepository.save(review);
	}

	@Override
	public List<Review> getAllReview(Long productId) {
		
		return reviewRepository.getAllProductsReview(productId);
	}

}
