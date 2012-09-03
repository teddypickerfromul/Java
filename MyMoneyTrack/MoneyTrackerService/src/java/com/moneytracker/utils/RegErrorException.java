package com.moneytracker.utils;

public class RegErrorException extends Exception {

    public RegErrorException() {
    }

    public RegErrorException(/*Log logger,*/String message) {
        super(message);
        //logger.info("Login failed :" + message);		
    }
}
