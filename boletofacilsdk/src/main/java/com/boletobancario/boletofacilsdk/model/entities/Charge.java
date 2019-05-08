package com.boletobancario.boletofacilsdk.model.entities;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

import com.boletobancario.boletofacilsdk.model.entities.enums.PaymentType;

public class Charge extends BaseEntity {
	private String description;
	private String reference;
	private BigDecimal amount;
	private Calendar dueDate;
	private Integer installments;
	private Integer maxOverdueDays;
	private BigDecimal fine;
	private BigDecimal interest;
	private Discount discount;
	private Payer payer;
	private Address billingAddress;
	private Boolean notifyPayer;
	private String notificationUrl;
	private String referralToken;
	private String feeSchemaToken;
	private String splitRecipient;
	private List<PaymentType> paymentTypes;
	private CreditCard creditCard;
	private Boolean paymentAdvance;
	private String creditCardHash;
	private Boolean creditCardStore;
	private String creditCardId;

	// Attributes used only in response
	private String code;
	private String link;
	private String payNumber;
	private String checkoutUrl;
	private BilletDetails billetDetails;
	private List<Payment> payments;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Calendar getDueDate() {
		return dueDate;
	}

	public void setDueDate(Calendar dueDate) {
		this.dueDate = dueDate;
	}

	public Integer getInstallments() {
		return installments;
	}

	public void setInstallments(Integer installments) {
		this.installments = installments;
	}

	public Integer getMaxOverdueDays() {
		return maxOverdueDays;
	}

	public void setMaxOverdueDays(Integer maxOverdueDays) {
		this.maxOverdueDays = maxOverdueDays;
	}

	public BigDecimal getFine() {
		return fine;
	}

	public void setFine(BigDecimal fine) {
		this.fine = fine;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public Discount getDiscount() {
		return discount;
	}

	public void setDiscount(Discount discount) {
		this.discount = discount;
	}

	public Payer getPayer() {
		return payer;
	}

	public void setPayer(Payer payer) {
		this.payer = payer;
	}

	public Address getBillingAddress() {
		return billingAddress;
	}

	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}

	public Boolean getNotifyPayer() {
		return notifyPayer;
	}

	public void setNotifyPayer(Boolean notifyPayer) {
		this.notifyPayer = notifyPayer;
	}

	public String getNotificationUrl() {
		return notificationUrl;
	}

	public void setNotificationUrl(String notificationUrl) {
		this.notificationUrl = notificationUrl;
	}

	public String getReferralToken() {
		return referralToken;
	}

	public void setReferralToken(String referralToken) {
		this.referralToken = referralToken;
	}

	public String getFeeSchemaToken() {
		return feeSchemaToken;
	}

	public void setFeeSchemaToken(String feeSchemaToken) {
		this.feeSchemaToken = feeSchemaToken;
	}

	public String getSplitRecipient() {
		return splitRecipient;
	}

	public void setSplitRecipient(String splitRecipient) {
		this.splitRecipient = splitRecipient;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getPayNumber() {
		return payNumber;
	}

	public void setPayNumber(String payNumber) {
		this.payNumber = payNumber;
	}

	public String getCheckoutUrl() {
		return checkoutUrl;
	}

	public void setCheckoutUrl(String checkoutUrl) {
		this.checkoutUrl = checkoutUrl;
	}

	public BilletDetails getBilletDetails() {
		return billetDetails;
	}

	public void setBilletDetails(BilletDetails billetDetails) {
		this.billetDetails = billetDetails;
	}

	public List<Payment> getPayments() {
		return payments;
	}

	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}

	public List<PaymentType> getPaymentTypes() {
		return paymentTypes;
	}

	public void setPaymentTypes(List<PaymentType> paymentTypes) {
		this.paymentTypes = paymentTypes;
	}

	public String getPaymentTypesAsString() {
		return PaymentType.paymentTypeListToString(paymentTypes);
	}

	public void setPaymentTypes(String paymentTypes) {
		this.paymentTypes = PaymentType.stringToPaymentTypeList(paymentTypes);
	}

	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	public Boolean getPaymentAdvance() {
		return paymentAdvance;
	}

	public void setPaymentAdvance(Boolean paymentAdvance) {
		this.paymentAdvance = paymentAdvance;
	}

	public String getCreditCardHash() {
		return creditCardHash;
	}

	public void setCreditCardHash(String creditCardHash) {
		this.creditCardHash = creditCardHash;
	}

	public Boolean getCreditCardStore() {
		return creditCardStore;
	}

	public void setCreditCardStore(Boolean creditCardStore) {
		this.creditCardStore = creditCardStore;
	}

	public String getCreditCardId() {
		return creditCardId;
	}

	public void setCreditCardId(String creditCardId) {
		this.creditCardId = creditCardId;
	}
}