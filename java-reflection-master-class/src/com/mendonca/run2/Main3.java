package com.mendonca.run2;

import java.lang.reflect.Array;
import java.util.LinkedList;
import java.util.List;


public class Main3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		try {
			// Integer [] result = concat(Integer.class, 1,2,38,4,5);

			// int [] result = concat(int.class, 1, 2, 3, new int[] {4, 5, 6}, 7);

			// String [] result = concat(String.class, new String[]{"a", "b"}, "c", new
			// String[] {"d", "e"});
			int[] result = concat(int.class, 1, 2, 3, new int[] { 4, 5, 6 }, 7);

			for (int index = 0; index < result.length; index++) {
				System.out.println(result[index]);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static <T> T concat(Class<?> type, Object... arguments) {

		if (arguments.length == 0) {
			return null;
		}

		List<Object> elements = new LinkedList<>();

		for (int index = 0; index < arguments.length; index++) {

			if (arguments[index].getClass().isArray()) {
				System.out.println(arguments[index].getClass().isArray());

				int length = Array.getLength(arguments[index]);

				for (int index2 = 0; index2 < length; index2++) {
					elements.add(Array.get(arguments[index], index2));
				}

			} else {
				elements.add(parseValue(type, arguments[index]));
			}

		}

		Object flattenedArray = Array.newInstance(type, elements.size());

		for (int i = 0; i < elements.size(); i++) {
			Array.set(flattenedArray, i, elements.get(i));
		}

		return (T) flattenedArray;
	}

	private static Object parseValue(Class<?> type, Object value) {

		if (type.equals(Integer.class) || type.equals(int.class)) {
			return Integer.parseInt(value.toString());
		} else {

			if (type.equals(Short.class) || type.equals(short.class)) {
				return Short.parseShort(value.toString());
			} else {

				if (type.equals(Long.class) || type.equals(long.class)) {
					return Long.parseLong(value.toString());
				} else {

					if (type.equals(Double.class) || type.equals(double.class)) {
						return Double.parseDouble(value.toString());
					} else {

						if (type.equals(Float.class) || type.equals(float.class)) {
							return Float.parseFloat(value.toString());
						} else {

							return value;
						}

					}

				}

			}

		}

	}

}
