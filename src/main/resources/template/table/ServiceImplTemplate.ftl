package ${serviceImpl.strPackage};

import java.util.List;
import java.lang.Integer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;

import ${entity.fullName};
import ${dao.fullName};
import ${service.fullName};
import ${serviceImpl.fullName};


 /**
 * 
 * @描述： 实体Bean
 * 
 * @创建人： ${generate.author}
 * 
 * @创建时间：${generate.createDate}
 * 
 * Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
@Service("${service.aliasName}")
public class ${serviceImpl.shortName} implements ${service.shortName}{
	
	@Autowired
	private ${dao.shortName} ${dao.aliasName};
	
	public Integer addBatch(List<${entity.shortName}> list){
		return ${dao.aliasName}.addBatch(list);
	}
	
	public Integer updateBatch(List<${entity.shortName}> list){
		return ${dao.aliasName}.updateBatch(list);
	}
	
	public Integer deleteBatch(Object[] objects){
		return ${dao.aliasName}.deleteBatch(objects);
	}
	
	public void add(${entity.shortName} ${entity.aliasName}){
		${dao.aliasName}.add(${entity.aliasName});
	}
	
	public void update(${entity.shortName} ${entity.aliasName}){
		${dao.aliasName}.update(${entity.aliasName});
	}

	public void deleteByPrimaryKey(Object id){
		${dao.aliasName}.deleteByPrimaryKey(id);
	}
	
	public ${entity.shortName} getByPrimaryKey(Object id){
		return ${dao.aliasName}.getByPrimaryKey(id);
	}

	public PageList<${entity.shortName}> list(PageRequest request){
		return ${dao.aliasName}.list(request);
	}

}
