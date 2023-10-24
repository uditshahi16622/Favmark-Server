package com.favmark.service;

import java.util.List;

import com.favmark.exception.ProductException;
import com.favmark.model.Rating;
import com.favmark.model.User;
import com.favmark.request.RatingRequest;

public interface RatingService {
	
	public Rating createRating(RatingRequest req, User user)throws ProductException;
	
	public List<Rating> getProductRating(Long productId);
	
	
}
