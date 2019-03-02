/**
 * 
 */
package com.flowers.microservice.logging.loggly.client;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * @author cgordon
 * @created 12/11/2017
 * @version 1.0
 *
 * Loggly REST interface, used internally by Retrofit
 *
 * @since 1.0.3
 */
interface ILogglyRestService {

  /**
     * Posts a single log event to Loggly's REST endpoint
     * @param token Loggly customer token
     * @param tags CSV of tags
     * @param message log event to be posted
     * @return result of the post as a {@link com.github.tony19.loggly.LogglyResponse}
     */
    @POST("inputs/{token}")
    Call<LogglyResponse> log(@Path("token") String token, @Header("X-LOGGLY-TAG") String tags, @Body String message);

    /**
     * Posts a single log event to Loggly's REST endpoint
     * @param token Loggly customer token
     * @param tags CSV of tags
     * @param message log event to be posted
     * @param callback callback to be invoked on completion of the post
     */
    @POST("inputs/{token}")
    Call<Void> log(@Path("token") String token, @Header("X-LOGGLY-TAG") String tags, @Body String message, Callback<LogglyResponse> callback);

    /**
     * Posts several log events at once to Loggly's bulk REST endpoint
     * @param token Loggly customer token
     * @param tags CSV of tags
     * @param messages log event messages, each delimited by new-line
     *                 The text is parsed for a log event in each line.
     *                 e.g., "Hello\nWorld" would create two log events.
     * @return result of the post as a {@link com.github.tony19.loggly.LogglyResponse}
     */
    @POST("bulk/{token}")
    Call<LogglyResponse> logBulk(@Path("token") String token, @Header("X-LOGGLY-TAG") String tags, @Body String messages);

    /**
     * Posts several log events at once to Loggly's bulk REST endpoint
     * @param token Loggly customer token
     * @param tags CSV of tags
     * @param messages log event messages, each delimited by new-line
     *                 The text is parsed for a log event in each line.
     *                 e.g., "Hello\nWorld" would create two log events.
     * @param callback callback to be invoked on completion of the post
     */
    @POST("bulk/{token}")
    Call<Void> logBulk(@Path("token") String token, @Header("X-LOGGLY-TAG") String tags, @Body String messages, Callback<LogglyResponse> callback);
}