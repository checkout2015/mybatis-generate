package cn.xiaoniu.generate.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.xiaoniu.generate.model.FileVo;
import freemarker.template.Configuration;
import freemarker.template.Template;
public class FreemarkUtils
{
  private static final String CONTENT_ENCODING = "UTF-8";
  private static final Logger log =  LoggerFactory.getLogger(FreemarkUtils.class);
  private static Configuration configuration;
  private static Template template;

  
  public static void init(String templateDir){
	  try{
	      URL templateBasePath = FreemarkUtils.class.getClassLoader().getResource(templateDir);
	      configuration = new Configuration(Configuration.VERSION_2_3_22);
		  configuration.setDirectoryForTemplateLoading(new File(templateBasePath.toURI()));
	  } catch (Exception e) {
	      log.error("",e);
	  }
  }
  
  public static void WriterPage(Map<String, Object> data){
	  for(String key:data.keySet()){
		  Object obj = data.get(key);
		  if(obj instanceof FileVo){
			  WriterPage(data,(FileVo)obj);
		  }
	  }
  }
  
  public static void WriterPage(Map<String, Object> data,FileVo fileVo){
	  WriterPage(data,fileVo.getTemplateFile(),fileVo.getFilePath(),fileVo.getFileName());
  }

  public static void WriterPage(Map<String, Object> data, String templateName, String fileDirPath, String targetFile)
  {
	  FileOutputStream fos = null;
    try {
      File dir = new File(fileDirPath);
      File file = new File(fileDirPath,targetFile);
      if (!dir.exists()) {
    	  dir.mkdirs();
      }
      fos = new FileOutputStream(file);
      BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(fos, CONTENT_ENCODING));
      
   	  template = configuration.getTemplate(templateName);
      template.process(data, writer);
      writer.flush();
      writer.close();
    } catch (Exception e) {
    	log.error("",e);
    }finally{
       try {
			fos.close();
		} catch (IOException e) {
			log.error("",e);
		}
    }
  }
}