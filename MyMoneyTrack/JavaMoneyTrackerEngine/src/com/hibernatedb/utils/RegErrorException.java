package com.hibernatedb.utils;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class RegErrorException extends Exception {

	public RegErrorException() {}
	
	public RegErrorException(/*Log logger,*/ String message) {
            super(message);    
            //logger.info("Login failed :" + message);		
	}
}
