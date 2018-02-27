package com.boletobancario.boletofacilsdk.model.entities;

import java.math.BigDecimal;

public class Discount extends BaseEntity {
	private BigDecimal amount;
	private Integer days;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getDays() {
		return days;
	}

	public void setDays(Integer days) {
		this.days = days;
	}
}
