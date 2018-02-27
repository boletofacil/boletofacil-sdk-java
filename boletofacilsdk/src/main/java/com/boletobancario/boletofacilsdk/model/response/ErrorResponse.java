package com.boletobancario.boletofacilsdk.model.response;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "result")
public class ErrorResponse extends BaseResponse {
	private String errorMessage;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
