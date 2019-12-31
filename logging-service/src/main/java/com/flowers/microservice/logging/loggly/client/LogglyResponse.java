/**
 * 
 */
package com.flowers.microservice.logging.loggly.client;

import java.io.Serializable;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 *
 * The response from Loggly's REST endpoints, which is a
 * JSON object, containing only a "response" key and its
 * value (normally equal to "ok" for success).
 *
 */
class LogglyResponse implements Serializable {

	private static final long serialVersionUID = -4517632156175411177L;

	/** Response value for success */
    public static final String SUCCESS_VALUE = "ok";

    /** A {@code LogglyResponse} that indicates success */
    public static final LogglyResponse OK = new LogglyResponse(SUCCESS_VALUE);

    /** Value of response. Assigned by deserializer. */
    private String response;

    /**
     * Constructs a blank {@code LogglyResponse}
     */
    public LogglyResponse() {
    }

    /**
     * Constructs a {@code LogglyResponse} for internal testing
     * @param text desired text value of response
     */
    LogglyResponse(String text) {
        this.response = text;
    }

    /**
     * Gets the text value of the response
     * @return the response as a string
     */
    public String getText() {
        return response;
    }

    /**
     * Determines whether the response indicates success
     * @return {@code true} if the result is success; {@code false} otherwise
     */
    public boolean isOk() {
        return SUCCESS_VALUE.equals(response);
    }
}