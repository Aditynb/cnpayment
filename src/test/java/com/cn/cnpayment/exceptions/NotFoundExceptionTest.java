package com.cn.cnpayment.exceptions;

import com.cn.cnpayment.exception.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Testing for Custom Exception - NotFoundException")
public class NotFoundExceptionTest {

    @Test
    @DisplayName("Testing for NotFoundException Constructor")
    void testConstructor(){
        NotFoundException notFoundException=  new NotFoundException("No payment found with given id");
        String msg="No payment found with given id";
        assertNull(notFoundException.getCause());
        assertEquals(0, notFoundException.getSuppressed().length);
        assertEquals(msg, notFoundException.getMessage());
    }

}
