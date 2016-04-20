${r'$'}(document).ready(function() ${r'{'}
	${r'$'}(document.body).append(buildEditWindow(450,400,'',${r'$'}('#editPanel').val(),'save','saveSubmit'));
	${r'$'}.parser.parse();
	init();
});
<#assign primaryAttributeName="${mapping.columns[mapping.table.primaryKey.columnName].attributeName}"/>

function init() ${r'{'}
	${r'$'}('#dg').datagrid(${r'{'} 
		url:${r'$'}('#path').val()+"/${controller.module}/${controller.page}/list",
		queryParams:${r'{'}query:jsonFromt(${r'$'}('#queryForm').serializeArray())},
        loadMsg:"正在努力加载数据，请稍后...",
        onDblClickRow:function(index,row)${r'{'}
        	showEditPage(row);
        },
        columns:[[
					${r'{'}field:'${primaryAttributeName}',checkbox : true},
                    <#list entity.attributes as attribute>
					    <#if !attribute.column.primaryKey>
					       ${r'{'}field:'${attribute.attributeName}',title:'',align:'center',width:"120"}<#if attribute_has_next>,</#if>
						</#if>
					</#list>
                ]]
    });
	
	 ${r'$'}('#queryButton').click(function() ${r'{'}
			${r'$'}('#dg').datagrid('load',${r'{'}query:jsonFromt(${r'$'}('#queryForm').serializeArray())});
	 });
	 
	 ${r'$'}('#addButton').click(function() ${r'{'}
		 ${r'$'}('#saveForm').form('clear').form('disableValidation');
		 ${r'$'}("#save").panel(${r'{'}title:"新增"});
		 ${r'$'}('#save').window('open');
	 });
	 
	 ${r'$'}('#updateButton').click(function() ${r'{'}
		 var columns = ${r'$'}('#dg').datagrid('getSelections');
		 if (columns.length == 0) ${r'{'}
			${r'$'}.messager.alert('提示', '请选择一条要修改的数据！', 'warning');
		 } else if (columns.length > 1) ${r'{'}
			${r'$'}.messager.alert('提示', '只能选择一条要修改的数据！', 'warning');
		 } else ${r'{'}
			 showEditPage(columns[0]);
		}
	 });
	 
	 function showEditPage(row)${r'{'}
		 ${r'$'}('#saveForm').form('clear').form('disableValidation');
			${r'$'}("#save").panel(${r'{'}title:"修改"});
			${r'$'}("#${primaryAttributeName}").val(row.${primaryAttributeName});
			<#list entity.attributes as attribute>
			    <#if !attribute.column.primaryKey>
			       //${r'$'}("#${attribute.attributeName}").textbox('setValue',row.${attribute.attributeName});
				</#if>
			</#list>
			//$("#fEnabled").prop("checked",row.fEnabled==1);
			${r'$'}('#save').window('open');
	 }
	 
	 
	 ${r'$'}('#saveSubmit').click(function() ${r'{'}
		 var isValidate = ${r'$'}('#saveForm').form('enableValidation').form('validate');
			if (!isValidate) ${r'{'}
				return false;
			}
			${r'$'}.ajax(${r'{'}
				type : 'post',
				url : ${r'$'}('#path').val() + '/${controller.module}/${controller.page}/save',
				data : jsonFromt(${r'$'}('#saveForm').serializeArray()),
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) ${r'{'}
					${r'$'}.messager.progress(${r'{'}
						title : '请稍后',
						msg : '玩命加载中...'
					});
				},
				success : function(result) ${r'{'}
					${r'$'}.messager.progress('close');
					${r'$'}('#save').window('close');
					if (result.isFlag) ${r'{'}
						${r'$'}.messager.alert('提示', result.msg, 'info');
					} else ${r'{'}
						${r'$'}.messager.alert('提示', result.msg, 'error');
					}
					${r'$'}('#dg').datagrid('reload');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) ${r'{'}
					${r'$'}.messager.progress('close');
					${r'$'}.messager.alert('提示', '服务异常！', 'error');
				}
			});
	 });
	 
	 
	 ${r'$'}('#deleteButton').click(function() ${r'{'}
		 var columns = ${r'$'}('#dg').datagrid('getSelections');
			if (columns.length == 0) ${r'{'}
				${r'$'}.messager.alert('提示', '请选择一条要删除的数据！', 'warning');
			} else ${r'{'}
				${r'$'}.messager.confirm('提示', '确定需要删除这<span style="color:red;">'+ columns.length + "</span>条数据码？", function(r) ${r'{'}
					if (r) ${r'{'}
						var ids = [];
						for (var i = 0; i < columns.length; i++) ${r'{'}
							ids.push(columns[i].${primaryAttributeName})
						}
						${r'$'}.ajax(${r'{'}
							type : 'post',
							url : ${r'$'}('#path').val() + '/${controller.module}/${controller.page}/delete',
							data : ${r'{'}ids:ids},
							traditional :true,
							dataType : 'json',
							beforeSend : function(XMLHttpRequest) ${r'{'}
								${r'$'}.messager.progress(${r'{'}
									title : '请稍后',
									msg : '玩命加载中...'
								});
							},
							success : function(result) ${r'{'}
								${r'$'}.messager.progress('close');
								if (result.isFlag) ${r'{'}
									${r'$'}.messager.alert('提示', result.msg, 'info');
								} else ${r'{'}
									${r'$'}.messager.alert('提示', result.msg, 'error');
								}
								${r'$'}('#dg').datagrid('reload');
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) ${r'{'}
								${r'$'}.messager.progress('close');
								${r'$'}.messager.alert('提示', '服务异常！', 'error');
							}
						});
					}
				});
			}
	 });
	
}
