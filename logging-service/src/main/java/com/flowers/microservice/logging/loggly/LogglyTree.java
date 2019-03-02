/**
 * 
 */
package com.flowers.microservice.logging.loggly;

import com.flowers.microservice.logging.loggly.client.ILogglyClient;
import com.flowers.microservice.logging.loggly.client.LogglyClient;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 * log messages to <a href="http://loggly.com">Loggly</a>
 */
public class LogglyTree {

    private ILogglyClient loggly;
    private ILogglyClient.Callback handler;
    private IFormatter formatter;

    /**
     * tree for posting messages to <a href="http://loggly.com">Loggly</a>
     * @param token Loggly token from https://www.loggly.com/docs/customer-token-authentication-token/
     */
    public LogglyTree(String token) {
        loggly = new LogglyClient(token);
        handler = new DummyCallback();
        formatter = new JsonFormatter();
    }

    /**
     * tree for posting messages to <a href="http://loggly.com">Loggly</a>
     * @param client Loggly client
     * @param handler Loggly-post result handler
     * @param formatter log formatter
     */
    LogglyTree(ILogglyClient client, ILogglyClient.Callback handler, IFormatter formatter) {
        this.loggly = client;
        this.handler = handler;
        this.formatter = formatter;
    }

    /**
     * Writes a log message to its destination. Called for all level-specific methods by default.
     *
     * @param priority Log level. 
     * @param tag Explicit or inferred tag. May be {@code null}.
     * @param message Formatted log message. May be {@code null}, but then {@code t} will not be.
     * @param t Accompanying exceptions. May be {@code null}, but then {@code message} will not be.
     */
    protected void log(int priority, String tag, String message, Throwable t) {
        loggly.log(formatter.format(priority, tag, message, t), handler);
    }

    /**
     * Sets the Loggly tag for all logs going forward
     * @param tag desired tag or CSV of multiple tags; use empty string
     *            to clear tags
     */
    public void tag(String tag) {
        loggly.setTags(tag);
    }
}