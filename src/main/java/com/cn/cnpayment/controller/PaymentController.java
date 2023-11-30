package com.cn.cnpayment.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cn.cnpayment.entity.Payment;
import com.cn.cnpayment.service.PaymentService;

import java.util.List;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	//autowire the payment service object
	@Autowired
	PaymentService paymentService;

	@GetMapping("/id/{id}")
	public Payment getPaymentById(@PathVariable int id)
	{
		return paymentService.getPaymentById(id);
	}

	@GetMapping("/allPayments")
	public List<Payment> getAllPayments()
	{
		return paymentService.getAllPayments();
	}

	@GetMapping("/paymentType/{paymentType}")
	public List<Payment> getPaymentByPaymentType(@PathVariable String paymentType)
	{
		return paymentService.getPaymentByPaymentType(paymentType);
	}

	@GetMapping("/description/{keyword}")
	public List<Payment> getPaymentByDescriptionKeyword(@PathVariable String keyword)
	{
		return paymentService.getPaymentByDescriptionKeyword(keyword);
	}

	@PostMapping("/add")
	public void addPayment(@RequestBody Payment payment){
		 paymentService.addPayment(payment);
	}


	/**
1. Call the required service method
2. Add proper annotation for DELETE Mapping and attach the pathvariable to the method parameter.
        **/
	@DeleteMapping("/delete/id/{id}")
	public void deletePayment(@PathVariable int id)
	{
paymentService.delete(id);
	}

	/**
	 1. Call the required service method
	 2. Add proper annotation for PUT Mapping and attach the required request body.
	 **/
	@PutMapping("/update")
	public void updatePayment(@RequestBody Payment paymnent )
	{
paymentService.update(paymnent);
	}

	/**
	 1. Call the required service method
	 2. Add proper annotation for PUT Mapping and attach the required pathvariables to the method parameter.
	 **/
	@PutMapping("/update/{id}/description/{description}")
	public void updateDescription(@PathVariable int id,@PathVariable String description)
	
	{
		paymentService.updateDescription(id, description);
	}



}
