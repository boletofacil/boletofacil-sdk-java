package com.boletobancario.boletofacilsdk.model.request;

import java.util.Calendar;

public class ListChargesDates extends BaseRequest {
	private Calendar beginDueDate;
	private Calendar endDueDate;
	private Calendar beginPaymentDate;
	private Calendar endPaymentDate;

	public Calendar getBeginDueDate() {
		return beginDueDate;
	}

	public void setBeginDueDate(Calendar beginDueDate) {
		this.beginDueDate = beginDueDate;
	}

	public Calendar getEndDueDate() {
		return endDueDate;
	}

	public void setEndDueDate(Calendar endDueDate) {
		this.endDueDate = endDueDate;
	}

	public Calendar getBeginPaymentDate() {
		return beginPaymentDate;
	}

	public void setBeginPaymentDate(Calendar beginPaymentDate) {
		this.beginPaymentDate = beginPaymentDate;
	}

	public Calendar getEndPaymentDate() {
		return endPaymentDate;
	}

	public void setEndPaymentDate(Calendar endPaymentDate) {
		this.endPaymentDate = endPaymentDate;
	}
}
