package com.cn.cnpayment.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//add proper annotation for this custom exception class
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ElementAlreadyExistException extends RuntimeException{
    //complete the method body for this given custom exception
    public ElementAlreadyExistException(String message)
    {
    	super(message);
    }
}
