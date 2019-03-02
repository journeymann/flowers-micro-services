package com.flowers.microservice.product.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableList;
import com.flowers.microservice.product.ProductApplication;
import com.flowers.microservice.product.domain.*;
import com.flowers.microservice.product.service.ProductService;
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
import java.util.Arrays;

import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SuppressWarnings("restriction")
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductApplication.class)
@WebAppConfiguration
public class ProductControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	private ProductController productController;

	@Mock
	private ProductService productService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
	}

	@Test
	public void shouldGetProductByName() throws Exception {

		final Product product = new Product();
		product.setName("test");

		when(productService.findProductByName(product.getName())).thenReturn(product);

		mockMvc.perform(get("/" + product.getName()))
				.andExpect(jsonPath("$.name").value(product.getName()))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldGetCurrentProduct() throws Exception {

		final Product product = new Product();
		product.setName("test");

		when(productService.findProductByName(product.getName())).thenReturn(product);

		mockMvc.perform(get("/current").principal(new UserPrincipal(product.getName())))
				.andExpect(jsonPath("$.name").value(product.getName()))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldSaveCurrentProduct() throws Exception {

		Item item1 = new Item();
		Item item2 = new Item();
		
		item1.setSku("1001-P-GFDT1325");
		item1.setDescription("My Funny Valentines");
		item1.setName("Valentines Roses");
		item1.setPrice(12.00);
		item1.setAvailableDate("01-03-2018");
		item1.setHeight(2);
		item1.setLength(3);
		item1.setWidth(5);
		item1.setWeight(12);

		item2.setSku("1001-P-GFDT1325");
		item2.setDescription("My Funny Valentines");
		item2.setName("Valentines Roses");
		item2.setPrice(12.00);
		item2.setAvailableDate("01-03-2018");
		item2.setHeight(2);
		item2.setLength(3);
		item2.setWidth(5);
		item2.setWeight(12);
		
		
		Product create = new Product();
		Product update = new Product();
		create.setName("Funny Valentine Roses, its so cute <3");
		create.setItems(Arrays.asList(item1, item2));
		
		update = productService.create(create);
		update.setName("test");
		update.setItems(ImmutableList.of(item1, item2));

		String json = mapper.writeValueAsString(update);

		mockMvc.perform(put("/current").principal(new UserPrincipal(update.getName())).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldFailOnValidationTryingToSaveCurrentProduct() throws Exception {

		final Product product = new Product();
		product.setName("test");

		String json = mapper.writeValueAsString(product);

		mockMvc.perform(put("/current").principal(new UserPrincipal(product.getName())).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void shouldRegisterNewProduct() throws Exception {

		final User user = new User();
		user.setUsername("test");
		user.setPassword("password");

		String json = mapper.writeValueAsString(user);
		System.out.println(json);
		mockMvc.perform(post("/").principal(new UserPrincipal("test")).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldFailOnValidationTryingToRegisterNewProduct() throws Exception {

		final User user = new User();
		user.setUsername("t");

		String json = mapper.writeValueAsString(user);

		mockMvc.perform(post("/").principal(new UserPrincipal("test")).contentType(MediaType.APPLICATION_JSON).content(json))
				.andExpect(status().isBadRequest());
	}
}
