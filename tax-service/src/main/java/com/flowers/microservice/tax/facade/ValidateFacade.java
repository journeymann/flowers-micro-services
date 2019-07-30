package com.flowers.microservice.tax.facade;



	import java.util.Arrays;
	import java.util.HashMap;
	import java.util.HashSet;
	import java.util.Map;
	import java.util.Optional;
	import java.util.Set;

	/**
	 * @author cgordon
	 * @created 04/11/2017
	 * @version 1.2
	 * 
	 * <p>
	 * This Java class contains primarily an isvalid() function that is intended to perform validation of regular string types viz
	 * 
	 *  validation type constants:
	 *  
	 *       Validator.USERNAME
	 *       Validator.URL
	 *       Validator.PASSWORD
	 *       Validator.IPADDRESS
	 *       Validator.EMAIL       
	 *       Validator.HTMLTAG 
	 *       Validator.STRONG_PASSWORD *enforces password complexity pattern as described below.             
	 *  <p>
	 *  sample usage:
	 *  
	 *  <pre>
	 *  {@code 
	 *  	String email = "cgordon@email.com";
	 *   	Validator util = new Validator();
	 *   
	 *  	if(util.isvalid(email, Validator.EMAIL)) 
	 *  			return true;
	 *  }
	 *  </pre>
	 *  
	 *  <p>
	 *  There are also convenience methods where the operation is inferred by the method name
	 *  <pre>
	 *  {@code 
	 *  	String email = "cgordon@email.com";
	 *   	Validator util = new Validator();
	 *   
	 *  	if(util.isvalidEmail(email)) 
	 *  			return true;
	 *  }
	 *  </pre>
	 *  <p>
	 *  
	 *  Details about the implementation is discussed below so that modification to the code is simplified.
	 *  This implementation uses regular expressions extensively in order to perform the validations. Escape characters had to be escaped for the eclipse editor to understand them. 
	 *  
	 *  User-name validation requires at least 3 alphabet characters and total length is limited to 16 alphanumeric characters. User-name must be lower case characters
	 *  Password validation requires at least 6 alphabet characters and total length is limited to 18 alphanumeric characters. Password is not case sensitive. 
	 *  <p>
	 *  Performance is always a concern especially for these types of use functions as they tend to be called extensively and repeatedly in production environments. 
	 *  Memory usage and process is optimized to O(1) by using Set and HashMap for input validation and retrieving the appropriate regular expression.
	 *  Variables use the minimum memory footprint (byte, short etc) as needed and core (java.lang) functions are used to perform validation processing.  
	 *  
	 *  The implementation was designed and complied using java 1.8.
	 *  <p>
	 */
	public class ValidateFacade {
		
		/** Statically scoped memory efficient validation type constants */
		public static final byte USERNAME = (byte)100; 
		public static final byte PASSWORD = (byte)101;
		public static final byte EMAIL = (byte)102;
		public static final byte URL = (byte)103;
		public static final byte IPADDRESS = (byte)104;
		public static final byte HTMLTAG = (byte)105;
		public static final byte STRONG_PASSWORD = (byte)106;	
		
		/**
		 * Predefined regular expressions constants for rule based validating various types of input strings. 
		 * @see <a href="https://docs.oracle.com/javase/tutorial/essential/regex/">Lesson: Regular Expressions (The Java<sup>tm</sup> Tutorials > Esential Classes (Oracle)</a>
		 * 
		 * */
		private static final String REGEXP_USERNAME = "/^[a-z0-9_-]{3,16}$/";
		private static final String REGEXP_PASSWORD = "/^[a-zA-Z0-9_-$#@!]{6,18}$/";
		private static final String REGEXP_EMAIL = "/^([a-z0-9_\\.-]+)@([\\da-z\\.-]+)\\.([a-z\\.]{2,6})$/";
		private static final String REGEXP_URL = "/^(https?|ftp)://[^\\s/$.?#].[^\\s]*$/";
		private static final String REGEXP_IPADDRESS = "/^(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/";
		private static final String REGEXP_HTMLTAG = "/^<([a-z]+)([^<]+)*(?:>(.*)<\\/\\1>|\\s+\\/>)$/";
		
		/**
		 *     (?=.*\d)		#   must contains one digit from 0-9
		 *     (?=.*[a-z])		#   must contains one lowercase characters
		 *     (?=.*[A-Z])		#   must contains one uppercase characters
		 *     (?=.*[@#$%])		#   must contains one special symbols in the list "@#$%"
	 	 *     match anything with previous condition checking{6,20} length at least 6 characters and maximum of 20
		 */		
		private static final String REGEXP_STRONG_PASSWORD = "((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";	
		
		private static final Set<Byte> validTypes = new HashSet<Byte>(Arrays.asList(USERNAME, PASSWORD, EMAIL, IPADDRESS, HTMLTAG, STRONG_PASSWORD));
		
		@SuppressWarnings("serial")
		private static final Map<Byte, String> expressions = new HashMap<Byte, String>() {
	          {
	            put(USERNAME, REGEXP_USERNAME);
	            put(PASSWORD, REGEXP_PASSWORD);
	            put(EMAIL, REGEXP_EMAIL);
	            put(URL, REGEXP_URL);
	            put(IPADDRESS, REGEXP_IPADDRESS);
	            put(HTMLTAG, REGEXP_HTMLTAG);
	            put(STRONG_PASSWORD, REGEXP_STRONG_PASSWORD);            
	          };
	        };	
	    
		/**
		 * Validates a given string based on predefined rules.
		 * Heavy lifting and rule enforcement done via regular expressions.
		 * 
		 * See {@link java.util.Optional#of#isPresent()) Optional.isPresent()} for java 1.8 feature to validate input object param not null.
		 * See {@link java.lang.String#matches(String) String.matches(regex)} for string <String> regex processing. 
		 * 
		 * @param String <String> literal to be validated.
		 * @param Constant (byte primitive) representing the type of validation to be performed.
		 * @return boolean primitive flagging success of the invalid operation  
		 * @throws none
		 */
		public static boolean isvalid(String _string, byte _type){
			
			return Optional.of(_string).isPresent() && 
					validTypes.contains(_type) && _string.matches((String)expressions.get(_type));
		}
		
		/**
		 * Validates a given string based on rules (regular expression) provided via parameter passed
		 * Heavy lifting and rule enforcement done via regular expressions.
		 * 
		 * See {@link java.util.Optional#of#isPresent()) Optional.isPresent()} for java 1.8 feature to validate input object param not null.
		 * See {@link java.lang.String#matches(String) String.matches(regex)} for string <String> regex processing. 
		 *  
		 * @param String <String> literal to be validated.
		 * @param String <String> regular expression to be used to validate input <String>
		 * @return boolean primitive flagging success of the invalid operation 
		 * @throws none
		 */
		public static boolean isvalid(String _string, String _expression){
			
			return Optional.of(_string).isPresent() && _string.matches(_expression);
		}
		
		/**
		 * Convenience method that validates a given user-name string based on predefined rules.
		 * 
		 * Use {@link #isvalid(String _string, byte _type) isvalid()} to perform username validation 
		 * 
		 * @param String <String> user name to be validated.
		 * @return boolean primitive flagging success of the invalid operation  
		 * @throws none
		 */
		public static boolean isvalidUsername(String _string){
			
			return isvalid(_string, USERNAME);
		}
		
		/**
		 * Convenience method that validates a email address string based on predefined rules.
		 * 
		 * Use {@link #isvalid(String _string, byte _type) isvalid()} to perform email validation 
		 * 
		 * @param String <String> email address to be validated.
		 * @return boolean primitive flagging success of the invalid operation  
		 * @throws none
		 */
		public static boolean isvalidEmail(String _string){
			
			return isvalid(_string, EMAIL);
		}
		
		/**
		 * Convenience method that validates a given password string based on predefined rules.
		 * Rule preference used is regular expression for validating strong passwords
		 * 
		 * Use {@link #isvalid(String _string, byte _type) isvalid()} to perform password validation 
		 * 
		 * @param String <String> literal to be validated.
		 * @return boolean primitive flagging success of the invalid operation  
		 * @throws none
		 */
		public static boolean isvalidPassword(String _string){
			
			return isvalid(_string, STRONG_PASSWORD);
		}
		
		/**
		 * Convenience method that validates a given html tag string based on predefined rules.
		 * 
		 * Use {@link #isvalid(String _string, byte _type) isvalid()} to perform html tag validation 
		 * 
		 * @param String <String> literal to be validated.
		 * @return boolean primitive flagging success of the invalid operation  
		 * @throws none
		 */
		public static boolean isvalidHtmlTag(String _string){
			
			return isvalid(_string, HTMLTAG);
		}
		
	}