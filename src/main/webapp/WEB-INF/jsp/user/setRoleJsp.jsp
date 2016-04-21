<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>批量审核用户</title>
</head>
<body>
	<form id="user_setRole_form" method="post">
		<br>
		&nbsp;&nbsp;权限级别&nbsp;<input class="easyui-combobox" id="user_setRole_roleId" name="roleId" style="width: 100px" data-options="valueField:'id',textField:'name',required:true,editable:false,url:'${pageContext.request.contextPath}/role/findAll.do'">
	</form>
	<script type="text/javascript">
		var user_setRole_submitForm = function($dialog,$grid,$ids){
			if($('#user_setRole_roleId').combobox('getValue')){//如果roleId不为空验证通过
				$('#user_setRole_OKbtn').linkbutton('disable');//确定按钮禁用
				$.post("${pageContext.request.contextPath}/user/setRole.do",{ids:$ids,roleId:$('#user_setRole_roleId').combobox('getValue')},function(r){
					if(r.success){
						$grid.datagrid('reload');//更新操作后刷新
						$dialog.dialog('close');
		   				$.messager.show({
							title : '提示',
							msg : r.msg
						});
					}else{//失败情况
						$.messager.alert('提示', r.msg,'error',function(){
		   					$('#user_setRole_OKbtn').linkbutton('enable');//开启取消按钮禁用
		   				});
					}
				},'json');	
			}
		}
	</script>
</body>
</html>