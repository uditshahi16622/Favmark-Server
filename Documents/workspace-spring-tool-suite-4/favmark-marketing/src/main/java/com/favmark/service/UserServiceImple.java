package com.favmark.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.favmark.config.JwtProvider;
import com.favmark.exception.UserException;
import com.favmark.model.User;
import com.favmark.repository.UserRepository;

@Service
public class UserServiceImple implements UserService{
	
	private UserRepository userRepository;
	private JwtProvider jwtProvider;
	public UserServiceImple(UserRepository userRepository, JwtProvider jwtProvider) {
		// TODO Auto-generated constructor stub
		this.userRepository=userRepository;
		this.jwtProvider=jwtProvider;
	}

	@Override
	public User findUserById(Long userId) throws UserException {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			return user.get();
		}
		
		throw new UserException("user not found with id: "+userId);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		String email = jwtProvider.getEmailFromToken(jwt);
		
		User user = userRepository.findByEmail(email);
		
		if(user==null) {
			throw new UserException("user not found with "+email);
		}
		return user;
	}

}
