package com.mendonca;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import com.mendonca.data.Actor;
import com.mendonca.data.Movie;

public class Main5 {

	public static void main(String[] args) throws IllegalAccessException {

		// com.mendonca.data.Address address = new com.mendonca.data.Address ("Main
		// Street", (short)1);
		// com.mendonca.data.Person person = new com.mendonca.data.Person("Joao", true,
		// 29, 400.777f,address);

		// String json = objectToJson(address,0);
		// System.out.println(json);

		// String json2 = objectToJson(person,0);
		// System.out.println(json2);

		Actor actor = new Actor("Gabriel Mendonca", new String[] { "Alto da comparecida", "The Good Son" });

		Actor actor87 = new Actor("Gabriel Mendonca", new String[] { "Alto da comparecida", "The Good Son" });

		Actor actor3 = new Actor("Gabriel Mendonca", new String[] { "Alto da comparecida", "The Good Son" });

		Movie movie = new Movie("Gabriel Movie", 9.8f, new String[] { "action", "drama" },
				new Actor[] { actor, actor3, actor87 });

		String json = objectToJson(actor, 0);
		String json2 = objectToJson(movie, 0);
		System.out.println(json);
		System.out.println(json2);

	}

	public static String objectToJson(Object instance, int indentSize) throws IllegalAccessException {
		Field[] fields = instance.getClass().getDeclaredFields();
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(indent(indentSize));
		stringBuilder.append("{");
		stringBuilder.append("\n");

		for (int index = 0; index < fields.length; index++) {
			Field field = fields[index];
			field.setAccessible(true);

			if (field.isSynthetic()) {
				continue;
			}
			stringBuilder.append(indent(indentSize + 1));
			stringBuilder.append(formatStringValue(field.getName()));
			stringBuilder.append(":");

			if (field.getType().isPrimitive()) {
				stringBuilder.append(formatPrimitiveValue2(field.get(instance), field.getType()));
			} else {

				if (field.getType().equals(String.class)) {
					stringBuilder.append(formatStringValue(field.get(instance).toString()));

				} else {

					if (field.getType().isArray()) {
						stringBuilder.append(arrayTojson(field.get(instance), indentSize + 1));

					} else {
						stringBuilder.append(objectToJson(field.get(instance), indentSize + 1));
					}

				}

			}

			if (index != fields.length - 1) {
				stringBuilder.append(",");
			}
			stringBuilder.append("\n");

		}
		stringBuilder.append(indent(indentSize));
		stringBuilder.append("}");
		return stringBuilder.toString();

	}

	private static Object arrayTojson(Object arrayInstance, int indentSize)
			throws IllegalArgumentException, IllegalAccessException {
		StringBuilder stringBuilder = new StringBuilder();
		int arrayLength = Array.getLength(arrayInstance);

		Class<?> componentType = arrayInstance.getClass().getComponentType();

		stringBuilder.append("[");
		stringBuilder.append("\n");

		for (int index = 0; index < arrayLength; index++) {

			Object element = Array.get(arrayInstance, index);

			if (componentType.isPrimitive()) {
				stringBuilder.append(indent(indentSize + 1));
				stringBuilder.append(formatPrimitiveValue2(element, componentType));
			} else {

				if (componentType.equals(String.class)) {
					stringBuilder.append(indent(indentSize + 1));
					stringBuilder.append(formatStringValue(element.toString()));

				} else {

					stringBuilder.append(objectToJson(element, indentSize + 1));

				}

			}

			if (index != arrayLength - 1) {
				stringBuilder.append(" , ");
			}
			stringBuilder.append("\n");

		}

		stringBuilder.append(indent(indentSize));
		stringBuilder.append("]");
		return stringBuilder.toString();
	}

	private static String indent(int indentSize) {
		StringBuilder stringBuilder = new StringBuilder();
		for (int index = 0; index < indentSize; index++) {
			stringBuilder.append("\t");
		}
		return stringBuilder.toString();
	}

	private static String formatPrimitiveValue(Field field, Object parentInstance)
			throws IllegalArgumentException, IllegalAccessException {

		if (field.getType().equals(boolean.class) || field.getType().equals(int.class)
				|| field.getType().equals(long.class) || field.getType().equals(short.class)) {
			return field.get(parentInstance).toString();
		} else {

			if (field.getType().equals(double.class) || field.getType().equals(float.class)) {
				return String.format("%.02f", field.get(parentInstance));
			}

			throw new RuntimeException(String.format("Type : %s is not supported ", field.getType().getName()));
		}

	}

	private static String formatStringValue(String value) {
		return String.format("\"%s\"", value);
	}

	private static String formatPrimitiveValue2(Object instance, Class<?> type)
			throws IllegalArgumentException, IllegalAccessException {

		if (type.equals(boolean.class) || type.equals(int.class) || type.equals(long.class)
				|| type.equals(short.class)) {
			return instance.toString();
		} else {

			if (type.equals(double.class) || type.equals(float.class)) {
				return String.format("%.02f", instance);
			}

			throw new RuntimeException(String.format("Type : %s is not supported ", type.getName()));
		}

	}

}
