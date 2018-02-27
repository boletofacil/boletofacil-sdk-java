package com.boletobancario.boletofacilsdk.model.entities;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import com.boletobancario.boletofacilsdk.model.ModelBase;

public class BaseEntity extends ModelBase {
	protected transient SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

	@Override
	@SuppressWarnings("rawtypes")
	public String toString() {
		StringBuilder sb = new StringBuilder();
		List<Field> fieldList = getHierarchyFields(getClass());
		for (Field field : fieldList) {
			try {
				if (!field.isSynthetic()) {
					field.setAccessible(true);
					sb.append(field.getName());
					sb.append(": ");
					if (Collection.class.isAssignableFrom(field.getType())) {
						appendCollection(sb, (List) field.get(this));
					} else {
						sb.append(fieldRepresentation(field));
					}
					sb.append(newLine());
				}
			} catch (IllegalArgumentException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}

		return sb.toString();
	}

	@SuppressWarnings("unchecked")
	private <T extends BaseEntity> List<Field> getHierarchyFields(Class<T> clazz) {
		List<Field> fieldList = new ArrayList<>();
		for (Field f : clazz.getDeclaredFields()) {
			fieldList.add(f);
		}
		while (clazz.getSuperclass() != BaseEntity.class) {
			clazz = (Class<T>) clazz.getSuperclass();
			for (Field f : clazz.getDeclaredFields()) {
				fieldList.add(f);
			}
		}
		return fieldList;
	}

	@SuppressWarnings("rawtypes")
	private void appendCollection(StringBuilder sb, List collection) {
		sb.append("[");
		if (collection != null) {
			sb.append(newLine());
			for (int i = 0; i < collection.size(); i++) {
				sb.append(collection.get(i));
				if (i < collection.size() - 1) {
					sb.append(",");
					sb.append(newLine());
				}
			}
		}
		sb.append("] ");
	}

	private Object fieldRepresentation(Field field) throws IllegalAccessException {
		return isDate(field) && field.get(this) != null ? df.format(((Calendar) field.get(this)).getTime())
		        : coalesceField(field);
	}

	private Object coalesceField(Field field) throws IllegalAccessException {
		return field.get(this) == null ? "" : field.get(this);
	}

	private String newLine() {
		return System.getProperty("line.separator");
	}

	private boolean isDate(Field field) {
		return Calendar.class.isAssignableFrom(field.getType());
	}
}
