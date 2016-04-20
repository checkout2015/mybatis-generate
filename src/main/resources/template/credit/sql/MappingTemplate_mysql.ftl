<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${dao.fullName}">
<resultMap  type="${entity.fullName}" id="${entity.shortName}ResultMap">
<#list vo.attributes as attribute>
	<result column="${attribute.column.columnName}" property="${attribute.attributeName}" jdbcType="${attribute.column.jdbcType}"/>
</#list>
</resultMap>
<select id="query${entity.shortName}" resultType="map"  >
	${sqlStr}
</select>

</mapper>