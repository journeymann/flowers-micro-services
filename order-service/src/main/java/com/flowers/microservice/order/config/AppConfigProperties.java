package com.flowers.microservice.order.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import java.net.URI;

/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */

@ConfigurationProperties
public class AppConfigProperties {
	
    @Value("${app.host.name}")
    private String host = "";
    
    @Value("${app.domain.name}")
    private String domain = "";

    public URI getPaymentUri() {
        return new ServiceUri(new Hostname("payment"), new Domain(domain), "/paymentAuth").toUri();
    }

    public URI getShippingUri() {
        return new ServiceUri(new Hostname(host), new Domain(domain), "/shipping").toUri();
    }

    public URI getProductUri() {
        return new ServiceUri(new Hostname(host), new Domain(domain), "/product").toUri();
    }
    
    public URI getTaxUri() {
        return new ServiceUri(new Hostname(host), new Domain(domain), "/tax").toUri();
    }    
    
    public void setDomain(String domain) {
        this.domain = domain;
    }

    private class Hostname {
        private final String hostname;

        private Hostname(String hostname) {
            this.hostname = hostname;
        }

        @Override
        public String toString() {
            if (hostname != null && !hostname.equals("")) {
                return hostname;
            } else {
                return "";
            }
        }
    }

    private class Domain {
        private final String domain;

        private Domain(String domain) {
            this.domain = domain;
        }

        @Override
        public String toString() {
            if (domain != null && !domain.equals("")) {
                return "." + domain;
            } else {
                return "";
            }
        }
    }

    private class ServiceUri {
        private final Hostname hostname;
        private final Domain domain;
        private final String endpoint;

        private ServiceUri(Hostname hostname, Domain domain, String endpoint) {
            this.hostname = hostname;
            this.domain = domain;
            this.endpoint = endpoint;
        }

        public URI toUri() {
            return URI.create(wrapHTTP(hostname.toString() + domain.toString()) + endpoint);
        }

        private String wrapHTTP(String host) {
            return "http://" + host;
        }

        @Override
        public String toString() {
            return "ServiceUri{" +
                    "hostname=" + hostname +
                    ", domain=" + domain +
                    '}';
        }
    }
}