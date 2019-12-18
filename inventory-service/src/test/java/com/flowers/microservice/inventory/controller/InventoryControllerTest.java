package com.flowers.microservice.inventory.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowers.microservice.inventory.InventoryApplication;
import com.flowers.microservice.inventory.controller.InventoryController;
import com.flowers.microservice.inventory.domain.*;
import com.flowers.microservice.inventory.service.InventoryService;
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
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InventoryApplication.class)
@WebAppConfiguration
public class InventoryControllerTest {

	private static final ObjectMapper mapper = new ObjectMapper();

	@InjectMocks
	private InventoryController inventoryController;
	@Mock
	private InventoryService inventoryService;

	private MockMvc mockMvc;

	@Before
	public void setup() {
		initMocks(this);
		this.mockMvc = MockMvcBuilders.standaloneSetup(inventoryController).build();
	}

	@Test
	public void shouldGetProductByName() throws Exception {

		final Product product = new Product();
		final Inventory inventory = new Inventory();

		product.setName("test");

		when(inventoryService.findProductCountById(product.getProductId())).thenReturn(inventory);

		mockMvc.perform(get("/" + product.getName()))
				.andExpect(jsonPath("$.name").value(product.getName()))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldGetCurrentProduct() throws Exception {

		final Product product = new Product();
		final Inventory inventory = new Inventory();

		product.setName("test");

		when(inventoryService.findProductCountById(product.getProductId())).thenReturn(inventory);

		mockMvc.perform(get("/current").principal(new UserPrincipal(product.getName())))
				.andExpect(jsonPath("$.name").value(product.getName()))
				.andExpect(status().isOk());
	}

	@Test
	public void shouldSaveCurrentProduct() throws Exception {

		
		final Product update = new Product();
		final Inventory inventory;
		final String productid = "1001-DF-1234";
		final Long count = 145L;
		
		update.setName("test");
		update.setProductId(productid);
		inventory = inventoryService.updateProductCount(productid, count);

		when(inventoryService.findProductCountById(update.getProductId())).thenReturn(inventory);
		
		String json = mapper.writeValueAsString(inventory);

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
