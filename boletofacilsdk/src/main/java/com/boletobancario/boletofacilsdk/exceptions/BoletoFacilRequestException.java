package com.boletobancario.boletofacilsdk.exceptions;

import com.boletobancario.boletofacilsdk.model.response.ErrorResponse;

public class BoletoFacilRequestException extends BoletoFacilException {

	private static final long serialVersionUID = 1L;

	public BoletoFacilRequestException(String message) {
		super(message);
	}

	public BoletoFacilRequestException(int httpStatusCode, String responseBody) {
		super("Erro na requisição (HTTP Code " + httpStatusCode + "): " + responseBody);
		this.httpStatusCode = httpStatusCode;
	}

	public BoletoFacilRequestException(int httpStatusCode, ErrorResponse error) {
		super("Erro na requisição (HTTP Code " + httpStatusCode + "): " + error.getErrorMessage());
		this.httpStatusCode = httpStatusCode;
		this.error = error;
	}

	private int httpStatusCode;

	public int getHttpStatusCode() {
		return httpStatusCode;
	}

	protected void setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
	}

	private ErrorResponse error;

	public ErrorResponse getError() {
		return error;
	}
}
