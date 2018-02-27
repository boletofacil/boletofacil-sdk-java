package com.boletobancario.boletofacilsdk.exceptions;

public class BoletoFacilTokenException extends BoletoFacilException {

	private static final long serialVersionUID = 1L;

	public BoletoFacilTokenException(String message) {
    		super(message);
    }

	public BoletoFacilTokenException(String message, Exception e) {
		super(message, e);
	}
}
