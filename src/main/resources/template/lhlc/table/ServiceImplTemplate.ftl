package ${serviceImpl.strPackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ${dao.fullName};
import ${service.fullName};


 /**
 * 
 * @描述： 实体Bean
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
	


}
