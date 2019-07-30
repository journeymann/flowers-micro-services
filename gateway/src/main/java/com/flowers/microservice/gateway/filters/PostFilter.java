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
public class PostFilter extends ZuulFilter {
	private static final transient Logger log = LoggerFactory.getLogger(PostFilter.class);
 
	  @Override
	  public String filterType() {
	    return "post";
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
	    
	    log.info("PostFilter: " + String.format("response's content type is %s", response.getStatus()));
	    
	    //if(StringUtils.isEmpty(RequestContext.getCurrentContext().getResponseBody())){
	    
	    //	response.setStatus(HttpStatus.SC_NO_CONTENT);
	   // 	return response;
	    //}
	    
    	//return response;
	    
	    System.out.printf("PostFilter: " + String.format("response's content type is %s\n", response.getStatus()));
	    System.out.printf("PostFilter: " + String.format("response's context %s\n", String.valueOf(RequestContext.getCurrentContext())));
	    System.out.printf("PostFilter: " + String.format("response's body %s\n", RequestContext.getCurrentContext().getResponseBody()));	    
	    
	    return null;
	  }
}