package com.codemaniac.bookingservice.exception;

public class AirlineNotFoundException extends RuntimeException{
    public AirlineNotFoundException(String message){
        super(message);
    }
}
