package ${entity.strPackage};

import cn.xn.lhlc.base.BaseEntity;
import cn.xn.lhlc.api.util.WebUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
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
public class ${response.shortName} extends BaseEntity {
	
	private static final long serialVersionUID = ${generate.serialVersionUID};
	
	public ${response.shortName}() {

	}

	public ${response.shortName}(${entity.shortName} ${entity.aliasName}) {
		WebUtil.initObj(this, ${entity.aliasName});
	}
	
<#list vo.attributes as attribute>
<#if attribute.comment??>
    /**
     *${attribute.comment}
     */
</#if>
<#if attribute.fullName == "java.lang.Boolean">   
	private boolean ${attribute.attributeName}=true;
<#else>
	private String ${attribute.attributeName};
</#if>	
</#list>


<#list vo.attributes as attribute>
	public void ${attribute.setMethodName}(<#if attribute.fullName == "java.lang.Boolean">boolean<#else>String</#if> ${attribute.attributeName}){
		this.${attribute.attributeName} = ${attribute.attributeName};
	}
	
	public <#if attribute.fullName == "java.lang.Boolean">boolean<#else>String</#if> ${attribute.getMethodName}(){
		return ${attribute.attributeName};
	}
	
</#list>

	public String toString() {
		return JSON.toJSONString(this, SerializerFeature.UseISO8601DateFormat);
	}
}

