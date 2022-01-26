package com.products.backend.response;

/**
 * @author yatheesh sai
 * Response message class 
 *
 */
public class ResponseMessage {
	 private String message;
	  public ResponseMessage(String message) {
		    this.message = message;
		  }

		  public String getMessage() {
		    return message;
		  }

		  public void setMessage(String message) {
		    this.message = message;
		  }
}
