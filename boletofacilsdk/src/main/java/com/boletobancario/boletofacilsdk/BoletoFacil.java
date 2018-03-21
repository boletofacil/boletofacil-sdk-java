package com.boletobancario.boletofacilsdk;

import org.apache.commons.lang3.StringUtils;

import com.boletobancario.boletofacilsdk.enums.BoletoFacilEnvironment;
import com.boletobancario.boletofacilsdk.enums.RequestType;
import com.boletobancario.boletofacilsdk.enums.ResponseType;
import com.boletobancario.boletofacilsdk.exceptions.BoletoFacilTokenException;
import com.boletobancario.boletofacilsdk.model.ModelBase;
import com.boletobancario.boletofacilsdk.model.entities.Charge;
import com.boletobancario.boletofacilsdk.model.entities.Payee;
import com.boletobancario.boletofacilsdk.model.entities.Split;
import com.boletobancario.boletofacilsdk.model.entities.Transfer;
import com.boletobancario.boletofacilsdk.model.request.ListChargesDates;
import com.boletobancario.boletofacilsdk.model.response.CancelChargeResponse;
import com.boletobancario.boletofacilsdk.model.response.ChargeResponse;
import com.boletobancario.boletofacilsdk.model.response.FeeSchemaResponse;
import com.boletobancario.boletofacilsdk.model.response.FetchBalanceResponse;
import com.boletobancario.boletofacilsdk.model.response.ListChargesResponse;
import com.boletobancario.boletofacilsdk.model.response.PayeeResponse;
import com.boletobancario.boletofacilsdk.model.response.TransferResponse;

public class BoletoFacil extends BoletoFacilBase {
	public BoletoFacil(BoletoFacilEnvironment boletoFacilEnvironment, String token) {
		this.boletoFacilEnvironment = boletoFacilEnvironment;
		if (StringUtils.isBlank(token)) {
			throw new BoletoFacilTokenException("Token do favorecido inválido");
		}
		this.token = token;
	}

	public ChargeResponse issueCharge(Charge charge) {
		return issueCharge(charge, ResponseType.JSON);
	}

	public ChargeResponse issueCharge(Charge charge, ResponseType responseType) {
		StringBuilder requestUri = new StringBuilder(getEndPoint() + "/issue-charge?");
		addRequestParameters(requestUri, RequestType.ISSUE_CHARGE, charge);
		return request(requestUri, responseType, ChargeResponse.class);
	}

	public TransferResponse requestTransfer(Transfer transfer) {
		return requestTransfer(transfer, ResponseType.JSON);
	}

	public TransferResponse requestTransfer(Transfer transfer, ResponseType responseType) {
		StringBuilder requestUri = new StringBuilder(getEndPoint() + "/request-transfer?");
		addRequestParameters(requestUri, RequestType.REQUEST_TRANSFER, transfer);
		return request(requestUri, responseType, TransferResponse.class);
	}

	public ListChargesResponse listCharges(ListChargesDates dates) {
		return listCharges(dates, ResponseType.JSON);
	}

	public ListChargesResponse listCharges(ListChargesDates dates, ResponseType responseType) {
		StringBuilder requestUri = new StringBuilder(getEndPoint() + "/list-charges?");
		addRequestParameters(requestUri, RequestType.LIST_CHARGES, dates);
		return request(requestUri, responseType, ListChargesResponse.class);
	}

	public FetchBalanceResponse fetchBalance() {
		return fetchBalance(ResponseType.JSON);
	}

	public FetchBalanceResponse fetchBalance(ResponseType responseType) {
		StringBuilder requestUri = new StringBuilder(getEndPoint() + "/fetch-balance?");
		addRequestParameters(requestUri, RequestType.FETCH_BALANCE, null);
		return request(requestUri, responseType, FetchBalanceResponse.class);
	}

	public CancelChargeResponse cancelCharge(Charge charge) {
		return cancelCharge(charge, ResponseType.JSON);
	}

	public CancelChargeResponse cancelCharge(Charge charge, ResponseType responseType) {
		StringBuilder requestUri = new StringBuilder(getEndPoint() + "/cancel-charge?");
		addRequestParameters(requestUri, RequestType.CANCEL_CHARGE, charge);
		return request(requestUri, responseType, CancelChargeResponse.class);
	}

	public PayeeResponse createPayee(Payee payee) {
		return createPayee(payee, ResponseType.JSON);
	}

	public PayeeResponse createPayee(Payee payee, ResponseType responseType) {
		StringBuilder requestUri = new StringBuilder(getEndPoint() + "/create-payee?");
		addRequestParameters(requestUri, RequestType.CREATE_PAYEE, payee);
		return postRequest(requestUri, responseType, PayeeResponse.class);
	}

	public FeeSchemaResponse createPayeeFeeSchema(Split split) {
		return createPayeeFeeSchema(split, ResponseType.JSON);
	}

	public FeeSchemaResponse createPayeeFeeSchema(Split split, ResponseType responseType) {
		StringBuilder requestUri = new StringBuilder(getEndPoint() + "/create-payee-fee-schema?");
		addRequestParameters(requestUri, RequestType.CREATE_PAYEE_FEE_SCHEMA, split);
		return request(requestUri, responseType, FeeSchemaResponse.class);
	}

	public PayeeResponse getPayeeStatus(Payee payee) {
		return getPayeeStatus(payee, ResponseType.JSON);
	}

	public PayeeResponse getPayeeStatus(Payee payee, ResponseType responseType) {
		StringBuilder requestUri = new StringBuilder(getEndPoint() + "/get-payee-status?");
		addRequestParameters(requestUri, RequestType.GET_PAYEE_STATUS, payee);
		return request(requestUri, responseType, PayeeResponse.class);
	}

	private void addRequestParameters(StringBuilder requestUri, RequestType requestType, ModelBase entity) {
		addTokenUriParameter(requestUri);
		switch (requestType) {
		case ISSUE_CHARGE:
			Charge chargeToIssue = (Charge) entity;
			addIssueChargeParameters(requestUri, chargeToIssue);
			break;
		case REQUEST_TRANSFER:
			Transfer transfer = (Transfer) entity;
			addRequestTransferParameters(requestUri, transfer);
			break;
		case LIST_CHARGES:
			ListChargesDates dates = (ListChargesDates) entity;
			addListChargeParameters(requestUri, dates);
			break;
		case FETCH_BALANCE:
			break;
		case CANCEL_CHARGE:
			Charge chargeToCancel = (Charge) entity;
			addCancelChargeParameters(requestUri, chargeToCancel);
			break;
		case CREATE_PAYEE:
			Payee payeeToCreate = (Payee) entity;
			addCreatePayeeParameters(requestUri, payeeToCreate);
			break;
		case CREATE_PAYEE_FEE_SCHEMA:
			Split split = (Split) entity;
			addCreatePayeeFeeSchemaParameters(requestUri, split);
			break;
		case GET_PAYEE_STATUS:
			Payee payeeToGetStatus = (Payee) entity;
			addGetPayeeStatusParameters(requestUri, payeeToGetStatus);
			break;
		}
	}

	private void addIssueChargeParameters(StringBuilder requestUri, Charge charge) {
		// @formatter:off
		addUriParameter(requestUri, "description", charge.getDescription());
		addUriParameter(requestUri, "reference", charge.getReference());
		addUriParameter(requestUri, "amount", charge.getAmount());
		addUriParameter(requestUri, "dueDate", charge.getDueDate());
		addUriParameter(requestUri, "installments", charge.getInstallments());
		addUriParameter(requestUri, "maxOverdueDays", charge.getMaxOverdueDays());
		addUriParameter(requestUri, "fine", charge.getFine());
		addUriParameter(requestUri, "interest", charge.getInterest());
		addUriParameter(requestUri, "discountAmount", charge.getDiscount() == null ? null : charge.getDiscount().getAmount());
		addUriParameter(requestUri, "discountDays", charge.getDiscount() == null ? null : charge.getDiscount().getDays());
		addUriParameter(requestUri, "payerName", charge.getPayer() == null ? null : charge.getPayer().getName());
		addUriParameter(requestUri, "payerCpfCnpj", charge.getPayer() == null ? null : charge.getPayer().getCpfCnpj());
		addUriParameter(requestUri, "payerEmail", charge.getPayer() == null ? null : charge.getPayer().getEmail());
		addUriParameter(requestUri, "payerSecondaryEmail", charge.getPayer() == null ? null : charge.getPayer().getSecondaryEmail());
		addUriParameter(requestUri, "payerPhone", charge.getPayer() == null ? null : charge.getPayer().getPhone());
		addUriParameter(requestUri, "payerBirthDate", charge.getPayer() == null ? null : charge.getPayer().getBirthDate());
		addUriParameter(requestUri, "billingAddressStreet", charge.getBillingAddress() == null ? null : charge.getBillingAddress().getStreet());
		addUriParameter(requestUri, "billingAddressNumber", charge.getBillingAddress() == null ? null : charge.getBillingAddress().getNumber());
		addUriParameter(requestUri, "billingAddressComplement", charge.getBillingAddress() == null ? null : charge.getBillingAddress().getComplement());
		addUriParameter(requestUri, "billingAddressNeighborhood", charge.getBillingAddress() == null ? null : charge.getBillingAddress().getNeighborhood());
		addUriParameter(requestUri, "billingAddressCity", charge.getBillingAddress() == null ? null : charge.getBillingAddress().getCity());
		addUriParameter(requestUri, "billingAddressState", charge.getBillingAddress() == null ? null : charge.getBillingAddress().getState());
		addUriParameter(requestUri, "billingAddressPostcode", charge.getBillingAddress() == null ? null : charge.getBillingAddress().getPostcode());
		addUriParameter(requestUri, "notifyPayer", charge.getNotifyPayer());
		addUriParameter(requestUri, "notificationUrl", charge.getNotificationUrl());
		addUriParameter(requestUri, "feeSchemaToken", charge.getFeeSchemaToken());
		addUriParameter(requestUri, "splitRecipient", charge.getSplitRecipient());
		addUriParameter(requestUri, "paymentTypes", charge.getPaymentTypesAsString());
		addUriParameter(requestUri, "creditCardNumber", charge.getCreditCard() == null ? null : charge.getCreditCard().getNumber());
		addUriParameter(requestUri, "creditCardHolderName", charge.getCreditCard() == null ? null : charge.getCreditCard().getHolderName());
		addUriParameter(requestUri, "creditCardSecurityCode", charge.getCreditCard() == null ? null : charge.getCreditCard().getSecurityCode());
		addUriParameter(requestUri, "creditCardExpirationMonth", charge.getCreditCard() == null ? null : charge.getCreditCard().getExpirationMonth());
		addUriParameter(requestUri, "creditCardExpirationYear", charge.getCreditCard() == null ? null : charge.getCreditCard().getExpirationYear());
		addUriParameter(requestUri, "paymentAdvance", charge.getPaymentAdvance());
		// @formatter:on
	}

	private void addRequestTransferParameters(StringBuilder requestUri, Transfer transfer) {
		addUriParameter(requestUri, "amount", transfer.getAmount());
	}

	private void addCancelChargeParameters(StringBuilder requestUri, Charge charge) {
		addUriParameter(requestUri, "code", charge.getCode());
	}

	private void addListChargeParameters(StringBuilder requestUri, ListChargesDates dates) {
		// @formatter:off
		addUriParameter(requestUri, "beginDueDate", dates.getBeginDueDate());
		addUriParameter(requestUri, "endDueDate", dates.getEndDueDate());
		addUriParameter(requestUri, "beginPaymentDate", dates.getBeginPaymentDate());
		addUriParameter(requestUri, "endPaymentDate", dates.getEndPaymentDate());
		// @formatter:on
	}

	private void addCreatePayeeParameters(StringBuilder requestUri, Payee payee) {
		// @formatter:off
        addUriParameter(requestUri, "notificationUrl", payee.getNotificationUrl());
        addUriParameter(requestUri, "name", payee.getName());
        addUriParameter(requestUri, "cpfCnpj", payee.getCpfCnpj());
        addUriParameter(requestUri, "email", payee.getEmail());
        addUriParameter(requestUri, "password", payee.getPassword());
        addUriParameter(requestUri, "birthDate", payee.getBirthDate());
        addUriParameter(requestUri, "phone", payee.getPhone());
        addUriParameter(requestUri, "linesOfBusiness", payee.getLinesOfBusiness());
        addUriParameter(requestUri, "tradingName", payee.getTradingName());
        addUriParameter(requestUri, "reprName", payee.getRepr() == null ? null : payee.getRepr().getName());
        addUriParameter(requestUri, "reprCpfCnpj", payee.getRepr() == null ? null : payee.getRepr().getCpfCnpj());
        addUriParameter(requestUri, "reprBirthDate", payee.getRepr() == null ? null : payee.getRepr().getBirthDate());
        addUriParameter(requestUri, "accountHolderName", payee.getAccountHolder().getName());
        addUriParameter(requestUri, "accountHolderCpfCnpj", payee.getAccountHolder().getCpfCnpj());
        addUriParameter(requestUri, "bankNumber", payee.getBankAccount().getBankNumber());
        addUriParameter(requestUri, "agencyNumber", payee.getBankAccount().getAgencyNumber());
        addUriParameter(requestUri, "accountNumber", payee.getBankAccount().getAccountNumber());
        addUriParameter(requestUri, "bankAccountType", payee.getBankAccount().getBankAccountType().name());
        addUriParameter(requestUri, "accountComplementNumber", payee.getBankAccount().getAccountComplementNumber());
        addUriParameter(requestUri, "category", payee.getCategory() == null ? null : payee.getCategory().name());
        addUriParameter(requestUri, "companyType", payee.getCompanyType() == null ? null : payee.getCompanyType().name());
        addUriParameter(requestUri, "street", payee.getAddress().getStreet());
        addUriParameter(requestUri, "number", payee.getAddress().getNumber());
        addUriParameter(requestUri, "complement", payee.getAddress().getComplement());
        addUriParameter(requestUri, "neighborhood", payee.getAddress().getNeighborhood());
        addUriParameter(requestUri, "city", payee.getAddress().getCity());
        addUriParameter(requestUri, "state", payee.getAddress().getState());
        addUriParameter(requestUri, "postCode", payee.getAddress().getPostcode());
        addUriParameter(requestUri, "businessAreaId", payee.getBusinessAreaId());
        if (payee.getEmailOptOut() != null) {
            addUriParameter(requestUri, "emailOptOut", payee.getEmailOptOut());
        }
        if (payee.getAutoApprove() != null) {
            addUriParameter(requestUri, "autoApprove", payee.getAutoApprove());
        }
		// @formatter:on
	}

	private void addGetPayeeStatusParameters(StringBuilder requestUri, Payee payee) {
		// @formatter:off
        addUriParameter(requestUri, "payeeCpfCnpj", payee.getCpfCnpj());
		// @formatter:on
	}

	private void addCreatePayeeFeeSchemaParameters(StringBuilder requestUri, Split split) {
		// @formatter:off
        addUriParameter(requestUri, "splitFixed", split.getSplitFixed());
        addUriParameter(requestUri, "splitVariable", split.getSplitVariable());
        addUriParameter(requestUri, "splitTriggerAmount", split.getSplitTriggerAmount());
		// @formatter:on
	}
}
