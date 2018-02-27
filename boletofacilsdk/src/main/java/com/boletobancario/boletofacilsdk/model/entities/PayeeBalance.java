package com.boletobancario.boletofacilsdk.model.entities;

import java.math.BigDecimal;

public class PayeeBalance extends BaseEntity {
	private BigDecimal balance;
	private BigDecimal withheldBalance;
	private BigDecimal transferableBalance;

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public BigDecimal getWithheldBalance() {
		return withheldBalance;
	}

	public void setWithheldBalance(BigDecimal withheldBalance) {
		this.withheldBalance = withheldBalance;
	}

	public BigDecimal getTransferableBalance() {
		return transferableBalance;
	}

	public void setTransferableBalance(BigDecimal transferableBalance) {
		this.transferableBalance = transferableBalance;
	}
}
