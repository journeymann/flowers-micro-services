package com.flowers.microservice.tax.health;

import java.util.stream.Collectors;
import com.flowers.microservice.beans.TaxRate;
import com.flowers.microservice.common.FunctionFacade;
import com.flowers.microservice.tax.repository.FlowersTaxRateMongoRepository;
import com.flowers.microservice.tax.service.TaxRateService;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
public class InitialiseTest {

	
	   public static void init(FlowersTaxRateMongoRepository repository, TaxRateService service) { 
	    	
			repository.deleteAll();
			
			System.out.printf("Tx count found with count():-----%s\n",repository.count());
			
			// save a couple of TaxRate
			repository.save(new TaxRate("100000", "NY", "Kings County", "11236", 1.00, false));
			repository.save(new TaxRate("100001", "NY", "Kings County", "11219", 2.00, false));
			repository.save(new TaxRate("100002", "NY", "Queens", "11319", 1.01, false));
			repository.save(new TaxRate("100003", "NY", "Queens", "11320", 1.01, false));
			repository.save(new TaxRate("100004", "NY", "Columbia", "07932", 1.5, false));
			repository.save(new TaxRate("100005", "NY", "Columbia", "07519", 1.00, false));
			repository.save(new TaxRate("100006", "NY", "Newark County", "07132", 1.00, false));
			repository.save(new TaxRate("100007", "NY", "Newark County", "07312", 1.00, false));
			repository.save(new TaxRate("100008", "NY", "Fairfield County", "06519", 2.00, false));
			repository.save(new TaxRate("100009", "NY", "Fairfield County", "06519", 2.00, false));
			
			System.out.printf("TEST: (service) all data: taxs objects: %s \n", FunctionFacade.printList(service.findAllTaxRates()));
			
			// fetch all TaxRate
			System.out.printf("TaxRate found with findAll():-----%s\n",FunctionFacade.printList(repository.findAll()));

			// fetch an individual TaxRate
			System.out.printf("TaxRate found with tax id('B003C'): -----%s\n",repository.findById("B003C"));

			System.out.printf("TaxRate list found with tax name('Candy'): -----%s\n", FunctionFacade.printList(repository.findAll().stream().filter(p -> p.getCounty().contains("Kings"))
					.collect(Collectors.toList())));
	
			System.out.printf("TaxRate [EXPECT FAIL] found with tax id('N401B'):-----%s\n",repository.findById("N401B"));
			
			//calling service interface implementation 
			System.out.println("\n\nCalling service data interface methods\n");

			System.out.printf("TaxRate (service) found with findAll():-----%s\n",FunctionFacade.printList(service.findAllTaxRates()));

			// fetch an individual TaxRate
			System.out.printf("TaxRate (service) found with tax id('B003C'):-----%s\n", service.findTaxRateById("B003C"));

			System.out.printf("TaxRate (service) list found with tax name('Candy'):-----%s\n", service.findAllTaxRateNamesLike("Kings"));
	
			System.out.printf("TaxRate (service) [EXPECT FAIL] found with tax id('N401B'):-----%s\n", service.findTaxRateById("100002"));
	    }
}
