package com.vn.dailycookapp.utils.json;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class JsonTransformer {
	
	public <T> String marshall(T t) {
//		long s = System.currentTimeMillis();
		JSONObject jsonObj = marshallChild(t);
//		long e = System.currentTimeMillis();
//		System.out.println(e-s);
		return jsonObj == null ? null : jsonObj.toString();
	}
	
	@SuppressWarnings("rawtypes")
	private JSONObject marshallChild(Object t) {
		try {
			Class<? extends Object> pClass = t.getClass();
			JSONObject obj = new JSONObject();
			// get all field private, protected, public
			List<Field> lField = new ArrayList<Field>();
			
			// get all field private, protected, public
			Field[] fields = pClass.getDeclaredFields();
			for (Field field : fields) {
				lField.add(field);
			}
			
			Class superClass = pClass.getSuperclass();
			if (!superClass.equals(Object.class)) {
				Field[] superFields = superClass.getDeclaredFields();
				for (Field field : superFields) {
					lField.add(field);
				}
			}
			
			for (Field field : lField) {
				// check for ignore this property
				if (field.isAnnotationPresent(JsonIgnoreProperty.class)) {
					continue;
				}
				
				JsonProperty jsonP = field.getAnnotation(JsonProperty.class);
				String value = "";
				if (jsonP != null) {
					value = jsonP.value();
				}
				String fieldName = field.getName();
				String jsonKey = value.isEmpty() ? fieldName : value;
				
				String firtChar = fieldName.subSequence(0, 1).toString();
				Method method = pClass.getMethod("get" + fieldName.replaceFirst(firtChar, firtChar.toUpperCase()),
						new Class[] {});
				
				// check ignore empty
				JsonIgnoreEmpty jsonIEP = field.getAnnotation(JsonIgnoreEmpty.class);
				// check primitive
				Object valueOfField = method.invoke(t, new Object[] {});
				if (valueOfField == null) {
					continue;
				}
				Class currentClass = valueOfField.getClass();
				
				// check type of field
				if (!(currentClass.isPrimitive() || currentClass.equals(String.class) || Number.class
						.isAssignableFrom(currentClass))) {
					// if is array
					if (currentClass.isArray()) {
						valueOfField = marshallArray(valueOfField);
					} else if (List.class.isAssignableFrom(currentClass)) {
						// if is list
						valueOfField = marshallList(valueOfField);
					} else if (Map.class.isAssignableFrom(currentClass)) {
						valueOfField = marshallMap(valueOfField);
					} else {
						// otherwise
						valueOfField = marshallChild(valueOfField);
					}
				}
				if (jsonIEP != null) {
					if (valueOfField == null) {
						continue;
					} else {
						obj.put(jsonKey, valueOfField);
					}
				} else {
					obj.put(jsonKey, valueOfField);
				}
			}
			return obj;
		} catch (Exception ex) {
			// TODO LOG HERE
		}
		return null;
	}
	
	private JSONObject marshallMap(Object valueOfField) {
		try {
			@SuppressWarnings("unchecked")
			Map<String, Object> map = (Map<String, Object>) valueOfField;
			JSONObject jsonObj = new JSONObject();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if (entry.getValue().getClass().isPrimitive() || entry.getValue().getClass().equals(String.class)) {
					jsonObj.put(entry.getKey(), entry.getValue());
				} else {
					jsonObj.put(entry.getKey(), marshallChild(entry.getValue()));
				}
			}
			return jsonObj;
		} catch (Exception ex) {
			// TODO LOG HERE
		}
		
		return null;
		
	}
	
	private JSONArray marshallArray(Object t) {
		try {
			JSONArray jsonArr = new JSONArray();
			Object[] arr = (Object[]) t;
			for (Object obj : arr) {
				if (obj.getClass().isPrimitive() || obj.getClass().equals(String.class)) {
					jsonArr.put(obj);
				} else {
					JSONObject jsonObj = marshallChild(obj);
					// System.out.println(jsonObj.toJSONString());
					jsonArr.put(jsonObj);
				}
			}
			return jsonArr;
			
		} catch (Exception ex) {
			// TODO
		}
		return null;
	}
	
	private JSONArray marshallList(Object t) {
		try {
			JSONArray jsonArr = new JSONArray();
			@SuppressWarnings("rawtypes")
			List list = (List) t;
			for (Object obj : list) {
				if (obj.getClass().isPrimitive() || obj.getClass().equals(String.class)) {
					jsonArr.put(obj);
				} else {
					JSONObject jsonObj = marshallChild(obj);
					// System.out.println(jsonObj.toJSONString());
					jsonArr.put(jsonObj);
				}
			}
			// System.out.println(jsonArr.toJSONString());
			return jsonArr;
		} catch (Exception ex) {
			// TODO
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public <T> T unmarshall(String jsonString, Class<T> tClass) {
		return (T) unmarshallChild(jsonString, tClass);
	}
	
	private Object unmarshallChild(String jsonString, Class<? extends Object> tClass) {
		JSONObject obj = new JSONObject(jsonString);
		return unmarshallMap(obj, tClass);
	}
	
	@SuppressWarnings("unchecked")
	private <T> List<T> unmarshallList(JSONArray jsonArr, Class<T> lClass) {
		try {
			List<T> list = new ArrayList<T>();
			for (int i = 0; i < jsonArr.length(); i++) {
				T obj = (T) jsonArr.get(i);
				Class<? extends Object> objClass = obj.getClass();
				if (objClass.isPrimitive() || objClass.equals(String.class)) {
					list.add(obj);
				} else {
					if (objClass.equals(JSONObject.class)) {
						Object objItem = unmarshallMap((JSONObject) obj, lClass);
						list.add((T) objItem);
					} else {
						// Jsonarry class
						List<T> objItem = unmarshallList((JSONArray) obj, lClass);
						list.add((T) objItem);
					}
				}
			}
			
			return list;
		} catch (Exception ex) {
			// TODO
			ex.printStackTrace();
		}
		
		return null;
	}
	
	private Object unmarshallMap(JSONObject obj, Class<? extends Object> tClass) {
		try {
			Object t = tClass.newInstance();
			List<Field> lField = new ArrayList<Field>();
			
			// get all field private, protected, public
			Field[] fields = tClass.getDeclaredFields();
			for (Field field : fields) {
				lField.add(field);
			}
			@SuppressWarnings({ "rawtypes" })
			Class superClass = tClass.getSuperclass();
			if (!superClass.equals(Object.class)) {
				Field[] superFields = superClass.getDeclaredFields();
				for (Field field : superFields) {
					lField.add(field);
				}
			}
			for (Field field : lField) {
				// check for ignore this property
				if (field.isAnnotationPresent(JsonIgnoreProperty.class)) {
					continue;
				}
				
				JsonProperty jsonP = field.getAnnotation(JsonProperty.class);
				String value = "";
				if (jsonP != null) {
					value = jsonP.value();
				}
				String fieldName = field.getName();
				String jsonKey = value.isEmpty() ? fieldName : value;
				
				// check ignore empty
				JsonIgnoreEmpty jsonIEP = field.getAnnotation(JsonIgnoreEmpty.class);
				
				Object valueOfField = obj.get(jsonKey);
				if (jsonIEP == null) {
					if (valueOfField == null) {
						continue;
					} else {
						// check primitive, String, JsonArray, JsonObject
						Class<? extends Object> classOfValue = valueOfField.getClass();
						if (classOfValue.equals(JSONArray.class)) {
							JSONArray arr = (JSONArray) valueOfField;
							List<? extends Object> list = unmarshallList(arr, arr.get(0).getClass());
							if (field.getType().isArray()) {
								if (!field.isAccessible()) {
									field.setAccessible(true);
								}
								Object arrUnknow = Array.newInstance(list.get(0).getClass(), list.size());
								for (int i = 0; i < list.size(); i++) {
									Array.set(arrUnknow, i, list.get(i));
								}
								
								Object[] oo = (Object[]) arrUnknow;
								
								setValue(t, tClass, field, oo, fieldName);
							} else {
								setValue(t, tClass, field, unmarshallList(arr, arr.get(0).getClass()), fieldName);
							}
							// unmarshallMap(jsonObj, classOfValue);
						} else if (classOfValue.equals(JSONObject.class)) {
							JSONObject jsonObj = (JSONObject) valueOfField;
							
							if (field.getType().isAssignableFrom(Map.class)) {
								@SuppressWarnings("unchecked")
								Iterator<String> keys = jsonObj.keys();
								
								Map<String, Object> newValueOfField = new HashMap<>();
								while (keys.hasNext()) {
									String key = keys.next();
									Object item = jsonObj.get(key);
									if (item.getClass().isPrimitive() || item.getClass().equals(String.class)) {
										newValueOfField.put(key, item);
									} else {
										if (item.getClass().equals(Map.class)) {
											newValueOfField.put(key, unmarshallMap((JSONObject) item, item.getClass()));
										} else {
											newValueOfField.put(key, unmarshallList((JSONArray) item, item.getClass()));
										}
									}
								}
								setValue(t, tClass, field, newValueOfField, fieldName);
								
							} else {
								setValue(t, tClass, field, unmarshallMap(jsonObj, field.getType()), fieldName);
							}
						} else {
							setValue(t, tClass, field, valueOfField, fieldName);
						}
						
					}
				} else {
					continue;
				}
			}
			return t;
		} catch (Exception ex) {
			// TODO LOG HERE
			ex.printStackTrace();
		}
		
		return null;
	}
	
	private void setValue(Object t, Class<? extends Object> tClass, Field field, Object valueOfField, String fieldName)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		String firtChar = fieldName.subSequence(0, 1).toString();
		
		// if (field.getClass().isArray()) {
		// @SuppressWarnings("unchecked")
		// List<? extends Object> list = (List<? extends Object>) valueOfField;
		// valueOfField = list.toArray();
		// }
		Method method = tClass.getMethod("set" + fieldName.replaceFirst(firtChar, firtChar.toUpperCase()),
				field.getType());
		
		// invoke method set
		method.invoke(t, valueOfField);
	}
	
	private static JsonTransformer	transformer;
	
	private JsonTransformer() {
		
	}
	
	public static JsonTransformer getInstance() {
		if (transformer == null) {
			transformer = new JsonTransformer();
		}
		return transformer;
	}
}
