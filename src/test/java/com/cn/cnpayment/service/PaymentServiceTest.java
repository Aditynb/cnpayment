package com.cn.cnpayment.service;

import com.cn.cnpayment.dal.PaymentDAL;
import com.cn.cnpayment.entity.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Testing for Service Layer")
class PaymentServiceTest {

    @MockBean
    private PaymentDAL paymentDAL;

    @Autowired
    private PaymentService paymentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

    }

    @Test
    @DisplayName("Testing for add payment")
    @Order(1)
    void testAddPayment(){
        Payment payment=new Payment(2, "Credit", "Credit Card Payment");
    doNothing().when(paymentDAL).addPayment(payment);
    paymentService.addPayment(payment);
    verify(paymentDAL, times(1)).addPayment(payment);
    }

    @Test
    @DisplayName("Testing for update payment")
    @Order(6)
    void testUpdatePayment(){
        Payment payment=new Payment(2, "Credit", "Credit Card Payment");
        doNothing().when(paymentDAL).update(payment);
        paymentService.update(payment);
        verify(paymentDAL, times(1)).update(payment);
    }

    @Test
    @DisplayName("Testing for update payment description")
    @Order(7)
    void testUpdatePaymentDescription(){
        Payment payment=new Payment(2, "Credit", "Credit Card Payment");
        String newDescription="updated description";
        doNothing().when(paymentDAL).updateDescription(1,newDescription);
        paymentService.updateDescription(1, newDescription);
        verify(paymentDAL, times(1)).updateDescription(1,newDescription);
    }

    @Test
    @DisplayName("Testing for delete payment")
    @Order(7)
    void testDeletePayment(){
        doNothing().when(paymentDAL).delete(1);
        paymentService.delete(1);
        verify(paymentDAL, times(1)).delete(1);
    }

    @Test
    @DisplayName("Testing for get payments by id")
    @Order(2)
    void testGetPaymentById() {

        Payment payment = new Payment(1, "Cash", "Description");
        when(paymentDAL.getById(1)).thenReturn(payment);
        assertEquals(payment, paymentService.getPaymentById(1));
        verify(paymentDAL, times(1)).getById(1);
    }

    @Test
    @DisplayName("Testing for get payments by payment type")
    @Order(3)
    void testGetPaymentByPaymentType() {

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(1, "Cash", "Description"));
        when(paymentDAL.getByPaymentType("Cash")).thenReturn(paymentList);

        List<Payment> result = paymentService.getPaymentByPaymentType("Cash");

        assertEquals(paymentList, result);
        verify(paymentDAL, times(1)).getByPaymentType("Cash");
    }

    @Test
    @DisplayName("Testing for get payments by description keyword")
    @Order(4)
    void testGetPaymentByDescriptionKeyword() {

        String keyword = "Keyword";
        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(1, "Cash", "Description with Keyword"));
        when(paymentDAL.getByPaymentDescription(eq(keyword))).thenReturn(paymentList);

        List<Payment> result = paymentService.getPaymentByDescriptionKeyword(keyword);


        assertEquals(paymentList, result);
        verify(paymentDAL, times(1)).getByPaymentDescription(eq(keyword));
    }

    @Test
    @DisplayName("Testing for get all payments")
    @Order(5)
    void testGetAllPayments() {

        List<Payment> paymentList = new ArrayList<>();
        paymentList.add(new Payment(1, "Cash", "Description 1"));
        paymentList.add(new Payment(2, "CreditCard", "Description 2"));
        when(paymentDAL.getAllPayments()).thenReturn(paymentList);

        List<Payment> result = paymentService.getAllPayments();

        assertEquals(paymentList, result);
        verify(paymentDAL, times(1)).getAllPayments();
    }
}
