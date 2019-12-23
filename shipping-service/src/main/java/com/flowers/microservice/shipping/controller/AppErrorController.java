package com.flowers.microservice.shipping.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
/**
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  12/11/2018
 * @version 1.0
 *
 */
@Controller
public class AppErrorController implements ErrorController  {
 
    @RequestMapping("/error")
    public String handleError(HttpServletRequest request) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
         
        if (status != null) {
            Integer statusCode = Integer.valueOf(status.toString());
         
            if(statusCode == HttpStatus.NOT_FOUND.value()) {
                return "error-404";
            }
            else if(statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                return "error-500";
            }
            else if(statusCode == HttpStatus.UNAUTHORIZED.value()) {
                return "error-401";
            }
            else if(statusCode == HttpStatus.FORBIDDEN.value()) {
                return "error-401";
            }
            else if(statusCode == HttpStatus.BAD_REQUEST.value()) {
                return "error-401";
            }
        }
        return "error";
    }    
 
    @Override
    public String getErrorPath() {
        return "/error";
    }
}