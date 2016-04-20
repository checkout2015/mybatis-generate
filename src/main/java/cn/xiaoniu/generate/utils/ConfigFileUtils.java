package cn.xiaoniu.generate.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xiaoniu.generate.config.Config;

import com.alibaba.fastjson.JSONObject;

public class ConfigFileUtils {
	private static final Logger logger = LoggerFactory.getLogger(ConfigFileUtils.class);

	
	 @SuppressWarnings({ "rawtypes", "unchecked" })
	 public static Map<String,Object> getSaveMap(Map<String,Object> save,String key){
		 String[] keys = key.split("\\.");
		 Map ret = save;
		 if(keys.length>0){
			 for(int i=0;i<keys.length-1;i++){
				 String strKey = keys[i];
				 Map tmpMap = (Map)ret.get(strKey);
				 if(tmpMap==null){
					 tmpMap = new HashMap<String,Object>();
					 ret.put(strKey, tmpMap);
				 }
				 ret = tmpMap;
			 } 
		 }
		 return ret;
	 }
	
	 
	 public static Config getConfig(String configFile){
		 InputStream is = RefectUtils.class.getClassLoader().getResourceAsStream(configFile);
			BufferedReader bf = new BufferedReader(new InputStreamReader(is));
			Properties conf = new Properties();
		try {
			conf.load(bf);
			Map<String,Object> map = new HashMap<String,Object>();
			for (Object key : conf.keySet()) {
				String strKey = key.toString();
				String strValue = (String)conf.get(key);
				strValue = StringUtils.substVars(strValue,conf);
				if(strKey.indexOf("generate.")==0){
					String str = StringUtils.removePri(strKey,"generate.");
					String[] ss = str.split("\\.");
					if(ss.length==1){
						map.put(ss[0],strValue);
					}else{
						Map<String,Object> save = getSaveMap(map,str);
						save.put(ss[ss.length-1],strValue);
					}
				}
			}
			return JSONObject.parseObject(JSONObject.toJSONString(map),Config.class);
		} catch (Exception e) {
			logger.error("", e);
		} finally {
			try {
				if (bf != null) {
					bf.close();
				}
			} catch (IOException e) {
				logger.error("", e);
			} finally {
				if (is != null) {
					try {
						is.close();
					} catch (IOException e) {
						logger.error("", e);
					}
				}
			}
		}
		return null;
	 }

	
}
