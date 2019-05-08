package com.boletobancario.boletofacilsdk;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.post;
import static com.github.tomakehurst.wiremock.client.WireMock.postRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.verify;

import java.lang.reflect.Field;
import java.math.BigDecimal;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HostConfiguration;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.junit.After;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;

import com.boletobancario.boletofacilsdk.enums.BoletoFacilEnvironment;
import com.boletobancario.boletofacilsdk.exceptions.BoletoFacilRequestException;
import com.boletobancario.boletofacilsdk.exceptions.BoletoFacilTokenException;
import com.boletobancario.boletofacilsdk.model.entities.Charge;
import com.boletobancario.boletofacilsdk.model.entities.FeeSchema;
import com.boletobancario.boletofacilsdk.model.entities.Payee;
import com.boletobancario.boletofacilsdk.model.entities.PayeeBalance;
import com.boletobancario.boletofacilsdk.model.entities.Payment;
import com.boletobancario.boletofacilsdk.model.entities.Split;
import com.boletobancario.boletofacilsdk.model.entities.Transfer;
import com.boletobancario.boletofacilsdk.model.entities.enums.PaymentStatus;
import com.boletobancario.boletofacilsdk.model.entities.enums.PaymentType;
import com.boletobancario.boletofacilsdk.model.request.ListChargesDates;
import com.boletobancario.boletofacilsdk.model.response.CancelChargeResponse;
import com.boletobancario.boletofacilsdk.model.response.ChargeResponse;
import com.boletobancario.boletofacilsdk.model.response.FeeSchemaResponse;
import com.boletobancario.boletofacilsdk.model.response.FetchBalanceResponse;
import com.boletobancario.boletofacilsdk.model.response.ListChargesResponse;
import com.boletobancario.boletofacilsdk.model.response.PayeeResponse;
import com.boletobancario.boletofacilsdk.model.response.TransferResponse;
import com.github.tomakehurst.wiremock.junit.WireMockRule;

public class BoletoFacilTest extends AbstractTest {
	@Test
	public void constructor() {
		BoletoFacil boletoFacil = new BoletoFacil(BoletoFacilEnvironment.SANDBOX, "ABC");

		Assert.assertNotNull(boletoFacil);
		Assert.assertEquals(BoletoFacilEnvironment.SANDBOX, boletoFacil.getBoletoFacilEnvironment());
		Assert.assertEquals("ABC", boletoFacil.getToken());
	}

	@Test(expected = BoletoFacilTokenException.class)
	public void constructorException() {
		new BoletoFacil(BoletoFacilEnvironment.SANDBOX, "");
	}

	@Test
	public void issueChargeUniqueMandatoryFields() {
		expectGetOk("/issue-charge", "issueChargeUnique.txt");
		Charge charge = getCharge();

		BoletoFacil boletoFacil = getBoletoFacil();
		ChargeResponse response = boletoFacil.issueCharge(charge);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());
		Assert.assertNotNull(response.getData());
		Assert.assertTrue(response.getData().getCharges().get(0) instanceof Charge);
		Assert.assertEquals("101", response.getData().getCharges().get(0).getCode());
		assertDate(getStartDate(), response.getData().getCharges().get(0).getDueDate());
		Assert.assertEquals(getBaseUrl(),
		        response.getData().getCharges().get(0).getLink().substring(0, getBaseUrl().length()));
		Assert.assertEquals("03399.63290 64000.001014 00236.601027 8 67150000025000",
		        response.getData().getCharges().get(0).getPayNumber());

		verifyGet("/issue-charge");
	}

	@Test
	public void issueChargeCarnet() {
		expectGetOk("/issue-charge", "issueChargeCarnet.txt");
		Charge charge = getCharge();
		charge.setInstallments(3);

		BoletoFacil boletoFacil = getBoletoFacil();
		ChargeResponse response = boletoFacil.issueCharge(charge);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());
		Assert.assertEquals(3, response.getData().getCharges().size());
		Assert.assertTrue(response.getData().getCharges().get(0) instanceof Charge);
		Assert.assertTrue(response.getData().getCharges().get(1) instanceof Charge);
		Assert.assertTrue(response.getData().getCharges().get(2) instanceof Charge);
		Assert.assertEquals("101", response.getData().getCharges().get(0).getCode());
		Assert.assertEquals("102", response.getData().getCharges().get(1).getCode());
		Assert.assertEquals("103", response.getData().getCharges().get(2).getCode());
		assertDate(getStartDate(), response.getData().getCharges().get(0).getDueDate());
		assertDate(getStartDateAddMonths(1), response.getData().getCharges().get(1).getDueDate());
		assertDate(getStartDateAddMonths(2), response.getData().getCharges().get(2).getDueDate());
		Assert.assertEquals(getBaseUrl(),
		        response.getData().getCharges().get(0).getLink().substring(0, getBaseUrl().length()));
		Assert.assertEquals(getBaseUrl(),
		        response.getData().getCharges().get(1).getLink().substring(0, getBaseUrl().length()));
		Assert.assertEquals(getBaseUrl(),
		        response.getData().getCharges().get(2).getLink().substring(0, getBaseUrl().length()));
		Assert.assertEquals("03399.63290 64000.001014 00236.601027 8 67150000004115",
		        response.getData().getCharges().get(0).getPayNumber());
		Assert.assertEquals("03399.63290 64000.001014 00236.601027 8 67250000004115",
		        response.getData().getCharges().get(1).getPayNumber());
		Assert.assertEquals("03399.63290 64000.001014 00236.601027 8 67350000004115",
		        response.getData().getCharges().get(2).getPayNumber());

		verifyGet("/issue-charge");
	}

	@Test
	@Ignore
	public void issueChargeWithProxy() {
		expectGetOkWithProxy("/issue-charge", "issueChargeUnique.txt");
		Charge charge = getCharge();

		BoletoFacil boletoFacil = getBoletoFacil();
		boletoFacil.setProxy("http://localhost", 9089, new UsernamePasswordCredentials("username", "password"));
		ChargeResponse response = boletoFacil.issueCharge(charge);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());
		Assert.assertNotNull(response.getData());
		Assert.assertTrue(response.getData().getCharges().get(0) instanceof Charge);
		Assert.assertEquals("101", response.getData().getCharges().get(0).getCode());
		assertDate(getStartDate(), response.getData().getCharges().get(0).getDueDate());
		Assert.assertEquals(getBaseUrl(),
		        response.getData().getCharges().get(0).getLink().substring(0, getBaseUrl().length()));
		Assert.assertEquals("03399.63290 64000.001014 00236.601027 8 67150000025000",
		        response.getData().getCharges().get(0).getPayNumber());

		verifyGet("/issue-charge");
	}

	@Test
	public void issueChargeWithCreditCardStoreAndCreditCardId() {
		expectGetOk("/issue-charge", "issueChargeWithCreditCardId.txt");
		Charge charge = getCharge();
		charge.setCreditCardStore(true);
		charge.setCreditCardId("XPTO-1234");

		BoletoFacil boletoFacil = getBoletoFacil();
		ChargeResponse response = boletoFacil.issueCharge(charge);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());
		Assert.assertNotNull(response.getData());
		Assert.assertTrue(response.getData().getCharges().get(0) instanceof Charge);
		Assert.assertEquals("101", response.getData().getCharges().get(0).getCode());
		assertDate(getStartDate(), response.getData().getCharges().get(0).getDueDate());
		Assert.assertEquals(getBaseUrl(),
		        response.getData().getCharges().get(0).getLink().substring(0, getBaseUrl().length()));
		Assert.assertEquals("03399.63290 64000.001014 00236.601027 8 67150000025000",
		        response.getData().getCharges().get(0).getPayNumber());
		Assert.assertNotNull(response.getData().getCharges().get(0).getPayments());

		Payment payment = response.getData().getCharges().get(0).getPayments().get(0);
		Assert.assertEquals(new BigDecimal("123.45"), payment.getAmount());
		Assert.assertEquals("XPTO-1234", payment.getCreditCardId());
		assertDate(getStartDate(), payment.getDate());
		Assert.assertEquals(new BigDecimal("4.56"), payment.getFee());
		Assert.assertEquals(Long.valueOf("123456"), payment.getId());
		Assert.assertEquals(PaymentStatus.CONFIRMED, payment.getStatus());
		Assert.assertEquals(PaymentType.CREDIT_CARD, payment.getType());

		verifyGet("/issue-charge");
	}

	@Test
	public void issueChargeWithCreditCardId() {
		expectGetOk("/issue-charge", "issueChargeUnique.txt");
		Charge charge = getCharge();
		charge.setCreditCardId("XPTO-1234");

		BoletoFacil boletoFacil = getBoletoFacil();
		ChargeResponse response = boletoFacil.issueCharge(charge);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());
		Assert.assertNotNull(response.getData());
		Assert.assertTrue(response.getData().getCharges().get(0) instanceof Charge);
		Assert.assertEquals("101", response.getData().getCharges().get(0).getCode());
		assertDate(getStartDate(), response.getData().getCharges().get(0).getDueDate());
		Assert.assertEquals(getBaseUrl(),
		        response.getData().getCharges().get(0).getLink().substring(0, getBaseUrl().length()));
		Assert.assertEquals("03399.63290 64000.001014 00236.601027 8 67150000025000",
		        response.getData().getCharges().get(0).getPayNumber());

		verifyGet("/issue-charge");
	}

	@Test
	public void issueChargeWithReferralToken() {
		expectGetOk("/issue-charge", "issueChargeUnique.txt");
		Charge charge = getCharge();
		charge.setReferralToken("TESTE_REFERRAL_TOKEN");

		BoletoFacil boletoFacil = getBoletoFacil();
		ChargeResponse response = boletoFacil.issueCharge(charge);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());
		Assert.assertNotNull(response.getData());
		Assert.assertTrue(response.getData().getCharges().get(0) instanceof Charge);
		Assert.assertEquals("101", response.getData().getCharges().get(0).getCode());
		assertDate(getStartDate(), response.getData().getCharges().get(0).getDueDate());
		Assert.assertEquals(getBaseUrl(),
		        response.getData().getCharges().get(0).getLink().substring(0, getBaseUrl().length()));
		Assert.assertEquals("03399.63290 64000.001014 00236.601027 8 67150000025000",
		        response.getData().getCharges().get(0).getPayNumber());

		verifyGet("/issue-charge");
	}

	@Test(expected = BoletoFacilRequestException.class)
	public void issueChargeErrorInvalidAmount() {
		expectGetError("/issue-charge", "issueChargeErrorInvalidAmount.txt");
		Charge charge = getCharge();
		charge.setAmount(BigDecimal.ZERO);

		BoletoFacil boletoFacil = getBoletoFacil();
		boletoFacil.issueCharge(charge);
	}

	@Test(expected = BoletoFacilRequestException.class)
	public void issueChargeErrorNoPayer() {
		expectGetError("/issue-charge", "issueChargeErrorNullPayer.txt");
		Charge charge = getCharge();
		charge.setPayer(null);

		BoletoFacil boletoFacil = getBoletoFacil();
		boletoFacil.issueCharge(charge);
	}

	@Test
	public void requestTransferPartialBalance() {
		expectGetOk("/request-transfer", "requestTransfer.txt");
		Transfer transfer = getTransfer();
		transfer.setAmount(BigDecimal.valueOf(150.00));

		BoletoFacil boletoFacil = getBoletoFacil();
		TransferResponse response = boletoFacil.requestTransfer(transfer);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());

		verifyGet("/request-transfer");
	}

	@Test
	public void requestTransferFullBalance() {
		expectGetOk("/request-transfer", "requestTransfer.txt");
		Transfer transfer = getTransfer();

		BoletoFacil boletoFacil = getBoletoFacil();
		TransferResponse response = boletoFacil.requestTransfer(transfer);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());

		verifyGet("/request-transfer");
	}

	@Test
	public void listCharges() {
		expectGetOk("/list-charges", "listCharges.txt");
		ListChargesDates dates = getListChargesDates();

		BoletoFacil boletoFacil = getBoletoFacil();
		ListChargesResponse response = boletoFacil.listCharges(dates);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());
		Assert.assertNotNull(response.getData());

		Assert.assertEquals(3, response.getData().getCharges().size());

		Assert.assertTrue(response.getData().getCharges().get(0) instanceof Charge);
		Assert.assertEquals("101", response.getData().getCharges().get(0).getCode());
		assertDate(getStartDate(), response.getData().getCharges().get(0).getDueDate());
		Assert.assertEquals(getBaseUrl(),
		        response.getData().getCharges().get(0).getLink().substring(0, getBaseUrl().length()));
		Assert.assertEquals("03399.63290 64000.001014 00236.601027 8 67150000025000",
		        response.getData().getCharges().get(0).getPayNumber());
		Assert.assertEquals(1, response.getData().getCharges().get(0).getPayments().size());
		Assert.assertTrue(response.getData().getCharges().get(0).getPayments().get(0) instanceof Payment);
		Assert.assertEquals(1113123, response.getData().getCharges().get(0).getPayments().get(0).getId().longValue());
		Assert.assertEquals(BigDecimal.valueOf(163.9),
		        response.getData().getCharges().get(0).getPayments().get(0).getAmount());
		assertDate(getStartDate(), response.getData().getCharges().get(0).getPayments().get(0).getDate());
		Assert.assertEquals(BigDecimal.valueOf(4.34),
		        response.getData().getCharges().get(0).getPayments().get(0).getFee());
		Assert.assertEquals(PaymentType.BOLETO, response.getData().getCharges().get(0).getPayments().get(0).getType());
		Assert.assertEquals(PaymentStatus.CONFIRMED,
		        response.getData().getCharges().get(0).getPayments().get(0).getStatus());

		Assert.assertTrue(response.getData().getCharges().get(1) instanceof Charge);
		Assert.assertEquals("102", response.getData().getCharges().get(1).getCode());
		assertDate(getStartDate(), response.getData().getCharges().get(1).getDueDate());
		Assert.assertEquals(getBaseUrl(),
		        response.getData().getCharges().get(1).getLink().substring(0, getBaseUrl().length()));
		Assert.assertEquals("03399.63290 64000.001024 00236.601027 3 67150000015000",
		        response.getData().getCharges().get(1).getPayNumber());
		Assert.assertEquals(2, response.getData().getCharges().get(1).getPayments().size());
		Assert.assertTrue(response.getData().getCharges().get(1).getPayments().get(0) instanceof Payment);
		Assert.assertEquals(1113124, response.getData().getCharges().get(1).getPayments().get(0).getId().longValue());
		Assert.assertEquals(BigDecimal.valueOf(1141.4),
		        response.getData().getCharges().get(1).getPayments().get(0).getAmount());
		assertDate(getStartDate(), response.getData().getCharges().get(1).getPayments().get(0).getDate());
		Assert.assertEquals(BigDecimal.valueOf(27.8),
		        response.getData().getCharges().get(1).getPayments().get(0).getFee());
		Assert.assertEquals(PaymentType.BOLETO, response.getData().getCharges().get(1).getPayments().get(0).getType());
		Assert.assertEquals(PaymentStatus.CONFIRMED,
		        response.getData().getCharges().get(1).getPayments().get(0).getStatus());
		Assert.assertTrue(response.getData().getCharges().get(1).getPayments().get(1) instanceof Payment);
		Assert.assertEquals(1113125, response.getData().getCharges().get(1).getPayments().get(1).getId().longValue());
		Assert.assertEquals(BigDecimal.valueOf(1141.5),
		        response.getData().getCharges().get(1).getPayments().get(1).getAmount());
		assertDate(getStartDate(), response.getData().getCharges().get(1).getPayments().get(1).getDate());
		Assert.assertEquals(BigDecimal.valueOf(27.85),
		        response.getData().getCharges().get(1).getPayments().get(1).getFee());
		Assert.assertEquals(PaymentType.CREDIT_CARD,
		        response.getData().getCharges().get(1).getPayments().get(1).getType());
		Assert.assertEquals(PaymentStatus.CONFIRMED,
		        response.getData().getCharges().get(1).getPayments().get(1).getStatus());

		Assert.assertEquals("103", response.getData().getCharges().get(2).getCode());
		assertDate(getStartDate(), response.getData().getCharges().get(2).getDueDate());
		Assert.assertEquals(getBaseUrl(),
		        response.getData().getCharges().get(2).getLink().substring(0, getBaseUrl().length()));
		Assert.assertEquals("03399.63290 64000.001024 00236.601027 3 67150000014000",
		        response.getData().getCharges().get(2).getPayNumber());
		Assert.assertEquals(0, response.getData().getCharges().get(2).getPayments().size());

		verifyGet("/list-charges");
	}

	@Test(expected = BoletoFacilRequestException.class)
	public void listChargesErrorNoDatesInformed() {
		expectGetError("/list-charges", "listChargesError.txt");
		ListChargesDates dates = getListChargesDates();

		BoletoFacil boletoFacil = getBoletoFacil();
		boletoFacil.listCharges(dates);
	}

	@Test
	public void fetchBalance() {
		expectGetOk("/fetch-balance", "fetchBalance.txt");

		BoletoFacil boletoFacil = getBoletoFacil();
		FetchBalanceResponse response = boletoFacil.fetchBalance();

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());
		Assert.assertNotNull(response.getData());
		Assert.assertTrue(response.getData() instanceof PayeeBalance);
		Assert.assertEquals(BigDecimal.valueOf(100), response.getData().getBalance());
		Assert.assertEquals(BigDecimal.valueOf(30), response.getData().getWithheldBalance());
		Assert.assertEquals(BigDecimal.valueOf(70), response.getData().getTransferableBalance());

		verifyGet("/fetch-balance");
	}

	@Test
	public void cancelCharge() {
		expectGetOk("/cancel-charge", "cancelCharge.txt");
		Charge charge = getCharge();
		charge.setCode("12345678");

		BoletoFacil boletoFacil = getBoletoFacil();
		CancelChargeResponse response = boletoFacil.cancelCharge(charge);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());

		verifyGet("/cancel-charge");
	}

	@Test(expected = BoletoFacilRequestException.class)
	public void cancelChargeError() {
		expectGetError("/cancel-charge", "cancelChargeError.txt");
		Charge charge = getCharge();
		charge.setCode("0");

		BoletoFacil boletoFacil = getBoletoFacil();
		boletoFacil.cancelCharge(charge);
	}

	@Test
	public void createPayeeMandatoryFields() {
		expectPostOk("/create-payee", "createPayee.txt");
		Payee payee = getPayee();

		BoletoFacil boletoFacil = getBoletoFacil();
		PayeeResponse response = boletoFacil.createPayee(payee);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());
		Assert.assertNotNull(response.getData());
		Assert.assertTrue(response.getData() instanceof Payee);
		Assert.assertEquals("22FAC22222EE2D22222222ADDDDDBEF38B222222D22D22E2", response.getData().getToken());

		verifyPost("/create-payee");
	}

	@Test
	public void createPayeeAutoApprovedAndEmailOptOut() {
		expectPostOk("/create-payee", "createPayee.txt");
		Payee payee = getPayee();
		payee.setEmailOptOut(true);
		payee.setAutoApprove(true);

		BoletoFacil boletoFacil = getBoletoFacil();
		PayeeResponse response = boletoFacil.createPayee(payee);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());
		Assert.assertNotNull(response.getData());
		Assert.assertTrue(response.getData() instanceof Payee);
		Assert.assertEquals("22FAC22222EE2D22222222ADDDDDBEF38B222222D22D22E2", response.getData().getToken());

		verifyPost("/create-payee");
	}

	@Test(expected = BoletoFacilRequestException.class)
	@Ignore
	public void createPayeeMethodNotAllowedException() {
		expectGetError("/create-payee", "cancelChargeError.txt");
		Payee payee = getPayee();

		BoletoFacil boletoFacil = getBoletoFacil();
		boletoFacil.createPayee(payee);
	}

	@Test
	public void createPayeeFeeSchemaSplitFixed() {
		expectGetOk("/create-payee-fee-schema", "feeSchema.txt");
		Split split = getSplit();
		split.setSplitFixed(BigDecimal.valueOf(5));

		BoletoFacil boletoFacil = getBoletoFacil();
		FeeSchemaResponse response = boletoFacil.createPayeeFeeSchema(split);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());
		Assert.assertNotNull(response.getData());
		Assert.assertTrue(response.getData() instanceof FeeSchema);
		Assert.assertEquals(123, response.getData().getId().longValue());
		Assert.assertEquals("37515135CBD4FA0176F77F944C15F064CB714C75FE23685B9EC84693A05B10F783FDC05C31BF5800",
		        response.getData().getFeeSchemaToken());

		verifyGet("/create-payee-fee-schema");
	}

	@Test
	public void createPayeeFeeSchemaSplitVariable() {
		expectGetOk("/create-payee-fee-schema", "feeSchema.txt");
		Split split = getSplit();
		split.setSplitVariable(BigDecimal.valueOf(25));

		BoletoFacil boletoFacil = getBoletoFacil();
		FeeSchemaResponse response = boletoFacil.createPayeeFeeSchema(split);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());
		Assert.assertNotNull(response.getData());
		Assert.assertTrue(response.getData() instanceof FeeSchema);
		Assert.assertEquals(123, response.getData().getId().longValue());
		Assert.assertEquals("37515135CBD4FA0176F77F944C15F064CB714C75FE23685B9EC84693A05B10F783FDC05C31BF5800",
		        response.getData().getFeeSchemaToken());

		verifyGet("/create-payee-fee-schema");
	}

	@Test
	public void getPayeeStatus() {
		expectGetOk("/get-payee-status", "payeeStatus.txt");
		Payee payee = getPayee();

		BoletoFacil boletoFacil = getBoletoFacil();
		PayeeResponse response = boletoFacil.getPayeeStatus(payee);

		Assert.assertNotNull(response);
		Assert.assertTrue(response.getSuccess());
		Assert.assertNotNull(response.getData());
		Assert.assertTrue(response.getData() instanceof Payee);
		Assert.assertEquals("Aprovado", response.getData().getStatus());

		verifyGet("/get-payee-status");
	}

	@Test(expected = BoletoFacilRequestException.class)
	public void getPayeeStatusInvalidPayeeException() {
		expectGetError("/get-payee-status", "payeeStatus.txt");
		Payee payee = getPayee();
		payee.setCpfCnpj("12345678000199");

		BoletoFacil boletoFacil = getBoletoFacil();
		boletoFacil.getPayeeStatus(payee);
	}

	@Test
	public void setProxy()
	        throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		BoletoFacil boletoFacil = new BoletoFacil(BoletoFacilEnvironment.SANDBOX, "ABC");

		Field httpClient = boletoFacil.getClass().getSuperclass().getDeclaredField("HTTP_CLIENT");
		httpClient.setAccessible(true);

		Assert.assertNull(((HttpClient) httpClient.get(boletoFacil)).getHostConfiguration().getProxyHost());
		Assert.assertNull(((HttpClient) httpClient.get(boletoFacil)).getState().getProxyCredentials(AuthScope.ANY));

		boletoFacil.setProxy("http://localhost");

		Assert.assertNotNull(((HttpClient) httpClient.get(boletoFacil)).getHostConfiguration().getProxyHost());
		Assert.assertEquals("http://localhost",
		        ((HttpClient) httpClient.get(boletoFacil)).getHostConfiguration().getProxyHost());
		Assert.assertEquals(80, ((HttpClient) httpClient.get(boletoFacil)).getHostConfiguration().getProxyPort());
		Assert.assertNull(((HttpClient) httpClient.get(boletoFacil)).getState().getCredentials(AuthScope.ANY));
	}

	@Test
	public void setProxyWithPort()
	        throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		BoletoFacil boletoFacil = new BoletoFacil(BoletoFacilEnvironment.SANDBOX, "ABC");

		Field httpClient = boletoFacil.getClass().getSuperclass().getDeclaredField("HTTP_CLIENT");
		httpClient.setAccessible(true);

		Assert.assertNull(((HttpClient) httpClient.get(boletoFacil)).getHostConfiguration().getProxyHost());
		Assert.assertNull(((HttpClient) httpClient.get(boletoFacil)).getState().getProxyCredentials(AuthScope.ANY));

		boletoFacil.setProxy("http://localhost", 8088);

		Assert.assertNotNull(((HttpClient) httpClient.get(boletoFacil)).getHostConfiguration().getProxyHost());
		Assert.assertEquals("http://localhost",
		        ((HttpClient) httpClient.get(boletoFacil)).getHostConfiguration().getProxyHost());
		Assert.assertEquals(8088, ((HttpClient) httpClient.get(boletoFacil)).getHostConfiguration().getProxyPort());
		Assert.assertNull(((HttpClient) httpClient.get(boletoFacil)).getState().getCredentials(AuthScope.ANY));
	}

	@Test
	public void setProxyWithPortAndCredentials()
	        throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
		BoletoFacil boletoFacil = new BoletoFacil(BoletoFacilEnvironment.SANDBOX, "ABC");

		Field httpClient = boletoFacil.getClass().getSuperclass().getDeclaredField("HTTP_CLIENT");
		httpClient.setAccessible(true);

		Assert.assertNull(((HttpClient) httpClient.get(boletoFacil)).getHostConfiguration().getProxyHost());
		Assert.assertNull(((HttpClient) httpClient.get(boletoFacil)).getState().getProxyCredentials(AuthScope.ANY));

		Credentials credentials = new UsernamePasswordCredentials("username", "password");
		boletoFacil.setProxy("http://localhost", 8088, credentials);

		Assert.assertNotNull(((HttpClient) httpClient.get(boletoFacil)).getHostConfiguration().getProxyHost());
		Assert.assertEquals("http://localhost",
		        ((HttpClient) httpClient.get(boletoFacil)).getHostConfiguration().getProxyHost());
		Assert.assertEquals(8088, ((HttpClient) httpClient.get(boletoFacil)).getHostConfiguration().getProxyPort());
		Assert.assertNotNull(((HttpClient) httpClient.get(boletoFacil)).getState().getProxyCredentials(AuthScope.ANY));
		Assert.assertEquals(credentials,
		        ((HttpClient) httpClient.get(boletoFacil)).getState().getProxyCredentials(AuthScope.ANY));
	}

	private void expectGetOk(String urlPath, String fileName) {
		stubFor(get(urlPathEqualTo(urlPath)).withQueryParam("token", equalTo("ABC"))
		        .withQueryParam("responseType", equalTo("JSON"))
		        .willReturn(aResponse().withStatus(200).withBodyFile(fileName)));
	}

	private void expectPostOk(String urlPath, String fileName) {
		stubFor(post(urlPathEqualTo(urlPath)).withQueryParam("token", equalTo("ABC"))
		        .withQueryParam("responseType", equalTo("JSON"))
		        .willReturn(aResponse().withStatus(200).withBodyFile(fileName)));
		// stubFor(post(urlPathEqualTo(urlPath)).withRequestBody(matching(".*token=ABC.*"))
		// .willReturn(aResponse().withStatus(200).withBodyFile(fileName)));
	}

	private void expectGetError(String urlPath, String fileName) {
		stubFor(get(urlPathEqualTo(urlPath)).withQueryParam("token", equalTo("ABC"))
		        .withQueryParam("responseType", equalTo("JSON"))
		        .willReturn(aResponse().withStatus(422).withBodyFile(fileName)));
	}

	private void expectGetOkWithProxy(String urlPath, String fileName) {
		stubFor(get(urlPathEqualTo(urlPath)).withQueryParam("token", equalTo("ABC"))
		        .withQueryParam("responseType", equalTo("JSON"))
		        .willReturn(aResponse().withStatus(200).withBodyFile(fileName).proxiedFrom("http://localhost:9089")));
	}

	private void verifyGet(String urlPath) {
		verify(getRequestedFor(urlPathEqualTo(urlPath)).withQueryParam("token", equalTo("ABC"))
		        .withQueryParam("responseType", equalTo("JSON")));
	}

	private void verifyPost(String urlPath) {
		verify(postRequestedFor(urlPathEqualTo(urlPath)).withQueryParam("token", equalTo("ABC"))
		        .withQueryParam("responseType", equalTo("JSON")));
	}

	private BoletoFacil getBoletoFacil() {
		return new BoletoFacil(BoletoFacilEnvironment.UNIT_TESTS, "ABC");
	}

	@After
	public void tearDown()
	        throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		BoletoFacil boletoFacil = new BoletoFacil(BoletoFacilEnvironment.SANDBOX, "ABC");

		Field httpClient = boletoFacil.getClass().getSuperclass().getDeclaredField("HTTP_CLIENT");
		httpClient.setAccessible(true);
		((HttpClient) httpClient.get(boletoFacil)).setHostConfiguration(new HostConfiguration());
		((HttpClient) httpClient.get(boletoFacil)).getState().setCredentials(AuthScope.ANY, null);
	}

	@Rule
	public WireMockRule wireMockRule = new WireMockRule(9089);
}
