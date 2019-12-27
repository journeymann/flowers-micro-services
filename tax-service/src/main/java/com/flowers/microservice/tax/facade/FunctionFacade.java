package com.flowers.microservice.tax.facade;

import java.lang.annotation.Annotation;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonRootName;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.flowers.microservice.beans.Model;
import com.flowers.microservice.beans.NullObject;
import static com.flowers.microservice.constants.GlobalConstants.DATEFORMAT_ISO8601;
import com.flowers.microservice.constants.TimeZones;
import com.flowers.microservice.tax.exception.Handler;
import com.google.json.JsonSanitizer;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  08/02/2017
 * @version 1.0
 * 
 * facade design pattern implementation with various functions used throughout application.
 *
 */
public class FunctionFacade {

	private static final DateFormat iso8601DateFormatter = new SimpleDateFormat(DATEFORMAT_ISO8601, Locale.ENGLISH);
	
	/**
	 * This method prints a given <code>Map</code> as a string.  
	 * 
	 * @param map accepts a <code>Map<String, String></code> as input to be formatted as a <code>String</code> 
	 * @returns The string <code>String</code> representation of the Set data type
	 */    
	public static String printMap(@NotNull final Map<String, String> map) {
		return Arrays.toString(map.entrySet().toArray());
	}

	/**
	 * Stream API implementation of filter for a given source list collection <code>List</code> of input values.
	 * (Generalized Target-Type Inference <T> is used)
	 * 
	 * @param predicate <code>Predicate</code> specifying filter conditions
	 * @param source data list <code>List</code> to be filtered based on predicate <code>Predicate</code>
	 * @return <code>List</code> resulting list of values that satisfy predicate conditions 
	 */
	public static <T> List<T> filter(@NotNull final Predicate<T> predicate, @NotNull final List<T> source) {
		return source.stream().filter(predicate).collect(Collectors.toList());
	}

	/**
	 * This method prints a given <code>List</code> as a string.  
	 * 
	 * @param list accepts a <code>List<?></code> as input to be formatted as a <code>String</code> 
	 * @returns The string <code>String</code> representation of the Set data type
	 */	
	public static String printList(@NotNull final List<?> list) {
		return Arrays.toString(list.toArray());
	}

	/**
	 * This method prints a given <code>Set</code> as a string.  
	 * 
	 * @param set accepts a <code>Set</code> as input to be formatted as a <code>String</code> 
	 * @returns The string <code>String</code> representation of the Set data type
	 */	
	public static String printSet(@NotNull final Set<?> set) {
		return Arrays.toString(set.toArray());
	}

	/**
	 * This method prints a given <code>Map</code> as a string.  
	 * 
	 * @param map accepts a <code>Map<String, String></code> as input to be formatted as a <code>String</code> 
	 * @returns The json string <code>String</code> representation of the Map data type
	 */	
	public static String printJsonMap(@NotNull final Map<String, ?> map) {

		return Handler.unchecked(() -> new ObjectMapper().writeValueAsString(map)).get();	
	}

	/**
	 * This method converts a date string <code>String</code> into a ISOP 8601 formatted string viz. "yyyy-MM-dd'T'HH:mm'Z'" 
	 * 
	 * @param inputDateAsString the input date as a <code>String</code>  
	 * @param inputStringFormat date format <code>String</code> e.g. "mm-dd-yyyy" 
	 * @returns the string <code>String</code> representation of the date in ISO 8601 format
	 */	
	public static String formatDateAsIso8601(final @NotNull String inputDateAsString, final @NotNull String inputStringFormat) throws ParseException {

		iso8601DateFormatter.setTimeZone(TimeZone.getTimeZone(TimeZones.UTC));

		final DateFormat inputDateFormatter = new SimpleDateFormat(inputStringFormat, Locale.ENGLISH);
		final Date inputDate = inputDateFormatter.parse(inputDateAsString);

		return iso8601DateFormatter.format(inputDate);
	}	

	/**
	 * This method converts a date <code>Date</code> object into a ISOP 8601 formatted string viz. "yyyy-MM-dd'T'HH:mm'Z'" 
	 * 
	 * @param date the input date object <code>Date</code> i.e. (java.util.Date)  
	 * @returns the string <code>String</code> representation of the date in ISO 8601 format
	 */	
	public static String formatDateAsIso8601(final @NotNull Date date) throws ParseException {

		iso8601DateFormatter.setTimeZone(TimeZone.getTimeZone(TimeZones.UTC));

		return iso8601DateFormatter.format(date);
	}	

	/**
	 * This method performs custom model object validation and also uses the Owasp library to encode and sanitize
	 * the data that is to be pushed to the Microservice REST service.
	 * Uses the NullObject {Model} design pattern to eliminate potential null errors etc.   
	 * 
	 * @param model input data as a subclass of <code>Model</code> superclass  
	 * @return the <code>String</code> description for <code>Model</code> object cleansed xml content 
	 */
	public static String getJsonModeltoString(@NotNull final Object model){

		return (model instanceof Model) ? JsonSanitizer.sanitize(Handler.unchecked(() -> new ObjectMapper().writeValueAsString(model)).get()) :
			model.toString();
	}	

	/**
	 * This method performs custom model object validation and also uses the Owasp library to encode and sanitize
	 * the data that is to be pushed to the Microservice REST service.
	 * Uses the NullObject {Model} design pattern to eliminate potential null errors etc.   
	 * 
	 * @param model input data as a subclass of <code>Model</code> superclass
	 * @param boolean flag sanitize optional    
	 * @return the <code>String</code> description for <code>Model</code> object cleansed xml content 
	 */
	public static String getJsonModeltoString(@NotNull final Object model, boolean flag){

		return (flag && model instanceof Model) ? JsonSanitizer.sanitize(Handler.unchecked(() -> new ObjectMapper().writeValueAsString(model)).get()) :
			model.toString();
	}	
	
	/**
	 * 
	 * Returns a the value of the JsonRootname annotation associated with the given <code>Object</code>
	 * 
	 * @param object input data <code>Object</code> from which to extract the json root name annotation  
	 * @return the <code>String</code> json root name annotation value for the given <code>Object</code> object  
	 */
	public static String getJsonRootValue(@NotNull final Object object){

		List<Annotation> annotations = Arrays.asList(object.getClass().getAnnotations());

		return Handler.unchecked(() -> ((JsonRootName)(Annotation)annotations.stream().filter(a -> a instanceof JsonRootName).findFirst()
				.orElse(NullObject.class.getAnnotation(JsonRootName.class))).value()).get();
	}	

	/**
	 * Stream API implementation of Utility function that performs the map transform operation on a list List<T> of one type of
	 * objects to a list List<R> of another unrelated type. (Generalized Target-Type Inference <T,R> is used)
	 * 
	 * <p>
	 * <i>usage: </i> map(myObject::getField, myHashtable)
	 * <i>usage: </i> map(() -> myObject.getField(), myHashtable) 
	 * 
	 * @param function mapping function <code>Function<T,R></code> @see java.util.Function
	 * @param source <code>List<T></code> list
	 * @return transformed list <code>List<R></code>
	 */
	public static <T,R> List<R> map(@NotNull final Function<T,R> function, @NotNull final List<T> source){

		return source.stream().map(function).collect(Collectors.toList());
	}	
	
	/**
	 * This function binds the input data generic type T to the input function 
	 * <code>Function</code>
	 * 
	 * <p>
	 * <i>usage: </i> bind(myObject::getField, "message: ")
	 * 
	 * @param fn function <code>Function</code> defining the operation
	 * @param val input generic type value
	 * @return <code>Supplier</code> data bound function
	 */
	public static <T, R> Supplier<R> bind(@NotNull final Function<T,R> fn, @NotNull final T val) {
	    return () -> fn.apply(val);
	}
	
	/**
	 * Java Function that takes a function <code>Function</code> as predicate and 
	 * performs the operation defined by the parameter function on the 
	 * <code>Map</code> collection keySet. 
	 * 
	 * <p>
	 * <i>usage: </i> transformKey(myHashtable, String::toLowercase);<br> 
	 * <i>usage: </i> transformKey(myHashtable, () -> String.toLowercase()); 
	 * 
	 * @param input map <code>Map</code> collection 
	 * @param function <code>Function</code> defining the operation
	 * @return map <code>Map</code> with output values 
	 */
	public static <X, Y, Z> Map<Z, Y> transformKey(@NotNull final Map<? extends X, ? extends Y> input,
			@NotNull final Function<X, Z> function) {
	    return input.keySet().parallelStream()
	        .collect(Collectors.toMap(key -> function.apply(key),
	                                  key -> input.get(key)));
	}    	
	
	/**
	 * Java Function that takes a function <code>Function</code> as predicate and 
	 * performs the operation defined by the parameter function on the 
	 * <code>Map</code> collection Values. 
	 *
	 * <p>
	 * <i>usage: </i> transformValue(myHashtable, String::toLowercase); <br> 
	 * <i>usage: </i> transformValue(myHashtable, () -> String.toLowercase()); 
	 * 
	 * @param input map <code>Map</code> collection 
	 * @param function <code>Function</code> defining the operation
	 * @return map <code>Map</code> with output values 
	 */
	public static <X, Y, Z> Map<X, Z> transformValue(@NotNull final Map<? extends X, ? extends Y> input,
			@NotNull final Function<Y, Z> function) {
	    return input.keySet().parallelStream()
	        .collect(Collectors.toMap(Function.identity(),
	                                  key -> function.apply(input.get(key))));
	}
	
	/**
	 * Method useful for solving the age old problem of copying collection objects that java refuses to do 'naturally'.
	 * 
	 * @param list <code>List</code> of objects.
	 * @param targetCollection
	 */
    public static <T> void copyListElements(final @NotNull List<T> list, @NotNull Supplier<Collection<T>> targetCollection) {
        list.forEach(targetCollection.get()::add);
    }	
	
    /**
     * Allows bulk cast conversion of list of related objects
     * 
     * @param list input <code>List</code> of objects
     * @param klass Target class to bulk convert list classes to.
     * @return list <code>List</code> of converted elements
     */
    public static <T,S> List<T> convertModelList(@NotNull final List<S> list, @NotNull final Class<T> klass) {
    	
    	return FunctionFacade.map(s -> klass.cast(s), list);
    }
    
    /**
     * Initializes a List collection of any type. 
     * 
     * @param list <code>List</code> initialized of type <T> generic
     */
    public static <T> void initializeModelList(@NotNull List<T> list) {
    	
        copyListElements(list, () -> new ArrayList<T>());
    }
    
    /**
     * Sorts a given list <code>List</code> by a parameter provided sort function
     * 
	 * <p>
	 * <i>usage: </i> sortListByFunction([myArrayList<Attribute>], Attribute::getFirst_name); <br> 
     *
     * @param models <code>List</code> objects of any type
     * @param function <code>Function</code> defining the rule operation to apply in order to sort the data 
     * @return list <code>List</code> collection of sorted objects
     */
	public static <T> List<T> sortListByFunction(@NotNull final List<T> list, @NotNull final Function<T, String> function) {	
		
    	return list.stream().sorted(Comparator.comparing(function)).collect(Collectors.toList());
	}    
}