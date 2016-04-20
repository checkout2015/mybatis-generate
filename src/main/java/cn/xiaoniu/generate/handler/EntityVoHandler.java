package cn.xiaoniu.generate.handler;

import cn.xiaoniu.generate.config.ConfigFactory;
import cn.xiaoniu.generate.config.TableConfig;
import cn.xiaoniu.generate.model.Column;
import cn.xiaoniu.generate.model.EntityAttributeVo;
import cn.xiaoniu.generate.model.EntityVo;
import cn.xiaoniu.generate.model.SqlResultSet;
import cn.xiaoniu.generate.model.Table;
import cn.xiaoniu.generate.utils.JavaTypeResolverUtils.JdbcTypeInformation;
import cn.xiaoniu.generate.utils.StringUtils;

public class EntityVoHandler {
	
	private static TableConfig tableConfig = ConfigFactory.config.getTable();
	
	public static EntityVo createVo(SqlResultSet resultSet){
		String baseName = resultSet.getName();
		if(StringUtils.isNotBlank(tableConfig.getSymbol())){
			baseName = resultSet.getName().replace(tableConfig.getSymbol(),"");
		}
		if(StringUtils.isNotBlank(tableConfig.getTablePri())){
			String pri = tableConfig.getTablePri();
			if(baseName.indexOf(pri)!=-1){
				baseName = baseName.substring(baseName.indexOf(pri)+pri.length());
			}
		}
		baseName = StringUtils.upperCaseFirstChar(StringUtils.underlineToCamel(baseName));
		
		EntityVo vo = new EntityVo();
		vo.setName(baseName);
		vo.setAliasName(StringUtils.lowerCaseFirstChar(baseName));
		for(Column column:resultSet.getColumns()){
			EntityAttributeVo attribute = convertColumn(column);
			vo.getAttributes().add(attribute);
		}
		vo.setTable(resultSet);
		return vo;
	}
	public static EntityVo createVo(Table table){
		String baseName = table.getName();
		if(StringUtils.isNotBlank(tableConfig.getSymbol())){
			baseName = table.getName().replace(tableConfig.getSymbol(),"");
		}
		if(StringUtils.isNotBlank(tableConfig.getTablePri())){
			String pri = tableConfig.getTablePri();
			if(baseName.indexOf(pri)!=-1){
				baseName = baseName.substring(baseName.indexOf(pri)+pri.length());
			}
		}
		baseName = StringUtils.upperCaseFirstChar(StringUtils.underlineToCamel(baseName.toLowerCase()));
		EntityVo vo = new EntityVo();
		vo.setName(baseName);
		vo.setAliasName(StringUtils.lowerCaseFirstChar(baseName));
		for(Column column:table.getColumns()){
			EntityAttributeVo attribute = convertColumn(column);
			attribute.setColumn(column);
			vo.getAttributes().add(attribute);
		}
		EntityAttributeVo primaryKey = convertColumn(table.getPrimaryKey());
		vo.setPrimaryKey(primaryKey);
		vo.setTable(table);
		return vo;
	}
	
	private static EntityAttributeVo convertColumn (Column column){
		String name = column.getColumnName();
		if(StringUtils.isNotBlank(tableConfig.getColumnPri())&&name.indexOf(tableConfig.getColumnPri())==0){
			int length = tableConfig.getColumnPri().length();
			name = name.substring(length);
		}
		name = StringUtils.lowerCaseFirstChar(StringUtils.underlineToCamel(name));
		JdbcTypeInformation type = column.getType();
		String comment = column.getColumnComment();
		EntityAttributeVo vo = new EntityAttributeVo();
		String packagePath = type.getJavaPackage();
		String className = type.getJavaShortName();
		String fullName = type.getJavaFullName()==null?"":type.getJavaFullName();
		vo.setAttributeName(name);
		vo.setComment(comment);
		vo.setStrPackage(packagePath);
		vo.setShortName(className);
		vo.setFullName(fullName);
		vo.setAliasName(StringUtils.lowerCaseFirstChar(name));
		vo.setColumn(column);
		return vo;
	}
	
}
