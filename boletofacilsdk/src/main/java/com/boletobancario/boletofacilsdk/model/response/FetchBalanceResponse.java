package com.boletobancario.boletofacilsdk.model.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.boletobancario.boletofacilsdk.model.entities.PayeeBalance;

@XmlRootElement(name = "result")
public class FetchBalanceResponse extends BaseResponse {
	private PayeeBalance data;

	public PayeeBalance getData() {
		return data;
	}

	public void setData(PayeeBalance data) {
		this.data = data;
	}
}
