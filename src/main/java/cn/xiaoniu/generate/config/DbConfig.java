package cn.xiaoniu.generate.config;

public class DbConfig {
	private String url;
	private String username;
	private String password;
	private String catalog;
	private String schema;
	private String dbType;

	public String getDbType() {
		return dbType;
	}

	public void setDbType(String dbType) {
		this.dbType = dbType;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCatalog() {
		return catalog;
	}

	public void setCatalog(String catalog) {
		this.catalog = catalog;
	}

	public String getSchema() {
		return schema;
	}

	public void setSchema(String schema) {
		this.schema = schema;
	}

	public boolean isOracle() {
		return url.contains("oracle");
	}

	public boolean isMysql() {
		return url.contains("mysql");
	}

	@Override
	public String toString() {
		return "DbConfig [url=" + url + ", username=" + username
				+ ", password=" + password + ", catalog=" + catalog
				+ ", schema=" + schema + "]";
	}
}
