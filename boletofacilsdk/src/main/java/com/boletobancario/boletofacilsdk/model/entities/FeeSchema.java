package com.boletobancario.boletofacilsdk.model.entities;

public class FeeSchema extends BaseEntity {
	private Long id;
	private String feeSchemaToken;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFeeSchemaToken() {
		return feeSchemaToken;
	}

	public void setFeeSchemaToken(String feeSchemaToken) {
		this.feeSchemaToken = feeSchemaToken;
	}
}
