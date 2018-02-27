package com.boletobancario.boletofacilsdk.exceptions;

public class BoletoFacilException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public BoletoFacilException() {
	}

	public BoletoFacilException(String message) {
		super(message);
	}

	public BoletoFacilException(String message, Exception e) {
		super(message, e);
	}
}
