package cn.xiaoniu.generate.utils;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RefectUtils {
	private static final Logger logger = LoggerFactory.getLogger(RefectUtils.class);

	public static boolean isPrimitive(Class<?> type) {
		return type.isPrimitive() || type == String.class
				|| type == Character.class || type == Boolean.class
				|| type == Byte.class || type == Short.class
				|| type == Integer.class || type == Long.class
				|| type == Float.class || type == Double.class
				|| type == Object.class;
	}

	public static Method getGetMethod(Object obj,String fileName){
		if(fileName==null||obj==null){
			return null;
		}
		Method method = null;
		try{
			Class<?> clazz = obj.getClass();
			String getMethodName =  "get"+fileName.substring(0,1).toUpperCase()+fileName.substring(1);
			method =clazz.getDeclaredMethod(getMethodName, new Class<?>[]{});
			if(method==null){
				getMethodName =  "is"+fileName.substring(0,1).toUpperCase()+fileName.substring(1);
				 method = clazz.getDeclaredMethod(getMethodName, new Class<?>[]{});
			}
		}catch(Exception e){
			logger.error("find "+fileName+" get method error!",e);
		}
		return method;
	}
	
}
