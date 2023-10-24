package com.favmark.service;

import java.util.List;

import com.favmark.exception.ProductException;
import com.favmark.model.Review;
import com.favmark.model.User;
import com.favmark.request.ReviewRequest;

public interface ReviewService{
	
	public Review createReview(ReviewRequest req, User user)throws ProductException;
	
	public List<Review> getAllReview(Long productId);
}
