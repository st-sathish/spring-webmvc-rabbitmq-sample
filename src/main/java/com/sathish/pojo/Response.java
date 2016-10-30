package com.sathish.pojo;

import java.io.Serializable;

/**
 * General class which is responsible to all the outgoing response
 * 
 * Indeed, If you need any object to customize you should extend to all the response object
 * @author Sathish Thangathurai
 *
 */
public class Response implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String error;

	private int code;

	private String message;

	
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public String toString() {
		return "{message:"+this.message+","+
				"code:"+this.code+","+
				"error:"+this.error+"}";
	}
}
