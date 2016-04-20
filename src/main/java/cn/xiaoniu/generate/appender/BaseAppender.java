package cn.xiaoniu.generate.appender;

import cn.xiaoniu.generate.config.AppenderConfig;
import cn.xiaoniu.generate.model.FileVo;

public interface BaseAppender {
	
	public abstract FileVo doInvoke(AppenderConfig appender);
}
