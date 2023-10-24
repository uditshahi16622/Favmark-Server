package com.favmark.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.favmark.exception.ProductException;
import com.favmark.model.Product;
import com.favmark.model.Rating;
import com.favmark.model.User;
import com.favmark.repository.RatingRepository;
import com.favmark.request.RatingRequest;

@Service
public class RatingServiceImpl implements RatingService{
	
	private RatingRepository ratingRepository;
	
	private ProductService productService;
	
	public RatingServiceImpl(RatingRepository ratingRepository,ProductService productService) {
		this.productService=productService;
		this.ratingRepository=ratingRepository;
	}

	@Override
	public Rating createRating(RatingRequest req, User user) throws ProductException {
		Product product = productService.findProductById(req.getProductId());
		Rating rating = new Rating();
		
		rating.setProduct(product);
		rating.setUser(user);
		rating.setRating(req.getRating());
		rating.setCreatedAt(LocalDateTime.now());
		return ratingRepository.save(rating);
	}

	@Override
	public List<Rating> getProductRating(Long productId) {
		
		return ratingRepository.getAllProductsRating(productId);
	}
	

}
