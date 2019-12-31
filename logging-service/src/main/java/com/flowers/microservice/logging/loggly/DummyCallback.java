/**
 * 
 */
package com.flowers.microservice.logging.loggly;

import com.flowers.microservice.logging.loggly.client.ILogglyClient;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 */

/**
 * Dummy callback for LogglyClient
 */
class DummyCallback implements ILogglyClient.Callback {
    /**
     * Function to be called when the log request was successfully sent to Loggly
     */
    @Override
    public void success() {
        // ignore
    }

    /**
     * Function to be called when the log request failed
     *
     * @param error message details about the failure
     */
    @Override
    public void failure(String error) {
        System.err.println("LogglyTree failed: " + error);
    }
}