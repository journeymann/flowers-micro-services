package com.flowers.microservice.account.service;

import com.flowers.microservice.account.domain.Account;
import com.flowers.microservice.account.domain.User;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

public interface AccountService {

	/**
	 * Finds account by given name
	 *
	 * @param accountName
	 * @return found account
	 */
	Account findByName(String accountName);

	/**
	 * Checks if account with the same name already exists
	 * Invokes Auth Service user creation
	 * Creates new account with default parameters
	 *
	 * @param user
	 * @return created account
	 */
	Account create(User user);

	/**
	 * Validates and applies incoming account updates
	 * Invokes Statistics Service update
	 *
	 * @param name
	 * @param update
	 */
	void saveChanges(String name, Account update);
}
