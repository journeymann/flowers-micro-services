/**
 * 
 */
package com.flowers.microservice.auth.repository;

import com.flowers.microservice.auth.domain.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Repository
public interface UserRepository extends CrudRepository<User, String> {

}