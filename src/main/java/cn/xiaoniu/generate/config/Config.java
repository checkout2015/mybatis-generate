package cn.xiaoniu.generate.config;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cn.xiaoniu.generate.utils.DateUtils;

public class Config {

	private String author = null;
	private String createDate = DateUtils.format(new Date(), "yyyy年MM月dd日 HH:mm:ss");
	private String templateDir = null;
	private String target;
	private String type;
	private Map<String, String> config = new HashMap<String, String>();
	private DbConfig db = new DbConfig();
	private TableConfig table = new TableConfig();
	private Map<String, AppenderConfig> appender = new HashMap<String, AppenderConfig>();

	public String getSerialVersionUID() {
		//return ObjectStreamClass.lookupAny(Calendar.class).getSerialVersionUID() + "L";
		 return new Random().nextLong()+"L";
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getTarget() {
		return target;
	}

	public void setTarget(String target) {
		this.target = target;
	}

	public Map<String, AppenderConfig> getAppender() {
		return appender;
	}

	public void setAppender(Map<String, AppenderConfig> appender) {
		this.appender = appender;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getTemplateDir() {
		return templateDir;
	}

	public void setTemplateDir(String templateDir) {
		this.templateDir = templateDir;
	}

	public Map<String, String> getConfig() {
		return config;
	}

	public void setConfig(Map<String, String> config) {
		this.config = config;
	}

	public DbConfig getDb() {
		return db;
	}

	public void setDb(DbConfig db) {
		this.db = db;
	}

	public TableConfig getTable() {
		return table;
	}

	public void setTable(TableConfig table) {
		this.table = table;
	}

	@Override
	public String toString() {
		return "Config [author=" + author + ", templateDir=" + templateDir + ", target=" + target + ", config=" + config + ", db=" + db + ", table=" + table + ", appender=" + appender + "]";
	}

}