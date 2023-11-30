package com.cn.cnpayment.dal;

import jakarta.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.cn.cnpayment.entity.Payment;
import java.util.ArrayList;
import java.util.List;


/**
 1. Override the methods of PaymentDal Interface.
 2. Add proper annotations for methods.
 **/
@Repository
public class PaymentDALImpl implements PaymentDAL{

	@Autowired
	EntityManager entityManager;



	@Override
	public Payment getById(int id) {
		Session session = entityManager.unwrap(Session.class);
		Payment payment = session.get(Payment.class, id);
		return payment;
	}

	@Override
	public List<Payment> getAllPayments() {
		Session session = entityManager.unwrap(Session.class);
		List<Payment> allPayments= session.createQuery(
				"SELECT p FROM Payment p", Payment.class).getResultList();
		return allPayments;
	}

	@Override
	public List<Payment> getByPaymentType(String paymentType){
		List<Payment> allPayments=getAllPayments();
		List<Payment> paymentsByPaymentType = new ArrayList<>();
		for(Payment payment : allPayments)
		{
			if(payment.getPaymentType().equalsIgnoreCase(paymentType))
			{
				paymentsByPaymentType.add(payment);
			}
		}
		return paymentsByPaymentType;
	}

	@Override
	public List<Payment> getByPaymentDescription(String keyword){
		List<Payment> allPayments=getAllPayments();
		List<Payment> paymentsByDescription = new ArrayList<>();
		for(Payment payment : allPayments)
		{
			if(payment.getDescription().contains(keyword))
			{
				paymentsByDescription.add(payment);
			}
		}
		return paymentsByDescription;
	}

	@Override
	public void addPayment(Payment payment){
		Session session=entityManager.unwrap(Session.class);
		session.save(payment);
	}
	public void delete(int paymentId) {
		// TODO Auto-generated method stub
		Session session=entityManager.unwrap(Session.class);
		Payment payment= session.get(Payment.class,paymentId);
		session.delete(payment);
		
		
	}

	@Override
	public void update(Payment updatePayment) {
		// TODO Auto-generated method stub
		Session session=entityManager.unwrap(Session.class);
		Payment payment=session.get(Payment.class,updatePayment.getId());
		payment.setDescription(updatePayment.getDescription());
		payment.setPaymentType(updatePayment.getPaymentType());
		session.update(payment);
		
	}

	@Override
	public void updateDescription(int paymentId, String description) {
		// TODO Auto-generated method stub
		Session session=entityManager.unwrap(Session.class);
		Payment payment=session.get(Payment.class,paymentId);
		payment.setDescription(description);
		session.update(payment);
		
	}

	//autowire the EntityManager object;

}
