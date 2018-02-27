package com.boletobancario.boletofacilsdk.model.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.boletobancario.boletofacilsdk.model.entities.Payee;

@XmlRootElement(name = "result")
public class PayeeResponse extends BaseResponse {
	private Payee data;

	public Payee getData() {
		return data;
	}

	public void setData(Payee data) {
		this.data = data;
	}
}
