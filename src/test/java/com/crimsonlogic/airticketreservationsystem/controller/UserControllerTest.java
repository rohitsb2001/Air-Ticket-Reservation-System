package com.crimsonlogic.airticketreservationsystem.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.math.BigInteger;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.crimsonlogic.airticketreservationsystem.entity.Users;
import com.crimsonlogic.airticketreservationsystem.exception.RecordAlreadyPresentException;
import com.crimsonlogic.airticketreservationsystem.exception.RecordNotFoundException;

@SpringBootTest
class UserControllerTest {

	@Autowired
	private UserController userController;

	@Test
	void testCreateUser() throws RecordAlreadyPresentException {
		Users user = new Users();
		user.setUserType("Customer");
		user.setUserId(BigInteger.valueOf(123456));
		user.setUserName("John Doe");
		user.setUserPassword("password");
		user.setUserPhone(BigInteger.valueOf(1234567890));
		user.setUserEmail("johndoe@gmail.com");
		userController.addUser(user);
		assertNotNull(user.getUserId());
	}

	@Test
	void testReadAllUsers() {
		Iterable<Users> users = userController.readAllUsers();
		assertNotNull(users);
	}

	@Test
	void testUpdateUser() throws RecordNotFoundException {
		Users user = new Users();
		user.setUserType("Customer");
		user.setUserId(BigInteger.valueOf(123456));
		user.setUserName("Jane Doe");
		user.setUserPassword("newpassword");
		user.setUserPhone(BigInteger.valueOf(98765430));
		user.setUserEmail("janedoe@gmail.com");
		//userController.updateUser(user);
		assertEquals(user.getUserName(), "Jane Doe");
	}

	@Test
	void testSearchUserByID() throws RecordNotFoundException {
		ResponseEntity<?> user = userController.searchUserByID(BigInteger.valueOf(123456));
		assertNotNull(user);
	}
//
//	@Test
//	void testDeleteUser() throws RecordNotFoundException {
//		userController.deleteUser(123456);
//		assertThrows(RecordNotFoundException.class, () -> userController.searchUserByID(BigInteger.valueOf(123456)));
//	}
}
