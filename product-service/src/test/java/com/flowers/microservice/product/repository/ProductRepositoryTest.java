package com.flowers.microservice.product.repository;

import com.flowers.microservice.product.ProductApplication;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductApplication.class)
public class ProductRepositoryTest {

	@SuppressWarnings("unused")
	@Autowired
	private ProductRepository repository;

}
