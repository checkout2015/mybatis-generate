package cn.xiaoniu.generate.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EntityVo {
	
	private String name;
	
	private String aliasName;
	
	private EntityAttributeVo primaryKey;
	
	private Table table;
	
	private List<EntityAttributeVo> attributes= new ArrayList<EntityAttributeVo>();
	
	public List<String> attrFullNames = new ArrayList<String>();
	
	public List<String> getAttrFullNames() {
		for(EntityAttributeVo attribute:attributes){
			String strTemp = attribute.getFullName();
			if(strTemp!=null&&!"".equals(strTemp)){
				boolean isExists = false;
				for(String str:attrFullNames){
					if(str.equals(strTemp)){
						isExists = true;
						break;
					}
				}
				if(!isExists){
					attrFullNames.add(strTemp);
				}
			}
		}
		Collections.sort(attrFullNames);
		return attrFullNames;
	}

	public void setAttrFullNames(List<String> attrFullNames) {
		this.attrFullNames = attrFullNames;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EntityAttributeVo getPrimaryKey() {
		return primaryKey;
	}

	public void setPrimaryKey(EntityAttributeVo primaryKey) {
		this.primaryKey = primaryKey;
	}

	public Table getTable() {
		return table;
	}

	public void setTable(Table table) {
		this.table = table;
	}

	public List<EntityAttributeVo> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<EntityAttributeVo> attributes) {
		this.attributes = attributes;
	}

	public String getAliasName() {
		return aliasName;
	}

	public void setAliasName(String aliasName) {
		this.aliasName = aliasName;
	}
	
	
}
