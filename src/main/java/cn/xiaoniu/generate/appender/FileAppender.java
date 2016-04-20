package cn.xiaoniu.generate.appender;

import cn.xiaoniu.generate.config.AppenderConfig;
import cn.xiaoniu.generate.model.FileVo;

public class FileAppender implements BaseAppender{

	@Override
	public FileVo doInvoke(AppenderConfig appender) {
		FileVo fileVo = new FileVo();
		fileVo.setFileName(appender.getFileName());
		fileVo.setFilePath(appender.getPath());
		fileVo.setTemplateFile(appender.getTemplate());
		return fileVo;
	}
	
}
