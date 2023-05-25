package com.codemaniac.bookingservice.exception;

public class FileLoadException extends RuntimeException{

  public FileLoadException(String msg, Throwable cause){
        super(msg,cause);
    }
}
