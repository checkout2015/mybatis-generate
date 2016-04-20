package cn.xiaoniu.generate.model;

import java.util.List;

import cn.xiaoniu.generate.config.ConfigFactory;

public class Table {
	private String name;
	private String sqlName;
	private List<Column> columns = null;
	private Column primaryKey;
	
	public String getSqlName() {
		return sqlName;
	}

	public void setSqlName(String sqlName) {
		this.sqlName = sqlName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.sqlName = name;
		if(ConfigFactory.config.getTable().getSymbol()!=null){
			this.sqlName = ConfigFactory.config.getTable().getSymbol()+name+ConfigFactory.config.getTable().getSymbol();
		}
		this.name = name;
	}

	public List<Column> getColumns() {
		return columns;
	}

	public void setColumns(List<Column> columns) {
		this.columns = columns;
	}

	public Column getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(Column primaryKey) {
		this.primaryKey = primaryKey;
	}
	
}
