package cn.xiaoniu.generate.model;

import cn.xiaoniu.generate.utils.StringUtils;

public class EntityAttributeVo extends JavaVo{
	
	private String attributeName;
	
	private String comment;
	
	private Column column ;
	

	public Column getColumn() {
		return column;
	}

	public void setColumn(Column column) {
		this.column = column;
	}

	public String getAttributeName() {
		return attributeName;
	}

	public void setAttributeName(String attributeName) {
		this.attributeName = attributeName;
	}

	public String getSetMethodName() {
		return StringUtils.getSetMethodName(attributeName);
	}

	public String getGetMethodName() {
		return StringUtils.getGetMethodName(attributeName, column.getType().getJavaFullName());
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
