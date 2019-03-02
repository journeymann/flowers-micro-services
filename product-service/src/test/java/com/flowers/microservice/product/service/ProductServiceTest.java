package com.flowers.microservice.product.service;

import com.flowers.microservice.product.client.ProductServiceClient;
import com.flowers.microservice.product.client.StatisticsServiceClient;
import com.flowers.microservice.product.domain.*;
import com.flowers.microservice.product.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class ProductServiceTest {

	@InjectMocks
	private ProductServiceImpl productService;

	@Mock
	private StatisticsServiceClient statisticsClient;

	@Mock
	private ProductServiceClient authClient;

	@Mock
	private ProductRepository repository;

	@Before
	public void setup() {
		initMocks(this);
	}

	@Test
	public void shouldFindByName() {

		final Product product = new Product();
		product.setName("test");

		when(productService.findProductByName(product.getName())).thenReturn(product);
		Product found = productService.findProductByName(product.getName());

		assertEquals(product, found);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailWhenNameIsEmpty() {
		productService.findProductByName("");
	}

	@Test
	public void shouldSaveChangesWhenUpdatedProductGiven() {

		Item item = new Item();
		
		item.setSku("1001-P-GFDT1325");
		item.setDescription("My Funny Valentines");
		item.setName("Valentines Roses");
		item.setPrice(12.00);
		item.setAvailableDate("01-03-2018");
		item.setHeight(2);
		item.setLength(3);
		item.setWidth(5);
		item.setWeight(12);

		Product create = new Product();
		create.setName("Funny Valentine Roses, its so cute <3");
		create.setItems(Arrays.asList(item));
		
		create = productService.create(create);
		
		Product update = new Product();
		
		item = new Item();
		item.setSku("1001-P-GFDT1325");
		item.setDescription("My Funny Valentines, its is really cute <3");
		item.setName("My Funny Valentines Roses");
		item.setPrice(12.00);
		item.setAvailableDate("01-03-2018");
		item.setHeight(2);
		item.setLength(3);
		item.setWidth(5);
		item.setWeight(12);

		when(productService.findProductByName("Funny Valentine Roses, its so cute <3")).thenReturn(create);
		productService.update(String.valueOf(create.getProductId()), update);

		assertEquals(update.getName(), create.getName());
		assertNotNull(update.getProductId());
		verify(repository, times(1)).save(create);
		verify(statisticsClient, times(1)).updateStatistics("test", update);
	}

	@Test(expected = IllegalArgumentException.class)
	public void shouldFailWhenNoProductsExistedWithGivenName() {
		final Product update = new Product();
		update.setItems(Arrays.asList(new Item()));

		when(productService.findProductByName("test")).thenReturn(null);
		productService.create(update);
	}
}
