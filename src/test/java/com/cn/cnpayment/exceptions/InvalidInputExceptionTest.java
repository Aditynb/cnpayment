package com.cn.cnpayment.exceptions;

import com.cn.cnpayment.exception.InvalidInputException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Testing for Custom Exception - InvalidInputException")
public class InvalidInputExceptionTest {

    @Test
    @DisplayName("Testing for InvalidInputException Constructor")
    void testConstructor(){
        InvalidInputException invalidInputException=new InvalidInputException("Invalid Input ");
}
}
