package com.flowers.microservice.common;

import java.time.Duration;
import java.util.Arrays;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import io.micrometer.core.instrument.Clock;
import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.influx.InfluxConfig;
import io.micrometer.influx.InfluxMeterRegistry;

@Configuration
@EnableScheduling
@EnableCircuitBreaker
@EnableOAuth2Client
@EnableGlobalMethodSecurity(prePostEnabled = true)
@ComponentScan
public abstract class AbstractAppConfig {

    @Value("${config.oauth2.accessTokenUri}")
    protected String accessTokenUri;

    @Value("${config.oauth2.userAuthorizationUri}")
    protected String userAuthorizationUri;

    @Value("${config.oauth2.clientID}")
    protected String clientID;

    @Value("${config.oauth2.clientSecret}")
    protected String clientSecret;	

    /**
     * The heart of our interaction with the resource; handles redirection for authentication, access tokens, etc.
     * @param oauth2ClientContext
     * @return
     */
    @Bean
    public OAuth2RestOperations restOath2Template(OAuth2ClientContext oauth2ClientContext) {
        return new OAuth2RestTemplate(resource(), oauth2ClientContext);
    }

    protected OAuth2ProtectedResourceDetails resource() {
        AuthorizationCodeResourceDetails resource = new AuthorizationCodeResourceDetails();
        resource.setClientId(clientID);
        resource.setClientSecret(clientSecret);
        resource.setAccessTokenUri(accessTokenUri);
        resource.setUserAuthorizationUri(userAuthorizationUri);
        resource.setScope(Arrays.asList("read","write"));

        return resource;
    }
    
    InfluxConfig config = new InfluxConfig() {
        @Override
        public Duration step() {
            return Duration.ofSeconds(10);
        }

        @Override
        public String db() {
            return "metrics";
        }

        @Override
        public String get(String k) {
            return null; // accept the rest of the defaults
        }
    };
    
    MeterRegistry registry = new InfluxMeterRegistry(config, Clock.SYSTEM);	
}
