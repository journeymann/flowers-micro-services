package com.flowers.microservice.auth.service;

import com.flowers.microservice.auth.domain.*;
import com.flowers.microservice.auth.repository.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class AuthServiceTest {

	@InjectMocks
	private UserServiceImpl authService;

	@Mock
	private UserRepository repository;

	@Before
	public void setup() {
		initMocks(this);
	}

	@Test
	public void shouldFindByName() {

		final User auth = new User();
		auth.setUsername("testuser");

		when(authService.findByName(auth.getUsername())).thenReturn(auth);
		User found = authService.findByName(auth.getUsername());

		assertEquals(auth, found);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailWhenNameIsEmpty() {
		authService.findByName("");
	}

	@Test
	public void shouldCreateAccountWithGivenUser() {

		User user = new User();
		user.setUsername("test");

		User auth = authService.create(user);

		assertEquals(user.getUsername(), auth.getUsername());
		assertEquals(user.getPassword(), auth.getPassword());
		
		verify(repository, times(1)).save(auth);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailWhenNoAccountsExistedWithGivenName() {
		final User update = new User();
		
		
		update.setUsername(new String());
		update.setPassword(new String());

		when(authService.findByName("testuser")).thenReturn(null);
		authService.saveChanges("test", update);
	}
}
