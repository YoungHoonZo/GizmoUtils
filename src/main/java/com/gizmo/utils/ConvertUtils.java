package com.gizmo.utils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * Dto, Vo 객체를 Map으로 변환한다.
 * ArrayList<Dto>, ArrayList<Vo>, List<Dto>, List<Vo> 를  ArrayList<HashMap> 으로 변환 한다.
 * 2022.07.03 Value Object에 primitive type이 존재할 때 Map으로 변환이 안되는 이슈 해결
 *
 * 본 소스는 아래 블로그에서 허락을 받아 가져와서 수정한 소스이다.
 *  출처:https://kim-jong-hyun.tistory.com/47#comment21603136
 */
public class ConvertUtils {

	/**
     * Value Object를 Map으로 변환한다.
	 * @param obj
	 * @return
	 */
	public static Map<String, Object> convertToMap(Object obj) {
		try {
			if (Objects.isNull(obj)) {
				return Collections.emptyMap();
			}
			Map<String, Object> convertMap = new HashMap<>();

			Field[] fields = obj.getClass().getDeclaredFields();

			for (Field field : fields) {
				field.setAccessible(true);
				convertMap.put(field.getName(), field.get(obj));
			}
			return convertMap;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
     * Map을 Value Object로 변환한다.
	 * @param map 원본 Map 데이터
	 * @param type 변환 하고자 하는 ValueObject
	 * @return
     * @param <T>
	 */
	public static <T> T convertToValueObject(Map<String, Object> map, Class<T> type) {
		try {
			Objects.requireNonNull(type, "Class cannot be null");
			T instance = type.getConstructor().newInstance();

			if (map == null || map.isEmpty()) {
				return instance;
			}

			for (Map.Entry<String, Object> entry : map.entrySet()) {
				Field[] fields = type.getDeclaredFields();

				for (Field field : fields) {
					field.setAccessible(true);
					String name = field.getName();

					boolean isSameType = false;

					if (entry.getValue() == null){
						isSameType = true;
					}
					else {
						isSameType = entry.getValue().getClass().equals(getReferenceType(field.getType()));
					}

					boolean isSameName = entry.getKey().equals(name);

					if (isSameType && isSameName) {
						field.set(instance, map.get(name));
						break;
					}
				}
			}
			return instance;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * List<Vo>를 List<Map> 으로 변환한다.
	 * @param list
	 * @return
	 */
	public static List<Map<String, Object>> convertToMaps(List<?> list) {
		if (list == null || list.isEmpty()) {
			return Collections.emptyList();
		}
		List<Map<String, Object>> convertList = new ArrayList<>(list.size());

		for (Object obj : list) {
			convertList.add(ConvertUtils.convertToMap(obj));
		}
		return convertList;
	}

	/**
	 * List<Map<String, Object>> 를 List<T>로 변환한다.
	 * @param list 변환할 List<Map<String, Object>>
	 * @param type 변환해서 List에 담을 ValueObject
	 * @return
	 * @param <T>
	 */
	public static <T> List<T> convertToValueObjects(List<Map<String, Object>> list, Class<T> type) {
		Objects.requireNonNull(type, "Class cannot be null");

		if (list == null || list.isEmpty()) {
			return Collections.emptyList();
		}
		List<T> convertList = new ArrayList<>(list.size());

		for (Map<String, Object> map : list) {
			convertList.add(ConvertUtils.convertToValueObject(map, type));
		}
		return convertList;
	}

	private static Class<?> getReferenceType(Class<?> type) {
		switch (type.getName()) {
			case "boolean" : return Boolean.class;
			case "byte"    : return Byte.class;
			case "short"   : return Short.class;
			case "char"    : return Character.class;
			case "int"     : return Integer.class;
			case "long"    : return Long.class;
			case "float"   : return Float.class;
			case "double"  : return Double.class;
			default        : return type;
		}
	}
}
