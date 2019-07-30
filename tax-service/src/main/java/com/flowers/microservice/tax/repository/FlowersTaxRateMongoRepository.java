/**
 * 
 */
package com.flowers.microservice.tax.repository;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

import com.flowers.microservice.tax.model.TaxRate;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2017
 * @version 1.0
 *
 */

@Repository
@RepositoryRestResource(exported = false)
public interface FlowersTaxRateMongoRepository extends MongoRepository<TaxRate, String> {

}

