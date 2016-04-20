package ${service.strPackage};

import com.xiaoniu.platform.base.PaginatorSevReq;
import com.xiaoniu.platform.base.PaginatorSevResp;
import ${entity.fullName};
import ${service.fullName};
 /**
 * 
 * 描述： 实体Bean
 * 
 * @创建人： ${generate.author}
 * 
 * @创建时间：${generate.createDate}
 * 
 * @Copyright (c) 深圳市小牛科技有限公司-版权所有
 */
public interface ${service.shortName}{

	public PaginatorSevResp<${entity.shortName}> query${entity.shortName}(PaginatorSevReq request);
	
}
