package ${serviceImpl.strPackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoniu.platform.base.PaginatorSevReq;
import com.xiaoniu.platform.base.PaginatorSevResp;
import com.xiaoniu.platform.base.enumeration.OrderEnum;
import com.xiaoniu.platform.base.utils.PaginatorUtil;
import com.xiaoniu.platform.paginator.domain.PageList;
import com.xiaoniu.platform.paginator.domain.PageRequest;
import ${entity.fullName};
import ${dao.fullName};
import ${service.fullName};
import ${serviceImpl.fullName};


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
@Service("${service.aliasName}")
public class ${serviceImpl.shortName} implements ${service.shortName}{
	
	@Autowired
	private ${dao.shortName} ${dao.aliasName};
		
		
	public PaginatorSevResp<${entity.shortName}> query${entity.shortName}(PaginatorSevReq request) {
		PageRequest req = PaginatorUtil.toPageRequest(request);
		if(request.getSort()==null||request.getOrder()==null){
			//req.setSort("bizTime");
			req.setOrder(OrderEnum.DESC.getValue());
		}
		PageList<${entity.shortName}> datas = ${dao.aliasName}.query${entity.shortName}(req);
		return  PaginatorUtil.toPaginatorSevResp(datas);
	}	

}
