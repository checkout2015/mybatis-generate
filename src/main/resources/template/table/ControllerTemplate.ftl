package ${controller.strPackage};

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import cn.xn.channel.base.BaseController;
import com.xiaoniu.mybatis.paginator.domain.PageRequest;
<#if !vo.primaryKey.column.autoIncrement>
import cn.xn.channel.utils.Util;
</#if>
import cn.xn.channel.utils.ResponseUtil;
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
@Controller
@RequestMapping(value = "${generate.config.module}/${vo.aliasName}")
public class ${controller.shortName} extends BaseController{

	@Autowired
	private ${service.shortName} ${service.aliasName};
	
	private static final Map<String,String> mapping = getMapping();

	@RequestMapping(value = "init")
	public String init() {
		return "${generate.config.module}/${vo.aliasName}List";
	}

	@RequestMapping(value = "list")
	@ResponseBody
	public Object list(PageRequest page) {
		if(page.getSort()!=null&&!"".equals(page.getSort())){
			page.setSort(mapping.get(page.getSort()));
		}
		return ${service.aliasName}.list(page).toPageResponseDefaultFormate();
	}

	@RequestMapping(value = "save")
	@ResponseBody
	public Object save(${entity.shortName} ${entity.aliasName}) {
		<#assign  primaryGetMethod="${vo.primaryKey.getMethodName}()"/>
	    <#assign  primarySetMethod="${vo.primaryKey.setMethodName}"/>
	    ${entity.shortName} save = null;
		if (${entity.aliasName}.${primaryGetMethod}==null||"".equals(${entity.aliasName}.${primaryGetMethod})) {
			save = new ${entity.shortName}();
		 <#if !vo.primaryKey.column.autoIncrement>
		    save.${primarySetMethod}(Util.getUUID());
		 </#if>
   <#list vo.attributes as attribute>
     <#if !attribute.column.primaryKey>
     		save.${attribute.setMethodName}(${entity.aliasName}.${attribute.getMethodName}());
	 </#if>
   </#list>
			${service.aliasName}.add(save);
			return ResponseUtil.getSuccessResponse();
		} else {
		    save = ${service.aliasName}.getByPrimaryKey(${entity.aliasName}.${primaryGetMethod});
   <#list vo.attributes as attribute>
     <#if !attribute.column.primaryKey>
     		save.${attribute.setMethodName}(${entity.aliasName}.${attribute.getMethodName}());
	 </#if>
   </#list>
			${service.aliasName}.update(save);
			return ResponseUtil.getSuccessResponse();
		}
	}

	

	@RequestMapping(value = "delete")
	@ResponseBody
	public Object delete(String[] ids) {
	    if(ids!=null&&ids.length>0){
	       ${service.aliasName}.deleteBatch(ids);
	    }
		return ResponseUtil.getSuccessResponse();
	}

	@RequestMapping(value = "get")
	@ResponseBody
	public Object get(String id) {
		return ${service.aliasName}.getByPrimaryKey(id);
	}
	
	private static Map<String,String> getMapping(){
		Map<String,String> map = new HashMap<String,String>();
		<#list vo.attributes as attribute>
		map.put("${attribute.attributeName}", "${attribute.column.sqlName}");
		</#list>
		return map;
	}
}
