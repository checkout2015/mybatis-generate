<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	request.setAttribute("ctx", request.getContextPath());
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
<link rel="stylesheet" type="text/css" href="${r'$'}${r'{'}ctx}/widget/css/default.css">
<link rel="stylesheet" type="text/css" href="${r'$'}${r'{'}ctx}/widget/ui/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${r'$'}${r'{'}ctx}/widget/ui/themes/color.css">
<link rel="stylesheet" type="text/css" href="${r'$'}${r'{'}ctx}/widget/ui/themes/default/easyui.css">
</head>
<body>
	<input id="path" type="hidden" value="${r'$'}${r'{'}ctx}" />
	<table id="dg" title="" class="easyui-datagrid" toolbar="#toolbar" rownumbers="true" pagination="true" pageList="[10,20,40,50,100,500]" fit="true" style="width: 100%;"></table>
	<div id="toolbar">
		<div>
			<a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-add" plain="true" id="addButton">添  加</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-edit" plain="true" id="updateButton">修  改</a>
	        <a href="javascript:void(0)" class="easyui-linkbutton" iconCls="icon-remove" plain="true" id="deleteButton">删  除</a>
		</div>
		<div>
			<form id="queryForm">
				 <input class="easyui-textbox" name="name" style="width: 150px"> <a id="queryButton"  class="easyui-linkbutton" iconCls="icon-search">查 询</a>
			</form>
		</div>
	</div>

	<textarea rows="0" cols="0" style="display: none;" id="editPanel">
		<form id="saveForm" method="post">
			<input type="hidden" name="${vo.primaryKey.attributeName}" id="${vo.primaryKey.attributeName}"/>
			<table cellpadding="5">
					<!-- <input type="checkbox" name="" id="" value="1"> 
					<input name="" id="" class="easyui-textbox" data-options="multiline:true,required:true,novalidate:true"  style="width:300px;height:200px"> -->
				<#list vo.attributes as attribute>	
				<#if !attribute.column.primaryKey>
				<tr>
					<td>${attribute.comment}:</td>
					<td><input class="easyui-textbox" name="${attribute.attributeName}" id="${attribute.attributeName}" data-options="required:true,novalidate:true" style="width:300px"></input></td>
				</tr>
				</#if>
			</#list>
			</table>
		</form>
	</textarea>
	<jsp:include page="../foot.jsp"></jsp:include>
</body>
<script type="text/javascript" src="${r'$'}${r'{'}ctx}/widget/js/commons.js"></script>
<script type="text/javascript" src="${r'$'}${r'{'}ctx}/widget/js/${generate.config.module}/${js.fileName}"></script>
</html>