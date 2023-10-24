package com.favmark.service;

import com.favmark.exception.UserException;
import com.favmark.model.User;

public interface UserService {
	
	public User findUserById(Long userId) throws UserException;
	
	public User findUserProfileByJwt(String jwt)throws UserException;
	
}
