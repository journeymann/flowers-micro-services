/**
 * 
 */
package com.flowers.microservice.auth.service;


import com.flowers.microservice.auth.domain.User;
import com.flowers.microservice.auth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@Service
public class UserServiceImpl implements UserService {

	private final Logger log = LoggerFactory.getLogger(getClass());

	private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

	@Autowired
	private UserRepository repository;

	public User create(User user) {

		User existing = repository.findOne(user.getUsername());
		Assert.isNull(existing, "user already exists: " + user.getUsername());

		String hash = encoder.encode(user.getPassword());
		user.setPassword(hash);

		repository.save(user);

		log.info("new user has been created: {}", user.getUsername());
		
		return user;
	}

	/**
	 * {@inheritDoc}
	 */
	@SuppressWarnings("deprecation")
	@Override
	public User findByName(String authName) {
		Assert.hasLength(authName);
		return repository.findOne(authName);
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public void saveChanges(String name, User update) {

		User auth = repository.findOne(name);
		Assert.notNull(auth, "can't find auth with name " + name);
		
		auth.setUsername(update.getUsername());
		auth.setPassword(update.getPassword());

		repository.save(auth);

		log.debug("auth {} changes has been saved", name);
	}
}
	
