package com.boletobancario.boletofacilsdk.model.entities;

import java.util.List;

public class ChargeList extends BaseEntity {
	private List<Charge> charges;

	public List<Charge> getCharges() {
		return charges;
	}

	public void setCharges(List<Charge> charges) {
		this.charges = charges;
	}
}
