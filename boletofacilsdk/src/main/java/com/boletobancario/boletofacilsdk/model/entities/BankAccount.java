package com.boletobancario.boletofacilsdk.model.entities;

import com.boletobancario.boletofacilsdk.model.entities.enums.BankAccountType;

public class BankAccount extends BaseEntity {
	private String bankNumber;
	private String agencyNumber;
	private String accountNumber;
	private BankAccountType bankAccountType;
	private Integer accountComplementNumber;

	public String getBankNumber() {
		return bankNumber;
	}

	public void setBankNumber(String bankNumber) {
		this.bankNumber = bankNumber;
	}

	public String getAgencyNumber() {
		return agencyNumber;
	}

	public void setAgencyNumber(String agencyNumber) {
		this.agencyNumber = agencyNumber;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BankAccountType getBankAccountType() {
		return bankAccountType;
	}

	public void setBankAccountType(BankAccountType bankAccountType) {
		this.bankAccountType = bankAccountType;
	}

	public Integer getAccountComplementNumber() {
		return accountComplementNumber;
	}

	public void setAccountComplementNumber(Integer accountComplementNumber) {
		this.accountComplementNumber = accountComplementNumber;
	}
}
