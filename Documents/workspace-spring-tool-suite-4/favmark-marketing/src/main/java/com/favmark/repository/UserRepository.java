package com.favmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.favmark.model.User;

public interface UserRepository extends JpaRepository<User, Long>{
	
	public User findByEmail(String email);
}
