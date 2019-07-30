package com.flowers.microservice.order.resource;
/**
 * 
 * @author <a href="mailto:casmong@gmail.com">cgordon</a><br>
 * {@literal @}created  02/11/2019
 * @version 1.0
 *
 */
public class PaymentResponse {
    private boolean authorised = false;
    private String  message;

    public PaymentResponse() {
    }

    public PaymentResponse(boolean authorised, String message) {
        this.authorised = authorised;
        this.message = message;
    }

    @Override
    public String toString() {
        return 	String.format("PaymentResponse{authorised: %s, message: %s}", authorised, message);
    }

    public boolean isAuthorised() {
        return authorised;
    }

    public void setAuthorised(boolean authorised) {
        this.authorised = authorised;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}

