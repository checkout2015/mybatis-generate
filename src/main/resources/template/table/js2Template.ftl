$(document).ready(function() ${r'{'}
	$(document.body).append(buildEditWindow(450,400,'',$('#editPanel').val(),'save','saveSubmit'));
	$.parser.parse();
	init();
});
<#assign primaryAttributeName="${mapping.columns[mapping.table.primaryKey.columnName].attributeName}"/>

function init() ${r'{'}
	$('#dg').datagrid(${r'{'} 
		url:$('#path').val()+"/${controller.module}/${controller.page}/list",
		queryParams:${r'{'}query:jsonFromt($('#queryForm').serializeArray())},
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
	
	 $('#queryButton').click(function() ${r'{'}
			$('#dg').datagrid('load',${r'{'}query:jsonFromt($('#queryForm').serializeArray())});
	 });
	 
	 $('#addButton').click(function() ${r'{'}
		 $('#saveForm').form('clear').form('disableValidation');
		 $("#save").panel(${r'{'}title:"新增"});
		 $('#save').window('open');
	 });
	 
	 $('#updateButton').click(function() ${r'{'}
		 var columns = $('#dg').datagrid('getSelections');
		 if (columns.length == 0) ${r'{'}
			$.messager.alert('提示', '请选择一条要修改的数据！', 'warning');
		 } else if (columns.length > 1) ${r'{'}
			$.messager.alert('提示', '只能选择一条要修改的数据！', 'warning');
		 } else ${r'{'}
			 showEditPage(columns[0]);
		}
	 });
	 
	 function showEditPage(row)${r'{'}
		 $('#saveForm').form('clear').form('disableValidation');
			$("#save").panel(${r'{'}title:"修改"});
			$("#${primaryAttributeName}").val(row.${primaryAttributeName});
			<#list entity.attributes as attribute>
			    <#if !attribute.column.primaryKey>
			       //$("#${attribute.attributeName}").textbox('setValue',row.${attribute.attributeName});
				</#if>
			</#list>
			//$("#fEnabled").prop("checked",row.fEnabled==1);
			
			$('#save').window('open');
	 }
	 
	 
	 $('#saveSubmit').click(function() ${r'{'}
		 var isValidate = $('#saveForm').form('enableValidation').form('validate');
			if (!isValidate) ${r'{'}
				return false;
			}
			$.ajax(${r'{'}
				type : 'post',
				url : $('#path').val() + '/${controller.module}/${controller.page}/save',
				data : jsonFromt($('#saveForm').serializeArray()),
				dataType : 'json',
				beforeSend : function(XMLHttpRequest) ${r'{'}
					$.messager.progress(${r'{'}
						title : '请稍后',
						msg : '玩命加载中...'
					});
				},
				success : function(result) ${r'{'}
					$.messager.progress('close');
					$('#save').window('close');
					if (result.isFlag) ${r'{'}
						$.messager.alert('提示', result.msg, 'info');
					} else ${r'{'}
						$.messager.alert('提示', result.msg, 'error');
					}
					$('#dg').datagrid('reload');
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) ${r'{'}
					$.messager.progress('close');
					$.messager.alert('提示', '服务异常！', 'error');
				}
			});
	 });
	 
	 
	 $('#deleteButton').click(function() ${r'{'}
		 var columns = $('#dg').datagrid('getSelections');
			if (columns.length == 0) ${r'{'}
				$.messager.alert('提示', '请选择一条要删除的数据！', 'warning');
			} else ${r'{'}
				$.messager.confirm('提示', '确定需要删除这<span style="color:red;">'+ columns.length + "</span>条数据码？", function(r) ${r'{'}
					if (r) ${r'{'}
						var ids = [];
						for (var i = 0; i < columns.length; i++) ${r'{'}
							ids.push(columns[i].${primaryAttributeName})
						}
						$.ajax(${r'{'}
							type : 'post',
							url : $('#path').val() + '/${controller.module}/${controller.page}/delete',
							data : ${r'{'}ids:ids},
							traditional :true,
							dataType : 'json',
							beforeSend : function(XMLHttpRequest) ${r'{'}
								$.messager.progress(${r'{'}
									title : '请稍后',
									msg : '玩命加载中...'
								});
							},
							success : function(result) ${r'{'}
								$.messager.progress('close');
								if (result.isFlag) ${r'{'}
									$.messager.alert('提示', result.msg, 'info');
								} else ${r'{'}
									$.messager.alert('提示', result.msg, 'error');
								}
								$('#dg').datagrid('reload');
							},
							error : function(XMLHttpRequest, textStatus, errorThrown) ${r'{'}
								$.messager.progress('close');
								$.messager.alert('提示', '服务异常！', 'error');
							}
						});
					}
				});
			}
	 });
	
}
