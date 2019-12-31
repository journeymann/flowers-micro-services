package com.flowers.microservice.account.repository;

import com.flowers.microservice.account.domain.Account;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Repository
public interface AccountRepository extends CrudRepository<Account, String> {

	@HystrixCommand(fallbackMethod = "reliable")
	default public Account findByName(String name){
		
		List<Account> accounts = (List<Account>) findAll();
		
		return accounts.stream().filter(account -> account.getName().equals(name)).findFirst().orElseGet(Account::new);
	};


	public default Account reliable() {
		Account account = new Account();
		account.setName("Sanjay Shukla");
		return account;
	}
	
}
