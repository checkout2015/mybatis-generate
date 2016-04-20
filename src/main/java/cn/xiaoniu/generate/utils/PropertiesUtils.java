package cn.xiaoniu.generate.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PropertiesUtils {
	private static final Logger logger = LoggerFactory.getLogger(PropertiesUtils.class);
	
	 public static Map<String,String> obj2Map(Map<String,Object> src) {
		 Map<String,String> save = new HashMap<String,String>();
		 try {
				for(String key:src.keySet()){
					obj2Map(save,src.get(key),key);
				}
			} catch (Exception e) {
				 throw new IllegalArgumentException("obj convert to map error!",e);
			}
			return save;
	 }
	
	public static Map<String,String> obj2Map(String name,Object obj) {
		Map<String,Object> src = new HashMap<String,Object>();
		src.put(name, obj);
		return obj2Map(src);
	}
	
	 @SuppressWarnings("rawtypes")
	 private static void obj2Map(Map<String,String> save,Object obj,String name) throws Exception{
			if(obj!=null){
				if(obj instanceof Map){
					Map map = (Map)obj;
					for(Object key: map.keySet()){
						Object val = map.get(key);
						 if(val!=null){
							 if(RefectUtils.isPrimitive(val.getClass())){
								 save.put((name==null?"":(name+"."))+key,(String)map.get(key));
							 }else{
								 obj2Map(save,val,(name==null?"":(name+"."))+key);
							 }
						 }
					}
				}else if(obj instanceof List|| obj instanceof Set||obj.getClass().isArray()){
					return ;
				}else{
					Class<?> clazz = obj.getClass();
					while(clazz!=null){
						Method[] methods = clazz.getDeclaredMethods();
						 for (Method method : methods) {
				             String methodName = method.getName();
				             if ((methodName.startsWith("get") || methodName.startsWith("is")) 
				                     && ! "getClass".equals(methodName) && ! "get".equals(methodName) && ! "is".equals(methodName)
				                     && Modifier.isPublic(method.getModifiers()) 
				                     && method.getParameterTypes().length == 0) {
				                 int i = methodName.startsWith("get") ? 3 : 2;
				                 String key = methodName.substring(i, i + 1).toLowerCase() + methodName.substring(i + 1);
				                 Object value = method.invoke(obj, new Object[]{});
				                 if(value!=null){
				                	 if(RefectUtils.isPrimitive(method.getReturnType())){
						                 save.put((name==null?"":(name+"."))+key,value.toString());
					                 }else{
					                	 obj2Map(save,value,(name==null?"":(name+"."))+key);
					                 }
				                 }
				             }
				         }
						 clazz = clazz.getSuperclass();
					}
				}
			}
	 }
	 
	 

	 
	 
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public static void refresh(Object target,Map cache) {
		 Object obj = target;
		 if(obj!=null){
				if(obj instanceof Map){
					Map map = (Map)obj;
					for(Object key:map.keySet()){
						Object value = map.get(key);
						if(value!=null){
							if(value instanceof String){
								map.put(key,StringUtils.substVars((String)value,cache));
							}else if(!RefectUtils.isPrimitive(value.getClass())){
								refresh(value,cache);
							}
						}
					}
				}else if(obj instanceof List|| obj instanceof Set||obj.getClass().isArray()){
					if(obj instanceof List){
						List list = (List)obj;
						//Object value:list
						for(int i=0;i<list.size();i++){
							Object value = list.get(i);
							if(value instanceof String){
								list.add(i, StringUtils.substVars((String)value,cache));
							}else if(!RefectUtils.isPrimitive(value.getClass())){
								refresh(value,cache);
							}
						}
					}
					if(obj instanceof Set){
						Set set = (Set)obj;
						Object[] list = set.toArray();
						set.clear();
						for(int i=0;i<list.length;i++){
							Object value = list[i];
							if(value instanceof String){
								list[i] = StringUtils.substVars((String)value,cache);
							}else if(!RefectUtils.isPrimitive(value.getClass())){
								refresh(value,cache);
							}
							set.add(list[i]);
						}
					}
					if(obj.getClass().isArray()){
						Object[] list = (Object[])obj;
						for(int i=0;i<list.length;i++){
							Object value = list[i];
							if(value instanceof String){
								list[i] = StringUtils.substVars((String)value,cache);
							}else if(!RefectUtils.isPrimitive(value.getClass())){
								refresh(value,cache);
							}
						}
					}
				}else{
					Class<?> clazz = obj.getClass();
					while(clazz!=null){
						Field[] fields = clazz.getDeclaredFields();
						 for (Field field : fields) {
				             Object value = null;
							try {
								Method getMethod = RefectUtils.getGetMethod(obj,field.getName());
								if(getMethod!=null){
									value = getMethod.invoke(obj, new Object[]{});
								}
								if(value!=null&&value instanceof String){
					            	 Method setMethod = clazz.getDeclaredMethod(StringUtils.getSetMethodName(field.getName()), new Class<?>[]{String.class});
					            	 setMethod.invoke(obj, StringUtils.substVars((String)value,cache));
					             }
					             if(value!=null&&!RefectUtils.isPrimitive(value.getClass())){
										refresh(value,cache);
								 }
							} catch (Exception  e) {
								logger.error("",e);
							}
				         }
						 clazz = clazz.getSuperclass();
					}
				}
			}
		}
	 
}
