package com.flowers.microservice.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowers.microservice.auth.AuthApplication;
import com.flowers.microservice.auth.domain.*;
import com.flowers.microservice.auth.service.UserService;
import com.sun.security.auth.UserPrincipal;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("restriction")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = AuthApplication.class)
@WebAppConfiguration
public class AuthControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	private UserController authController;

	@Mock
	private UserService authService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(authController).build();
	}

	@Test
	public void shouldGetAuthByName() throws Exception {

		final User auth = new User();
		auth.setUsername("testuser");

		when(authService.findByName(auth.getUsername())).thenReturn(auth);

		mockMvc.perform(get("/" + auth.getUsername()))
				.andExpect(jsonPath("$.name").value(auth.getUsername()))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldGetCurrentAuth() throws Exception {

		final User auth = new User();
		auth.setUsername("testuser");

		when(authService.findByName(auth.getUsername())).thenReturn(auth);

		mockMvc.perform(get("/current").principal(new UserPrincipal(auth.getUsername())))
				.andExpect(jsonPath("$.name").value(auth.getUsername()))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldFailOnValidationTryingToSaveCurrentAuth() throws Exception {

		final User auth = new User();
		auth.setUsername("testuser");

		String json = mapper.writeValueAsString(auth);

		mockMvc.perform(put("/current").principal(new UserPrincipal(auth.getUsername())).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void shouldRegisterNewAuth() throws Exception {

		final User user = new User();
		user.setUsername("test");
		user.setPassword("password");

		String json = mapper.writeValueAsString(user);
		System.out.println(json);
		mockMvc.perform(post("/").principal(new UserPrincipal("test")).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldFailOnValidationTryingToRegisterNewAuth() throws Exception {

		final User user = new User();
		user.setUsername("t");

		String json = mapper.writeValueAsString(user);

		mockMvc.perform(post("/").principal(new UserPrincipal("test")).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest());
	}
}
