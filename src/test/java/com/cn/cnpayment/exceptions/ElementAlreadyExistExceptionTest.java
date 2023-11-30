package com.cn.cnpayment.exceptions;

import com.cn.cnpayment.exception.ElementAlreadyExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Testing for Custom Exception - ElementAlreadyExistException")
public class ElementAlreadyExistExceptionTest {

    @Test
    @DisplayName("Testing for ElementAlreadyExistException Construtor")
    void testConstructor(){
        String message="Payment Already Exists";
        ElementAlreadyExistException elementAlreadyExistException=new ElementAlreadyExistException("Payment Already Exists");
        assertNull(elementAlreadyExistException.getCause());
        assertEquals(0, elementAlreadyExistException.getSuppressed().length);
    assertEquals( message,elementAlreadyExistException.getMessage());
    }
}
