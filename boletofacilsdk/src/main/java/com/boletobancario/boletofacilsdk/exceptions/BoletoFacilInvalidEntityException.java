package com.boletobancario.boletofacilsdk.exceptions;

import com.boletobancario.boletofacilsdk.model.ModelBase;

public class BoletoFacilInvalidEntityException extends BoletoFacilException {

	private static final long serialVersionUID = 1L;

	public BoletoFacilInvalidEntityException(ModelBase entity) {
		super(entity.getClass().getSimpleName() + " inválido.");
	}

	public BoletoFacilInvalidEntityException(ModelBase entity, Exception e) {
		super(entity.getClass().getSimpleName() + " inválido.", e);
	}
}
