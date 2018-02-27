package com.boletobancario.boletofacilsdk.model.response;

import com.boletobancario.boletofacilsdk.model.ModelBase;

public class BaseResponse extends ModelBase {
	private Boolean success;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}
}
