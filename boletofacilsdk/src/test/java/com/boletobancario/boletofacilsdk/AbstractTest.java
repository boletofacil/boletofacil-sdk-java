package com.boletobancario.boletofacilsdk;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

import com.boletobancario.boletofacilsdk.model.entities.Address;
import com.boletobancario.boletofacilsdk.model.entities.BankAccount;
import com.boletobancario.boletofacilsdk.model.entities.Charge;
import com.boletobancario.boletofacilsdk.model.entities.Payee;
import com.boletobancario.boletofacilsdk.model.entities.Payer;
import com.boletobancario.boletofacilsdk.model.entities.Person;
import com.boletobancario.boletofacilsdk.model.entities.Split;
import com.boletobancario.boletofacilsdk.model.entities.Transfer;
import com.boletobancario.boletofacilsdk.model.entities.enums.BankAccountType;
import com.boletobancario.boletofacilsdk.model.entities.enums.Category;
import com.boletobancario.boletofacilsdk.model.request.ListChargesDates;

import junit.framework.Assert;

public abstract class AbstractTest {

	protected Payer getPayer() {
		Payer payer = new Payer();
		payer.setName("Pagador do SDK");
		payer.setCpfCnpj("00922156964");
		return payer;
	}

	protected Charge getCharge() {
		Charge charge = new Charge();
		charge.setDescription("Teste de cobrança pelo SDK Java");
		charge.setAmount(BigDecimal.valueOf(123.45));
		charge.setDueDate(getTodayDate());
		charge.setPayer(getPayer());
		return charge;
	}

	protected Transfer getTransfer() {
		Transfer transfer = new Transfer();
		return transfer;
	}

	protected ListChargesDates getListChargesDates() {
		Calendar endDate = getStartDate();
		endDate.add(Calendar.DAY_OF_MONTH, 1);

		ListChargesDates dates = new ListChargesDates();
		dates.setBeginDueDate(getStartDate());
		dates.setEndDueDate(endDate);
		return dates;
	}

	protected Person getAccountHolder() {
		Person accountHolder = new Person();
		accountHolder.setName("Favorecido do SDK");
		accountHolder.setCpfCnpj("18472019110");
		return accountHolder;
	}

	protected BankAccount getBankAccount() {
		BankAccount bankAccount = new BankAccount();
		bankAccount.setBankNumber("237");
		bankAccount.setAgencyNumber("1234");
		bankAccount.setAccountNumber("5678-9");
		bankAccount.setBankAccountType(BankAccountType.CHECKING);
		return bankAccount;
	}

	protected Address getAddress() {
		Address address = new Address();
		address.setStreet("Rua Teste SDK");
		address.setNumber("S/N");
		address.setCity("São Paulo");
		address.setState("SP");
		address.setPostcode("01010-100");
		return address;
	}

	protected Payee getPayee() {
		Payee payee = new Payee();
		payee.setName("Favorecido do SDK");
		payee.setCpfCnpj("18472019110");
		payee.setEmail("email@email.com");
		payee.setPassword("senha");
		payee.setBirthDate(getStartDate());
		payee.setPhone("3232-3232");
		payee.setLinesOfBusiness("Linhas_de_negocio");
		payee.setAccountHolder(getAccountHolder());
		payee.setBankAccount(getBankAccount());
		payee.setCategory(Category.OTHER);
		payee.setAddress(getAddress());
		payee.setBusinessAreaId(1000);
		return payee;
	}

	protected Split getSplit() {
		Split split = new Split();
		split.setSplitTriggerAmount(BigDecimal.valueOf(10));
		return split;
	}

	protected Calendar getMinDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(Long.MIN_VALUE));
		return cal;
	}

	protected Calendar getMaxDate() {
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date(Long.MAX_VALUE));
		return cal;
	}

	protected Calendar getTodayDate() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal;
	}

	protected Calendar getStartDate() {
		Calendar cal = getDate(2013, 2, 19);
		cal.set(Calendar.HOUR, 11);
		cal.set(Calendar.MINUTE, 21);
		cal.set(Calendar.SECOND, 5);
		return cal;
	}

	protected Calendar getStartDateAddMonths(int months) {
		Calendar cal = getDate(2013, 2, 19);
		cal.set(Calendar.HOUR, 11);
		cal.set(Calendar.MINUTE, 21);
		cal.set(Calendar.SECOND, 5);
		cal.add(Calendar.MONTH, months);
		return cal;
	}

	protected Calendar getDate(int year, int month, int day) {
		Calendar cal = getTodayDate();
		cal.set(Calendar.YEAR, year);
		cal.set(Calendar.MONTH, month - 1);
		cal.set(Calendar.DAY_OF_MONTH, day);
		return cal;
	}

	protected boolean assertDate(Calendar expected, Calendar actual) {
		return expected.get(Calendar.YEAR) == actual.get(Calendar.YEAR)
		        && expected.get(Calendar.MONTH) == actual.get(Calendar.MONTH)
		        && expected.get(Calendar.DAY_OF_MONTH) == actual.get(Calendar.DAY_OF_MONTH);
	}

	protected void assertResult(String expected, String actual) {
		Assert.assertEquals(replaceBlanks(expected), replaceBlanks(actual));
	}

	protected String getBaseUrl() {
		return "https://boletofacil";
	}

	protected String newLine() {
		return System.getProperty("line.separator");
	}

	private String replaceBlanks(String str) {
		return str.replaceAll("\\s+", "");
	}
}
