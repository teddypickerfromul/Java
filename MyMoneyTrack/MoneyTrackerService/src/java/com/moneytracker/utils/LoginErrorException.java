package com.moneytracker.utils;

public class LoginErrorException extends Exception {

    public LoginErrorException() {
    }

    public LoginErrorException(/*Log logger,*/String message) {
        super(message);
        //logger.info("Login failed :" + message);		
    }
}
