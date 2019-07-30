/**
 * 
 */
package com.flowers.microservice.tax.exception;

import java.util.concurrent.Callable;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  10/12/2017 
 * @version 1.0
 * 
 *  <p>
 *  
 *  This class implements the exception tunneling paradigm and is a solution to the scenario where lambda expressions
 *  throw exceptions. It allows for the continued readability and conciseness that lambda expressions bring to the 
 *  java programming language.   
 *  
 *  @see <a href="http://wiki.c2.com/?ExceptionTunneling">Exception Tunneling</a>
 *    
 *  <p>
 */
public class Handler {

	private static transient final Logger logger = LoggerFactory.getLogger(Handler.class);	

	/**
	 * This method accepts a <code>Callable</code> type value that contains a method call data, and calls the method and handles any
	 * exceptions that may be thrown by the method (lambda) expression. (Generalized Target-Type Inference <T> is used) 
	 * 
	 * @param Callable function/method <code>Callable</code>
	 * @return Supplier <code>Supplier</code> type containing the return value
	 */
	public static <T> Supplier<T> unchecked(Callable<T> f) {
		   return () -> {
		      try {
		         return f.call();
		      }
		      catch (Exception e) {
					logger.error("Unchecked Exception has occured:, {}", e);
		         throw new RuntimeException(e);
		      }
		      catch (Throwable t) {
					logger.error("Checked exception has occured:, {}", t);
		         throw t;
		      }
		   };
		}

	/**
	 * This method accepts a <code>Callable</code> type value that contains a method call data, and calls the method and handles any
	 * exceptions that may be thrown by the method (lambda) expression.   
	 * 
	 * @param Callable function/method <code>Callable</code>
	 */
	public static void uncheckedVoid(Callable<?> f) {

		Handler.unchecked(f);		
	}	
	
}