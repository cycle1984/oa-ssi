<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/common/common.jspf"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配置机构</title>
</head>
<body>

	<script type="text/javascript">
		$(function(){
		  	 
		});
		/**点击确定按钮方法定义 */
		var myGroup_saveUI_submitForm = function($dialog,$grid){
			$('#myGroup_saveUI_form').form('enableValidation');//启用验证
			if($('#myGroup_saveUI_form').form('validate')){//如果表单验证通过
				$('#myGroupSaveUI_OKbtn').linkbutton('disable');//确定按钮禁用
				var url=null;
				if($('#myGroup_saveUI_form_id').val().length>0){
					url=getRealPath()+'/myGroup/edit.do';//若隐藏id有值，则是修改，反之是新增
				}else{
					url=getRealPath()+'/myGroup/save.do';
				}
				$.post(url,$('#myGroup_saveUI_form').serialize(),function(r){
					if(r.success){
						if($('#myGroup_saveUI_form_id').val().length>0){//更新操作情况
							$grid.datagrid('reload');//更新操作后刷新
						}else{//新增情况
							$grid.datagrid('insertRow',{
			   				    index:0,
			   				    row:r.obj
			   				});
						}
						$dialog.dialog('close');
		   				$.messager.show({
							title : '提示',
							msg : r.msg
						});
					}else{//失败情况
						$.messager.alert('提示', r.msg,'error',function(){
		   					$('#myGroupSaveUI_OKbtn').linkbutton('enable');//开启取消按钮禁用
		   				});
					}
				},'json');
			}
		};
		
		/**
	  	 *编辑机构信息，回显数据 
	  	 */
	  	var myGroup_saveUI_editForm = function($data){
  			$.messager.progress({
  				text : '数据加载中....'
  			});
  			$('#myGroup_saveUI_form').form('load',{//表单内数据赋值
  				id : $data.id,
  				name : $data.name,
  				remark:$data.remark
  			});
  			$.messager.progress('close');//关闭数据加载提示窗口
		};
	</script>
	
	<div style="text-align: center;vertical-align: middle;">
		<fieldset>
			<legend>机构基本信息</legend>
			<form id="myGroup_saveUI_form"  method="post" class="easyui-form" data-options="novalidate:true">
				<input name="id" id="myGroup_saveUI_form_id" type="hidden"><!-- 隐藏机构的ID主键  -->
				<table style="margin:0px auto;">
					<tr>
						<th>机构名称</th>
						<td><input name="name" id="myGroup_saveUI_form_name" class="easyui-textbox" data-options="required:true"></td>
					</tr>
					<tr>
						<th>描述</th>
						<td><input name="remark" class="easyui-textbox" data-options="multiline:true,height:50"></td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
</body>
</html>