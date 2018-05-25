package com.boletobancario.boletofacilsdk.model.request;

import java.util.Calendar;

public class ListChargesDates extends BaseRequest {
	private Calendar beginDueDate;
	private Calendar endDueDate;
	private Calendar beginPaymentDate;
	private Calendar endPaymentDate;
	private Calendar beginPaymentConfirmation;
	private Calendar endPaymentConfirmation;

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

	public Calendar getBeginPaymentConfirmation() {
		return beginPaymentConfirmation;
	}

	public void setBeginPaymentConfirmation(Calendar beginPaymentConfirmation) {
		this.beginPaymentConfirmation = beginPaymentConfirmation;
	}

	public Calendar getEndPaymentConfirmation() {
		return endPaymentConfirmation;
	}

	public void setEndPaymentConfirmation(Calendar endPaymentConfirmation) {
		this.endPaymentConfirmation = endPaymentConfirmation;
	}
}
