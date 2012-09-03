package com.hibernatedb.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class LoginErrorException extends Exception {

	public LoginErrorException() {}
	
	public LoginErrorException(/*Log logger,*/ String message) {
            super(message);    
            //logger.info("Login failed :" + message);		
	}
}
