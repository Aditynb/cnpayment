package com.cn.cnpayment.controller;

import com.cn.cnpayment.dal.PaymentDAL;
import com.cn.cnpayment.entity.Payment;
import com.cn.cnpayment.service.PaymentService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Testing for Controller Layer")
@WebMvcTest
public class ControllerTest {

    @Autowired
    PaymentController controller;

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private PaymentService paymentService;


    @Test
    @DisplayName("Testing for GET /payment/id/{id}")
    @Order(2)
    void testGetPaymentById() throws Exception {
        when(paymentService.getPaymentById(anyInt())).thenReturn(new Payment(2, "Debit", "Description"));
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payment/id/{pnr}", 2);
        mockMvc
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string("{\"id\":2,\"paymentType\":\"Debit\",\"description\":\"Description\"}"));
    }

    @Test
    @DisplayName("Testing for GET /payment/allPayments")
    @Order(3)
    void testGetAllPayments() throws Exception {

        when(paymentService.getAllPayments()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payment/allPayments");
        mockMvc
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    @Test
    @DisplayName("Testing for GET /paymentType/{paymentType}")
    @Order(4)
    void testGetPaymentByPaymentType() throws Exception {
        List<Payment> paymentList = new ArrayList<>();
        Payment payment1 = new Payment(2, "Debit", "Description");
        paymentList.add(payment1);
        List<Payment> paymentList1 = new ArrayList<>();
        Payment payment = new Payment(2, "Debit", "Description");
        paymentList1.add(payment);
        ObjectMapper objectMapper = new ObjectMapper();

        String expectedJson = objectMapper.writeValueAsString(paymentList);
        when(paymentService.getPaymentByPaymentType("Debit")).thenReturn(paymentList1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payment/paymentType/Debit");

        mockMvc
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(expectedJson));
    }


    @Test
    @DisplayName("Testing for GET /paymentType/description/{keyword}")
    @Order(5)
    void testGetPaymentByDescription() throws Exception {

        List<Payment> paymentList1 = new ArrayList<>();
        Payment payment2 = new Payment(2, "Debit", "Description");
        Payment payment3 = new Payment(3, "Credit", "Description");
        paymentList1.add(payment2);
        paymentList1.add(payment3);
        ObjectMapper objectMapper = new ObjectMapper();

        String expectedJson = objectMapper.writeValueAsString(paymentList1);
        when(paymentService.getPaymentByDescriptionKeyword("Description")).thenReturn(paymentList1);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/payment/description/Description");
        mockMvc
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(expectedJson));
    }

    @Test
    @DisplayName("Testing for POST /payment/add")
    @Order(1)
    void testAddPayment() throws Exception {
        Payment payment = new Payment(1, "Cash", "Cash Payment");
        doNothing().when(paymentService).addPayment(payment);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/payment/add");
        ObjectMapper objectMapper = new ObjectMapper();
        String paymentJsonObj = objectMapper.writeValueAsString(payment);
        requestBuilder
                .contentType(MediaType.APPLICATION_JSON)
                .content(paymentJsonObj);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Testing for PUT /payment/update")
    @Order(6)
    void testUpdatePayment() throws Exception {
        Payment payment=new Payment(1, "Cash", "cash payment description");
 doNothing().when(paymentService).update(payment);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.put("/payment/update");
        ObjectMapper objectMapper = new ObjectMapper();
        String paymentJsonObj = objectMapper.writeValueAsString(payment);
        requestBuilder
                .contentType(MediaType.APPLICATION_JSON)
                .content(paymentJsonObj);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @DisplayName("Testing for PUT /payment/update/{id}/description/{description}")
    @Order(7)
    void testUpdatePaymentDescription() throws Exception {
        int paymentId = 1;
        String newDescription = "New Description";
        MockHttpServletRequestBuilder requestBuilder= MockMvcRequestBuilders.put("/payment/update/{id}/description/{description}", paymentId, newDescription);
        requestBuilder.contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
                        .andExpect(MockMvcResultMatchers.status().isOk());

    }

    @Test
    @DisplayName("Testing for DELETE /payment/delete/id/{id}")
    @Order(8)
    void testDeletePayment() throws Exception{
        doNothing().when(paymentService).delete(1);
     mockMvc.perform(MockMvcRequestBuilders.delete("/payment/delete/id/{id}", 1)
             .contentType(MediaType.APPLICATION_JSON))
             .andExpect(MockMvcResultMatchers.status().isOk());
    }

}
