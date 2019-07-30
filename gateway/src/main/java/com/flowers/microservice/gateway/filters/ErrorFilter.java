/**
 * 
 */
package com.flowers.microservice.gateway.filters;

/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 *
 */
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
 
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
 
@Component
public class ErrorFilter extends ZuulFilter {
	private static final transient Logger log = LoggerFactory.getLogger(PostFilter.class);
 
	  @Override
	  public String filterType() {
	    return "error";
	  }
 
	  @Override
	  public int filterOrder() {
	    return 1;
	  }
 
	  @Override
	  public boolean shouldFilter() {
	    return true;
	  }
 
	  @Override
	  public Object run() {
	    HttpServletResponse response = RequestContext.getCurrentContext().getResponse();
	    
	    log.info("ErrorFilter: " + String.format("response status is %d", response.getStatus()));
	    
	    return null;
	  }
}