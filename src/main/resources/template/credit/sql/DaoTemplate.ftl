package ${dao.strPackage};

import com.xiaoniu.platform.base.common.BaseDao;
import com.xiaoniu.platform.paginator.domain.PageList;
import com.xiaoniu.platform.paginator.domain.PageRequest;
import ${entity.fullName};

 /**
 * 
 * 描述： Dao接口
 * 
 * @创建人： ${generate.author}
 * 
 * @创建时间：${generate.createDate}
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface ${dao.shortName} extends BaseDao{
	
	public PageList<${entity.shortName}> query${entity.shortName}(PageRequest pageRequest); 

}
