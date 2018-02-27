package com.boletobancario.boletofacilsdk.model.entities;

import java.math.BigDecimal;

public class Transfer extends BaseEntity {
	private BigDecimal amount;

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
