package cn.xiaoniu.generate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xiaoniu.generate.GenerateFactory;
import cn.xiaoniu.generate.utils.ConfigFileUtils;
import cn.xiaoniu.generate.utils.RefectUtils;
import cn.xiaoniu.generate.utils.StringUtils;

public class Run {
	 private static final Logger logger = LoggerFactory.getLogger(ConfigFileUtils.class);
	
	  public static void main(String[] args) throws Exception {
		// GenerateFactory.codeGenerate("credit/generate_table.properties");
		 //GenerateFactory.codeGenerate("credit/generate_sql.properties");
	   InputStream is = RefectUtils.class.getClassLoader().getResourceAsStream("config.properties");
	   BufferedReader bf = new BufferedReader(new InputStreamReader(is));
	   Properties conf = new Properties();
		try {
			conf.load(bf);
			String strValue = (String)conf.get("config.properties");
			if(StringUtils.isNotBlank(strValue)){
				String[] properties = strValue.split(",");
				if(properties.length>0){
					for(String p:properties){
						GenerateFactory.codeGenerate(p);
					}
				}
			}
			
		}catch(Exception e){
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
	  }
}
