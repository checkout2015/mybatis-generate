package ${entity.strPackage};

import com.xiaoniu.platform.base.common.BaseEntity;
<#list vo.attrFullNames as attrFullName>
 import ${attrFullName};
</#list>
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
public class ${entity.shortName} extends BaseEntity {
	
	private static final long serialVersionUID = ${generate.serialVersionUID};
	
<#list vo.attributes as attribute>
<#if attribute.comment??>
    /**
     *${attribute.comment}
     */
</#if>     
	private ${attribute.shortName} ${attribute.attributeName};
	
</#list>


<#list vo.attributes as attribute>
	public void ${attribute.setMethodName}(${attribute.shortName} ${attribute.attributeName}){
		this.${attribute.attributeName} = ${attribute.attributeName};
	}
	
	public ${attribute.shortName} ${attribute.getMethodName}(){
		return ${attribute.attributeName};
	}
	
</#list>

}

