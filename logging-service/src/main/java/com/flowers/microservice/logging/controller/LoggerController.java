/**
 * 
 */
package com.flowers.microservice.logging.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import javax.validation.Valid;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

@RestController
@RequestMapping(path = "/logger")
public class LoggerController {

	private static transient final Logger logger = LoggerFactory.getLogger(LoggerController.class);
	
    @HystrixCommand(fallbackMethod = "fallback")
	@RequestMapping(path = "/log", method = RequestMethod.POST)
	public void setLogEntity(@RequestParam(value = "code", required = false) @Valid String code,
			@Valid @RequestParam(value = "message", required = false) String message) {

		logger.info("code: {} , message; {} ", code, message);
	}
    
    public void fallback() {
    	logger.info("code: {} , message; {} ", "000", "Default Message");
    }
    
}