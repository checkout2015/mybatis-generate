package ${controller.strPackage};

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.xn.lhlc.api.util.WebUtil;
import cn.xn.lhlc.base.api.BaseResponse;
import cn.xn.lhlc.base.api.BaseController;
import cn.xn.lhlc.base.api.PaginatorRequest;
import cn.xn.lhlc.base.api.RequestHead;
import cn.xn.lhlc.base.PaginatorSevReq;
import cn.xn.lhlc.base.PaginatorSevResp;
import cn.xn.lhlc.utils.ResponseUtil;
import cn.xn.lhlc.utils.StringUtils;
import ${entity.fullName};
import ${response.fullName};
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
@Controller
public class ${controller.shortName} extends BaseController{

	@Autowired
	private ${service.shortName} ${service.aliasName};
	
	@RequestMapping(params = "method=xxxx")
	@ResponseBody
	public BaseResponse query${entity.shortName}(PaginatorRequest req,RequestHead head) {
		String userId = WebUtil.getUserId(head.getToken());
		PaginatorSevReq pageRequest = req.toPaginatorSevReq();
		if(StringUtils.isNotBlank(userId)){
			pageRequest.getQueryConditions().put("userId",userId);
		}
		PaginatorSevResp<${entity.shortName}> datas = ${service.aliasName}.query${entity.shortName}(pageRequest);
		return ResponseUtil.getSuccessResponse(datas,${response.shortName}.class);
	}
}
