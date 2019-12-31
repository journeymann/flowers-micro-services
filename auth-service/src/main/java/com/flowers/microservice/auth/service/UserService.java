/**
 * 
 */
package com.flowers.microservice.auth.service;

import com.flowers.microservice.auth.domain.User;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

public interface UserService {

	/**
	 * Finds user by given name
	 *
	 * @param userName
	 * @return found account
	 */
	User findByName(String userName);

	/**
	 * Checks if user with the same name already exists
	 * Invokes Auth Service user creation
	 * Creates new user with default parameters
	 *
	 * @param user
	 * @return created user
	 */
	User create(User user);

	/**
	 * Validates and applies incoming user updates
	 * Invokes Statistics Service update
	 *
	 * @param name
	 * @param update
	 */
	void saveChanges(String name, User update);
}
