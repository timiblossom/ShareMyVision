package com.smv.util.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;


public class TypeUtil {
	@SuppressWarnings("unchecked")
	public static <T> Class<T> getGenericParameterFromSuperclass(int parameterIndex, Class<?> superClass, Class<?> on) {
	    while (!on.getSuperclass().equals(superClass)) {
	        on = on.getSuperclass();
	        if(on == null)
	        	return null;
	    }
	    	
	    Class<?> cls = (Class<?>) ((ParameterizedType) on.getGenericSuperclass()).getActualTypeArguments()[parameterIndex];
	    
	    return (Class<T>) cls;
	}
	
	public static <T> Class<T> getGenericParameterFromSuperclass(int parameterIndex, Class<?> superClass, Class<?> on, Class<T> defaultClass) {
		Class<T> result = getGenericParameterFromSuperclass(parameterIndex, superClass, on);
		if(result != null)
			return result;
		
		return defaultClass;
	}
	
	public static <T> T findMostSpecific(Class<?> cls, Map<Class<?>, T> converters) {
		T result = null;
		Class<?> mostSpecific = null;
		for(Entry<Class<?>, T> entry: converters.entrySet()) {
			if(entry.getKey().isAssignableFrom(cls)) {
				if(mostSpecific == null || mostSpecific.isAssignableFrom(cls)) {
					result = entry.getValue();
					mostSpecific = entry.getKey();
				}
			}
		}
		
		return result;
	}
	
	public static <T> T fromString(String value, Class<T> returnCls) {
		return fromString(value, returnCls, null);
	}
	
	public static Date fromJavascriptDate(String value) {
		return DateTimeFormat
			.longDateTime()
			.parseDateTime(value)
			.toDate();
	}
	
	/**
	 * Shortcut alleviating the need for a class definition so long as <tt>defaultValue</tt> is not <tt>null</tt>.
	 */
	@SuppressWarnings("unchecked")
	public static <T> T fromString(String value, T defaultValue) {
		assert(defaultValue != null);
		
		return fromString(value, (Class<T>) defaultValue.getClass(), defaultValue);
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T fromString(String value, Class<T> returnCls, T defaultValue) {
		if(value == null) 
			return defaultValue;
		
		if(String.class.equals(returnCls) || returnCls == null)
			return (T) value;
		
		if(Boolean.class.equals(returnCls)) {
			if("true".equalsIgnoreCase(value))
				return (T) Boolean.TRUE;
			
			if("1".equalsIgnoreCase(value))
				return (T) Boolean.TRUE;
			
			return (T) Boolean.FALSE;
		}
		
		if(Double.class.equals(returnCls) || 
				double.class.equals(returnCls)) {
			return (T) Double.valueOf(Double.parseDouble(value));
		}
		
		if(Float.class.equals(returnCls) || 
				float.class.equals(returnCls)) {
			return (T) Float.valueOf(Float.parseFloat(value));
		}
		
		if(Integer.class.equals(returnCls) || 
				int.class.equals(returnCls)) {
			return (T) Integer.valueOf(Integer.parseInt(value));
		}
		
		throw new RuntimeException("Unsupported conversion: " + returnCls.getName());
	}
	
	public static <T> T coallesce(T...values) {
		for(T value : values)
			if(value != null)
				return value;
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T convert(Object value, Class<T> type){
		try {
			if(value == null || type.isAssignableFrom(value.getClass()))
				return (T) value;
			
			if(value instanceof List){
				if(type.isArray()){
					//convert to array of type
					Class<?> arrayType = type.getComponentType();
					Object[] values = ((List)value).toArray();
					Object array = Array.newInstance(arrayType, values.length);
					int i = 0;
					for(Object aValue: values)
						Array.set(array, i++, convert(aValue, arrayType));
					return (T) array;
				} else if(List.class.isAssignableFrom(type)){
					return (T) new ArrayList<Object>((List<?>) value);
				}
			} else if(value instanceof String) {
				String strValue = (String) value;
				if(List.class.isAssignableFrom(type)){
					return (T) Arrays.asList(strValue.split("[,]"));					
				}
			}
			
			if(type == Integer.class)
				if(value.toString().length()<=0)
					return null;
				else
					return (T) Integer.valueOf(value.toString());
			
			if(type == int.class)
				if(value.toString().length()<=0)
					return (T) Integer.valueOf(0);
				else
					return (T) Integer.valueOf(value.toString());
			
			if(type == String.class)
				return (T) value.toString();
			
			if(type == Date.class && DateTime.class.isAssignableFrom(value.getClass())) {
				return (T)((DateTime)value).toDate();
			}
			
			if(type == Date.class)
				throw new RuntimeException("String to Date conversion not implemented yet");
			
			if(type == Long.class || type == long.class)
				return (T) Long.valueOf(value.toString());
			
			if(type == Float.class || type == float.class)
				return (T) Float.valueOf(value.toString());
			
			if(type == Double.class || type == double.class)
				return (T) Double.valueOf(value.toString());
			
			if(type == Boolean.class || type == boolean.class)
				return (T) Boolean.valueOf(value.toString().equalsIgnoreCase("true") 
						|| value.toString().equals("1") 
						|| value.toString().equalsIgnoreCase("on")
						|| value.toString().equalsIgnoreCase("yes"));
			
			if(type == Short.class || type == short.class)
				return (T) Short.valueOf(value.toString());
			
			if(type == Byte.class || type == byte.class)
				return (T) Byte.valueOf(value.toString());
			
			if(type == Character.class || type == char.class)
				return (T) Character.valueOf(value.toString().charAt(0));
			
			if (Enum.class.isAssignableFrom(type))
				return (T) Enum.valueOf((Class<? extends Enum>)type, value.toString());
			
		} catch(NumberFormatException e) {
			throw new TypeConversionException(value, type, e);
			
		}
		
		throw new TypeConversionException(value, type);
	}
	
	
	public static class TypeConversionException extends RuntimeException {
		private static final long serialVersionUID = 1L;

		protected Object   value;
		protected Class<?> type;
		
		public TypeConversionException(Object value, Class<?> type) {
			super("Could not convert value in object of type "+value.getClass().getCanonicalName()+" to target type of "+type.getCanonicalName());
			this.value = value;
			this.type  = type;
		}
		
		public TypeConversionException(Object value, Class<?> type, Throwable cause) {
			super("Could not convert value in object of type "+value.getClass().getCanonicalName()+" to target type of "+type.getCanonicalName(), cause);
			this.value = value;
			this.type  = type;
		}
		
		public Object getObject() {
			return value;
		}
		
		public Class<?> getConversionClass() {
			return type;
		}
	}
	
	
	public static <T extends Annotation> T findAnnotation(Class<?> cls, Class<T> ann) {
		Class<?> found = findAnnotated(cls, ann);
		if(found != null)
			return found.getAnnotation(ann);
		
		return null;
	}
	
	public static Class<?> findAnnotated(Class<?> cls, Class<? extends Annotation> ann) {
		if(cls == null)
			return null;
		
		if(cls.isAnnotationPresent(ann))
			return cls;
		
		for(Class<?> i: cls.getInterfaces()) {
			Class<?> found = findAnnotated(i, ann);
			if(found != null)
				return found;
		}
		
		return findAnnotated(cls.getSuperclass(), ann);
	}
	
	public static boolean empty(String value) {
		if(value == null)
			return true;
		if(value.trim().length() == 0)
			return true;
		
		return false;
	}
}

