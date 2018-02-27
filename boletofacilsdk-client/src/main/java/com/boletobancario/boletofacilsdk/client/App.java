package com.boletobancario.boletofacilsdk.client;

import java.io.IOException;

public class App {

	public static void main(String[] args) throws IOException {
		BoletoFacilClient client = new BoletoFacilClient();
		client.mainMenu(args.length > 0 ? args[0] : "");
	}

}
