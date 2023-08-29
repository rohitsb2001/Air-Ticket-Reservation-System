package com.crimsonlogic.airticketreservationsystem.service;

import java.math.BigInteger;

import org.springframework.http.ResponseEntity;

import com.crimsonlogic.airticketreservationsystem.entity.Users;


public interface UserService {

	public ResponseEntity<?> createUser(Users newUser);

	public Users updateUser(Users newUser);

	public String deleteUser(BigInteger UserId);

	public Iterable<Users> displayAllUser();

	public ResponseEntity<?> findUserById(BigInteger userId);
}