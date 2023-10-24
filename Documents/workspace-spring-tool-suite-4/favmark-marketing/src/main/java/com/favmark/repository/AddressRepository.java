package com.favmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.favmark.model.Address;

public interface AddressRepository extends JpaRepository<Address, Long> {

}
