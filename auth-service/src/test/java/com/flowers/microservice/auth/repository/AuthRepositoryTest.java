package com.flowers.microservice.auth.repository;

import com.flowers.microservice.auth.AuthApplication;
import com.flowers.microservice.auth.domain.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthApplication.class)
public class AuthRepositoryTest {

	@Autowired
	private UserRepository repository;

	@Test
	public void shouldFindUserByName() {

		User stub = getStubAccount();
		repository.save(stub);

		User found = repository.findOne(stub.getUsername());
		assertEquals(stub.getUsername(), found.getUsername());
		assertEquals(stub.getPassword(), found.getPassword());
	}

	private User getStubAccount() {

		User account = new User();
		account.setUsername("testuser");
		account.setUsername("testuser@password");

		return account;
	}
}
