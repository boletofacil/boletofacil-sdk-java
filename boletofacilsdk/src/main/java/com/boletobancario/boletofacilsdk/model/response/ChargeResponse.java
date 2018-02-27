package com.boletobancario.boletofacilsdk.model.response;

import javax.xml.bind.annotation.XmlRootElement;

import com.boletobancario.boletofacilsdk.model.entities.ChargeList;

@XmlRootElement(name = "result")
public class ChargeResponse extends BaseResponse {
	private ChargeList data;

	public ChargeList getData() {
		return data;
	}

	public void setData(ChargeList data) {
		this.data = data;
	}
}
