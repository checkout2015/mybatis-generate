package ${service.strPackage};


import java.util.List;
import java.lang.Integer;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;
import ${entity.fullName};
import ${service.fullName};
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
public interface ${service.shortName}{

	public Integer addBatch(List<${entity.shortName}> list);
	
	public Integer updateBatch(List<${entity.shortName}> list);
	
	public Integer deleteBatch(Object[] objects);
	
	public void add(${entity.shortName} ${entity.aliasName});
	
	public void update(${entity.shortName} ${entity.aliasName});

	public void deleteByPrimaryKey(Object id);
	
	public ${entity.shortName} getByPrimaryKey(Object id);

	public PageList<${entity.shortName}> list(PageRequest request);
	
}
