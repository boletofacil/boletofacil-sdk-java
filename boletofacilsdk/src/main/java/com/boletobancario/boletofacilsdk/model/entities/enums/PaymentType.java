package com.boletobancario.boletofacilsdk.model.entities.enums;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public enum PaymentType {
	BOLETO,
	CREDIT_CARD,
	INSTALLMENT_CREDIT_CARD;

	public static List<PaymentType> stringToPaymentTypeList(String paymentTypes) {
		if (paymentTypes == null) {
			return null;
		}
		String types = paymentTypes.toUpperCase().replaceAll(" ", "");
		return PaymentType.stringListToPaymentTypeList(Arrays.asList(types.split(",")));
	}

	public static String paymentTypeListToString(List<PaymentType> paymentTypes) {
		return paymentTypes == null || paymentTypes.isEmpty() ? null : join(enumListToStringSet(paymentTypes), ",");
	}

	private static List<PaymentType> stringListToPaymentTypeList(List<String> stringList) {
		Set<PaymentType> set = EnumSet.noneOf(PaymentType.class);
		for (String s : stringList) {
			if (!s.isEmpty()) {
				set.add(PaymentType.valueOf(s));
			}
		}
		return new ArrayList<>(set);
	}

	private static Set<String> enumListToStringSet(List<PaymentType> enumList) {
		Set<PaymentType> enumSet = EnumSet.noneOf(PaymentType.class);
		enumSet.addAll(enumList);
		Set<String> result = new HashSet<>();
		for (PaymentType enumItem : enumSet) {
			result.add(enumItem.name());
		}
		return result;
	}

	private static String join(Set<String> set, String connector) {
		StringBuilder builder = new StringBuilder();
		for (String s : set) {
			builder.append(s);
			builder.append(connector);
		}
		return builder.toString().substring(0, builder.length() - connector.length());
	}
}
