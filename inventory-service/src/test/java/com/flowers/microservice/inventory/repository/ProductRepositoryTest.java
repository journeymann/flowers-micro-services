package com.flowers.microservice.inventory.repository;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.flowers.microservice.inventory.InventoryApplication;
import com.flowers.microservice.inventory.repository.InventoryRepository;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = InventoryApplication.class)
public class ProductRepositoryTest {

	@SuppressWarnings("unused")
	@Autowired
	private InventoryRepository repository;

}
