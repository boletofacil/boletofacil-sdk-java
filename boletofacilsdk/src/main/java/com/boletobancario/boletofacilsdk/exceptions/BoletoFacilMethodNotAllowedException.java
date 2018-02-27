package com.boletobancario.boletofacilsdk.exceptions;

import org.apache.commons.httpclient.HttpStatus;

public class BoletoFacilMethodNotAllowedException extends BoletoFacilRequestException {

	private static final long serialVersionUID = 1L;

	public BoletoFacilMethodNotAllowedException(String message) {
		super(message);
		setHttpStatusCode(HttpStatus.SC_METHOD_NOT_ALLOWED);
	}
}
