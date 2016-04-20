package cn.xiaoniu.generate.model;

import cn.xiaoniu.generate.config.ConfigFactory;
import cn.xiaoniu.generate.utils.JavaTypeResolverUtils.JdbcTypeInformation;

public class Column {
	private String columnName;
	private String columnComment;
	private JdbcTypeInformation type;
	private int length;
	private boolean nullable;
	private int scale;
	private boolean primaryKey = false;
	private String jdbcType;
	private String sqlName;
	private boolean autoIncrement;
	
	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	public void setAutoIncrement(boolean autoIncrement) {
		this.autoIncrement = autoIncrement;
	}

	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getJdbcType() {
		return jdbcType;
	}

	public void setJdbcType(String jdbcType) {
		this.jdbcType = jdbcType;
	}

	public boolean getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(boolean primaryKey) {
		this.primaryKey = primaryKey;
	}

	public String getColumnName() {
		return columnName;
	}

	public void setColumnName(String columnName) {
		this.sqlName = columnName;
		if(ConfigFactory.config.getTable().getSymbol()!=null){
			this.sqlName = ConfigFactory.config.getTable().getSymbol()+columnName+ConfigFactory.config.getTable().getSymbol();
		}
		this.columnName = columnName;
	}


	public String getColumnComment() {
		return columnComment;
	}

	public void setColumnComment(String columnComment) {
		this.columnComment = columnComment;
	}

	public JdbcTypeInformation getType() {
		return type;
	}

	public void setType(JdbcTypeInformation type) {
		this.type = type;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public boolean isNullable() {
		return nullable;
	}

	public void setNullable(boolean nullable) {
		this.nullable = nullable;
	}

	public int getScale() {
		return scale;
	}

	public void setScale(int scale) {
		this.scale = scale;
	}

	
}