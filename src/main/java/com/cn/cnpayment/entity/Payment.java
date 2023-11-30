package com.cn.cnpayment.entity;

import jakarta.persistence.*;


/*
add required annotations for the entity class
*/
@Entity
@Table(name="Payment")
public class Payment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)

	@Column
	private int id;

	@Column
	private String paymentType;

	@Column
	private String description;

	public int getId() {
		return id;
	}

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Payment(int id, String paymentType, String description) {
		this.id = id;
		this.paymentType = paymentType;
		this.description = description;
	}

	public Payment() {
	}
}

