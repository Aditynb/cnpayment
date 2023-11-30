package com.cn.cnpayment.repository;

import com.cn.cnpayment.dal.PaymentDAL;
import com.cn.cnpayment.dal.PaymentDALImpl;
import com.cn.cnpayment.entity.Payment;
import jakarta.persistence.EntityManager;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.hibernate.Session;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@DisplayName("Testing for DAL Layer")
public class RepositoryTests {

    @MockBean
    private EntityManager entityManager;

Session session;
    @Autowired
    PaymentDALImpl paymentDALImpl;

    @BeforeEach
    public void setUp() {
        session=mock(Session.class);
        when(entityManager.unwrap(Session.class)).thenReturn(session);
    }

    @Test
    @DisplayName("Testing for add payment")
    @Order(1)
    void testAddPayment(){
        Payment payment=new Payment(1, "Cash", "Description");
        paymentDALImpl.addPayment(payment);
        verify(session).save(payment);
    }

    @Test
    @DisplayName("Testing for update payment")
    @Order(6)
    void updatePaymentTest(){
        int paymentId = 1;
        Payment updatedPayment = new Payment(1, "Updated Payment Type", "Updated Description");

        Payment payment = new Payment(1, "Old Payment Type", "Old Description");
        when(session.get(Payment.class, 1)).thenReturn(payment);

        paymentDALImpl.update(updatedPayment);

        verify(session).update(payment);
    }

@Test
@DisplayName("Testing for delete payment by id")
@Order(7)
void testDeletePayment(){
    Payment paymentToDelete = new Payment(1, "Payment Type", "Description");

    when(session.get(Payment.class, 1)).thenReturn(paymentToDelete);

    paymentDALImpl.delete(1);

    verify(session).delete(paymentToDelete);
}

    @Test
    @DisplayName("Testing for update payment description")
    public void testUpdateDescription() {
        Payment payment=mock(Payment.class);
        String updatedDescription="description changed";
        when(session.get(Payment.class, 1)).thenReturn(payment);

        paymentDALImpl.updateDescription(1, updatedDescription);

        verify(payment).setDescription(updatedDescription);
        verify(session).update(payment);
    }
    @Test
    @DisplayName("Testing for get by id")
    @Order(2)
    public void testGetById() {


        Payment expectedPayment = new Payment(1, "Cash", "Description");
        when(session.get(eq(Payment.class), eq(1))).thenReturn(expectedPayment);
        Payment result = paymentDALImpl.getById(1);

        verify(session).get(eq(Payment.class), eq(1));
        assertEquals(expectedPayment, result);
    }

    @Test
    @DisplayName("Testing for get all payments")
    @Order(3)
    public void testGetAllPayments() {


        Query<Payment> queryMock = mock(Query.class);
        when(session.createQuery(anyString(), eq(Payment.class))).thenReturn(queryMock);

        List<Payment> mockPayments = new ArrayList<>();
        mockPayments.add(new Payment(1, "Cash", "Description"));
        when(queryMock.getResultList()).thenReturn(mockPayments);

        List<Payment> result = paymentDALImpl.getAllPayments();

        verify(session).createQuery(anyString(), eq(Payment.class));
        verify(queryMock).getResultList();
        assertEquals(mockPayments, result);
    }

    @Test
    @DisplayName("Testing for get payment by type")
    @Order(4)
    void testGetByPaymentType(){

        Query<Payment> queryMock = mock(Query.class);
        Payment payment=new Payment(1, "Cash", "Description");
        List<Payment> paymentList=new ArrayList<>();
        paymentList.add(payment);
        when(session.createQuery(anyString(), eq(Payment.class))).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(paymentList);
        List<Payment> expected=paymentDALImpl.getByPaymentType("Cash");
        verify(queryMock).getResultList();
        assertEquals(expected, paymentList);
    }

    @Test
    @DisplayName("Testing for get by description")
    @Order(5)
    void testGetByPaymentDescription(){

        Query<Payment> queryMock = mock(Query.class);
        Payment payment=new Payment(1, "Cash", "Description");
        List<Payment> paymentList=new ArrayList<>();
        paymentList.add(payment);
        when(session.createQuery(anyString(), eq(Payment.class))).thenReturn(queryMock);
        when(queryMock.getResultList()).thenReturn(paymentList);
        when(paymentDALImpl.getByPaymentDescription(anyString())).thenReturn(paymentList);

        List<Payment> expected=paymentDALImpl.getByPaymentDescription("Description");
        verify(queryMock).getResultList();
        assertEquals(expected, paymentList);
    }

}