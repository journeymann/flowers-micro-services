package com.flowers.microservice.inventory;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.flowers.microservice.inventory.InventoryApplication;
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
public class ProductServiceApplicationTests {

	@Test
	public void contextLoads() {

	}

}
