package com.boletobancario.boletofacilsdk.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;

import com.boletobancario.boletofacilsdk.BoletoFacil;
import com.boletobancario.boletofacilsdk.enums.BoletoFacilEnvironment;
import com.boletobancario.boletofacilsdk.enums.ResponseType;
import com.boletobancario.boletofacilsdk.exceptions.BoletoFacilException;
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
import com.boletobancario.boletofacilsdk.model.response.BaseResponse;
import com.boletobancario.boletofacilsdk.model.response.CancelChargeResponse;
import com.boletobancario.boletofacilsdk.model.response.ChargeResponse;
import com.boletobancario.boletofacilsdk.model.response.FeeSchemaResponse;
import com.boletobancario.boletofacilsdk.model.response.FetchBalanceResponse;
import com.boletobancario.boletofacilsdk.model.response.ListChargesResponse;
import com.boletobancario.boletofacilsdk.model.response.PayeeResponse;
import com.boletobancario.boletofacilsdk.model.response.TransferResponse;

public class BoletoFacilClient {
	private BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	private String readInput() throws IOException {
		br = new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}

	private BoletoFacil boletoFacil;

	private void clearScreen() throws IOException {
		final String os = System.getProperty("os.name");

		if (os.contains("Windows")) {
			Runtime.getRuntime().exec("cls");
		} else {
			Runtime.getRuntime().exec("clear");
		}
	}

	private void mainMenu() throws IOException {
		mainMenu("");
	}

	public void mainMenu(String token) throws IOException {
		clearScreen();
		System.out.println("=========================================");
		System.out.println(" Geração de cobrança no ambiente Sandbox ");
		System.out.println("=========================================");

		if (boletoFacil == null) {
			while (StringUtils.isBlank(token)) {
				System.out.println("");
				System.out.println("Digite o token do favorecido:");
				token = readInput();
			}

			boletoFacil = new BoletoFacil(BoletoFacilEnvironment.SANDBOX, token);
		}

		boolean validOption = false;
		while (!validOption) {
			int key = menuOptions();
			validOption = true;

			switch (key) {
			case 1: // 1
				issueCharge();
				break;
			case 2: // 2
				fetchBalance();
				break;
			case 3: // 3
				requestTransfer();
				break;
			case 4: // 4
				cancelCharge();
				break;
			case 5: // 5
				listCharges();
				break;
			case 6: // 6
				createPayee();
				break;
			case 7: // 7
				createPayeeFeeSchema();
				break;
			case 8: // 8
				getPayeeStatus();
				break;
			case 9: // 9
				System.exit(0);
				break;
			default:
				validOption = false;
				break;
			}
		}
	}

	private void issueCharge() throws IOException {
		Payer payer = new Payer();
		payer.setName("Pagador teste - SDK Java");
		payer.setCpfCnpj("03423577193");

		Charge charge = new Charge();
		charge.setDescription("Cobrança teste gerada pelo SDK Java");
		charge.setAmount(BigDecimal.valueOf(176.45));
		charge.setPayer(payer);

		try {
			ChargeResponse response = boletoFacil.issueCharge(charge);
			showObjectResponseHeader();
			for (Charge c : response.getData().getCharges()) {
				System.out.println("");
				System.out.println(c);
			}
			showResponseSerialized(response);
		} catch (BoletoFacilException e) {
			handleException(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			doneMessage();
		}
	}

	private void fetchBalance() throws IOException {
		try {
			FetchBalanceResponse response = boletoFacil.fetchBalance(ResponseType.XML);
			showObjectResponseHeader();
			System.out.println(response.getData());
			showResponseSerialized(response);
		} catch (BoletoFacilException e) {
			handleException(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			doneMessage();
		}
	}

	private void requestTransfer() throws IOException {
		System.out.println("");
		System.out.println("Entre o valor (ou deixe em branco para transferir todo o saldo disponível):");
		String amountString = br.readLine();

		Transfer transfer = new Transfer();
		if (StringUtils.isNotBlank(amountString)) {
			transfer.setAmount(new BigDecimal(amountString));
		}

		try {
			TransferResponse response = boletoFacil.requestTransfer(transfer);
			showObjectResponseHeader();
			System.out.println(response);
			showResponseSerialized(response);
		} catch (BoletoFacilException e) {
			handleException(e);
		} finally {
			doneMessage();
		}
	}

	private void cancelCharge() throws IOException {
		String code = null;

		while (StringUtils.isBlank(code)) {
			System.out.println("");
			System.out.println("Entre o código da cobrança:");
			code = readInput();
		}

		try {
			Charge charge = new Charge();
			charge.setCode(code);
			CancelChargeResponse response = boletoFacil.cancelCharge(charge);
			showObjectResponseHeader();
			System.out.println(response);
			showResponseSerialized(response);
		} catch (BoletoFacilException e) {
			handleException(e);
		} finally {
			doneMessage();
		}
	}

	private void listCharges() throws IOException {
		Calendar endDate = Calendar.getInstance();
		endDate.add(Calendar.DAY_OF_WEEK, 5);
		ListChargesDates dates = new ListChargesDates();
		dates.setBeginDueDate(Calendar.getInstance());
		dates.setEndDueDate(endDate);

		try {
			ListChargesResponse response = boletoFacil.listCharges(dates);
			showObjectResponseHeader();
			for (Charge c : response.getData().getCharges()) {
				System.out.println("");
				System.out.println(c);
			}
			showResponseSerialized(response);
		} catch (BoletoFacilException e) {
			handleException(e);
		} finally {
			doneMessage();
		}
	}

	private void createPayee() throws IOException {
		String cpfCnpj = null;
		String email = null;

		while (StringUtils.isBlank(cpfCnpj)) {
			System.out.println("");
			System.out.println("Entre o CPF/CNPJ do novo favorecido:");
			cpfCnpj = readInput();
		}

		while (StringUtils.isBlank(email)) {
			System.out.println("");
			System.out.println("Entre o email do novo favorecido:");
			email = readInput();
		}

		Calendar birthDate = Calendar.getInstance();
		birthDate.add(Calendar.YEAR, -19);

		Person person = new Person();
		person.setName("Favorecido do SDK Java");
		person.setCpfCnpj(cpfCnpj);

		BankAccount account = new BankAccount();
		account.setBankAccountType(BankAccountType.CHECKING);
		account.setBankNumber("237");
		account.setAgencyNumber("123");
		account.setAccountNumber("4567");
		account.setAccountComplementNumber(0);

		Address address = new Address();
		address.setStreet("Rua Teste");
		address.setNumber("123");
		address.setCity("4106902");
		address.setState("PR");
		address.setPostcode("80100010");

		Payee payee = new Payee();
		payee.setName("Favorecido do SDK Java");
		payee.setCpfCnpj(cpfCnpj);
		payee.setEmail(email);
		payee.setPassword("abacate");
		payee.setBirthDate(birthDate);
		payee.setPhone("(41) 99876-5432");
		payee.setLinesOfBusiness("bla");
		payee.setAccountHolder(person);
		payee.setBankAccount(account);
		payee.setCategory(Category.OTHER);
		payee.setAddress(address);
		payee.setBusinessAreaId(1000);

		try {
			PayeeResponse response = boletoFacil.createPayee(payee);
			showObjectResponseHeader();
			System.out.println(response.getData());
			showResponseSerialized(response);
		} catch (BoletoFacilException e) {
			handleException(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			doneMessage();
		}
	}

	private void createPayeeFeeSchema() throws IOException {
		Split split = new Split();
		split.setSplitTriggerAmount(BigDecimal.valueOf(2.25));
		split.setSplitVariable(BigDecimal.valueOf(30));

		try {
			FeeSchemaResponse response = boletoFacil.createPayeeFeeSchema(split);
			showObjectResponseHeader();
			System.out.println(response.getData());
			showResponseSerialized(response);
		} catch (BoletoFacilException e) {
			handleException(e);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			doneMessage();
		}
	}

	private void getPayeeStatus() throws IOException {
		String cpfCnpj = null;

		while (StringUtils.isBlank(cpfCnpj)) {
			System.out.println("");
			System.out.println("Entre o CPF/CNPJ do favorecido desejado:");
			cpfCnpj = readInput();
		}

		Payee payee = new Payee();
		payee.setCpfCnpj(cpfCnpj);

		try {
			PayeeResponse response = boletoFacil.getPayeeStatus(payee);
			showObjectResponseHeader();
			System.out.println(response.getData());
			showResponseSerialized(response);
		} catch (BoletoFacilException e) {
			handleException(e);
		} finally {
			doneMessage();
		}
	}

	private void handleException(BoletoFacilException e) {
		System.out.println("");
		System.out.println(e.getMessage());
	}

	private void doneMessage() throws IOException {
		System.out.println("");
		System.out.println("Aperte uma tecla para voltar ao menu principal...");
		readInput();
		mainMenu();
	}

	private int menuOptions() throws IOException {
		System.out.println("");
		System.out.println("O que deseja fazer?");
		System.out.println("");
		System.out.println("1) Emitir uma cobrança");
		System.out.println("2) Consultar saldo");
		System.out.println("3) Solicitar transferência");
		System.out.println("4) Cancelar uma cobrança");
		System.out.println("5) Listar cobranças e pagamentos");
		System.out.println("6) Criar um favorecido");
		System.out.println("7) Criar esquema de taxas");
		System.out.println("8) Consultar status de favorecido");
		System.out.println("9) Encerrar o programa");
		System.out.println("");
		System.out.println("Entre a opção desejada:");
		String input = readInput();
		return StringUtils.isBlank(input) ? 0 : Integer.parseInt(input);
	}

	private void showObjectResponseHeader() {
		System.out.println("");
		System.out.println("");
		System.out.println("Resposta do servidor:");
		System.out.println("-----------------------------------------------------------");
		System.out.println("OBJETO ----------------------------------------------------");
		System.out.println("-----------------------------------------------------------");
	}

	void showResponseSerialized(BaseResponse response) {
		System.out.println("");
		System.out.println("-----------------------------------------------------------");
		System.out.println("SERIALIZADO EM JSON ---------------------------------------");
		System.out.println("-----------------------------------------------------------");
		System.out.println(response.toJson());
		System.out.println("");
		System.out.println("-----------------------------------------------------------");
		System.out.println("SERIALIZADO EM XML ----------------------------------------");
		System.out.println("-----------------------------------------------------------");
		System.out.println(response.toXml());
		System.out.println("");
	}

}
