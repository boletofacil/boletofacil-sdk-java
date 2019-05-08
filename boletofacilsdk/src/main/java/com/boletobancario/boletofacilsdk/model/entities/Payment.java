package com.boletobancario.boletofacilsdk.model.entities;

import java.math.BigDecimal;
import java.util.Calendar;

import com.boletobancario.boletofacilsdk.model.entities.enums.PaymentStatus;
import com.boletobancario.boletofacilsdk.model.entities.enums.PaymentType;

public class Payment extends BaseEntity {
	private Long id;
	private BigDecimal amount;
	private Calendar date;
	private BigDecimal fee;
	private PaymentType type;
	private PaymentStatus status;
	private String creditCardId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public PaymentType getType() {
		return type;
	}

	public void setType(PaymentType type) {
		this.type = type;
	}

	public PaymentStatus getStatus() {
		return status;
	}

	public void setStatus(PaymentStatus status) {
		this.status = status;
	}

	public String getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(String creditCardId) {
		this.creditCardId = creditCardId;
	}
}
