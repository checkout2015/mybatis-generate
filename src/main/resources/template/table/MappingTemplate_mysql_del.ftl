<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${dao.fullName}">
<#assign primaryAttributeName="${mapping.columns[mapping.table.primaryKey.columnName].attributeName}"/>
<#assign primarySqlName="${mapping.table.primaryKey.sqlName}"/>	
<parameterMap type="${entity.fullName}" id="${entity.shortName}ParameterMap" />	
<!-- Result Map-->
<resultMap  type="${entity.fullName}" id="${entity.shortName}ResultMap">
<#list entity.attributes as attribute>
	<result column="${attribute.column.columnName}" property="${attribute.attributeName}" jdbcType="${attribute.column.jdbcType}"/>
</#list>
</resultMap>
  <sql id="Base_Column_List">
    <trim suffixOverrides=",">
	  	<#list entity.attributes as attribute>
	    	${attribute.column.sqlName},
	    </#list>
    </trim>
  </sql>  
  	
  <select id="getByPrimaryKey"  resultMap="${entity.shortName}ResultMap">
    select 
    <include refid="Base_Column_List" />
    from ${mapping.table.sqlName} where <#if mapping.table.primaryKey??> ${mapping.table.primaryKey.sqlName} = #${r'{'}0}</#if>
  </select>	
  
   <delete id="deleteByPrimaryKey" >
    delete from ${mapping.table.sqlName}  <#if mapping.table.primaryKey ??>where ${mapping.table.primaryKey.sqlName} = #${r'{'}0}</#if>
  </delete>
  
  
  <insert id="add" parameterMap="${entity.shortName}ParameterMap" >
		INSERT INTO ${mapping.table.sqlName}
		<trim prefix="(" suffix=")" suffixOverrides=",">
			<#list entity.attributes as attribute>
			 	<if test=" null != ${attribute.attributeName} and '' != ${attribute.attributeName} ">
					${attribute.column.sqlName},
				</if>
			</#list>
		</trim>
		<trim prefix="VALUES(" suffix=")" suffixOverrides=",">
		     <#list entity.attributes as attribute>
			 	<if test=" null != ${attribute.attributeName} and '' != ${attribute.attributeName} ">
					 	#${r'{'}${attribute.attributeName}},
				</if>
			</#list>
		</trim>
	</insert>
  
  
  <update id="update" parameterMap="${entity.shortName}ParameterMap">
		UPDATE ${mapping.table.sqlName}
		<set>
			<trim suffixOverrides=",">
				<#list entity.attributes as attribute>
				   <#if !attribute.column.primaryKey>
					<if test=" null != ${attribute.attributeName} and '' != ${attribute.attributeName} ">
						${attribute.column.sqlName} = #${r'{'}${attribute.attributeName}},
					</if>
					</#if>
				</#list>
			</trim>
		</set>
		<where>
			<#list entity.attributes as attribute><#if attribute.column.primaryKey> ${attribute.column.sqlName} = #${r'{'}${attribute.attributeName}}</#if></#list>
		</where>
	</update>
  

  <insert id="addBatch" parameterType="java.util.List">
	insert into ${mapping.table.sqlName} 
	<trim prefix="(" suffix=")" >
			<include refid="Base_Column_List" />
	</trim>
	VALUES
	<foreach item="item" index="index" collection="list" separator=",">
		<trim prefix="(" suffix=")" suffixOverrides=",">
				<#list entity.attributes as attribute> #${r'{'}item.${attribute.attributeName},jdbcType=${attribute.column.jdbcType}},</#list>
		</trim>
	</foreach>
</insert>

 
<update id="updateBatch" parameterType="java.util.List">
      <foreach collection="list" item="item" index="index" open="" close="" separator=";">
            update ${mapping.table.sqlName}
            <set>
              	<trim suffixOverrides=",">
				   <#list entity.attributes as attribute>
				     <#if !attribute.column.primaryKey>
					   <if test="item.${attribute.attributeName}!=null and item.${attribute.attributeName}!=''">
					        ${attribute.column.sqlName} = #${r'{'}item.${attribute.attributeName},jdbcType=${attribute.column.jdbcType}},
					   </if>
					   </#if>
				   </#list>
			   </trim>
            </set>
             where <#list entity.attributes as attribute><#if attribute.column.primaryKey>${attribute.column.sqlName} = #${r'{'}item.${attribute.attributeName}}</#if></#list>
     </foreach>
</update>         

<delete id="deleteBatch" parameterType="Object">
	DELETE FROM ${mapping.table.sqlName} 
	<where>
		<foreach item="item" index="index" collection="array"  open="(" separator="or" close=") ">
			 <#list entity.attributes as attribute><#if attribute.column.primaryKey> ${attribute.column.sqlName}  = #${r'{'}item}</#if></#list>
		</foreach>
	</where>
</delete>
    
    
<select id="list" resultMap="${entity.shortName}ResultMap"  >
		SELECT * FROM ${mapping.table.sqlName} T WHERE 1=1
		<#list entity.attributes as attribute>
		<if test=" null != ${attribute.attributeName} and '' != ${attribute.attributeName} ">
				AND	T.${attribute.column.sqlName} = #${r'{'}${attribute.attributeName}}
		</if>
		</#list>
</select>

</mapper>