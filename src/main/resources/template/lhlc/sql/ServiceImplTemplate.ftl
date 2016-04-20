package ${serviceImpl.strPackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.xiaoniu.mybatis.paginator.domain.PageList;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;
import cn.xn.lhlc.base.PaginatorSevReq;
import cn.xn.lhlc.base.PaginatorSevResp;
import cn.xn.lhlc.enumeration.OrderEnum;
import cn.xn.lhlc.utils.PaginatorUtil;
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
