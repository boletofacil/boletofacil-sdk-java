package com.boletobancario.boletofacilsdk.model.entities;

import java.math.BigDecimal;

public class Split extends BaseEntity {
	private BigDecimal splitFixed = BigDecimal.valueOf(0);
	private BigDecimal splitVariable = BigDecimal.valueOf(0);
	private BigDecimal splitTriggerAmount = BigDecimal.valueOf(0);

	public BigDecimal getSplitFixed() {
		return splitFixed;
	}

	public void setSplitFixed(BigDecimal splitFixed) {
		this.splitFixed = splitFixed;
	}

	public BigDecimal getSplitVariable() {
		return splitVariable;
	}

	public void setSplitVariable(BigDecimal splitVariable) {
		this.splitVariable = splitVariable;
	}

	public BigDecimal getSplitTriggerAmount() {
		return splitTriggerAmount;
	}

	public void setSplitTriggerAmount(BigDecimal splitTriggerAmount) {
		this.splitTriggerAmount = splitTriggerAmount;
	}
}
