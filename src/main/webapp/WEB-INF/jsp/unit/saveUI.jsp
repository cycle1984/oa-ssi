<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>配置单位</title>
</head>
<body>

	<div style="text-align: center;vertical-align: middle;">
		<fieldset>
			<legend>单位基本信息</legend>
			<form id="unit_saveUI_form" method="post" class="easyui-form" data-options="novalidate:true">
				<input name="id" id="unit_saveUI_form_id" type="hidden"><!-- 隐藏单位的ID主键  -->
				<table style="margin:0px auto;">
					<tr>
						<th>单位简称</th>
						<td><input name="name" id="unit_saveUI_form_name" class="easyui-textbox" data-options="required:true"></td>
					</tr>
					<tr>
						<th>单位全称</th>
						<td><input name="fullName" class="easyui-textbox" data-options="required:true"></td>
					</tr>
					<tr>
						<th>办公室电话</th>
						<td><input name="tel" id="unit_saveUI_form_tel" class="easyui-textbox" data-options="required:true"></td>
					</tr>
					<tr>
						<th>所属系统</th>
						<td><input name="myGroup.id" id="unit_SaveUI_unitSelect"></td>
					</tr>
					<tr>
						<th>是否允许接收文件</th>
						<td style="font-size: 15px;">是<input type="radio" name="state" value="0" checked="checked">否<input type="radio" name="state" value="1"></td>
					</tr>
					<tr>
						<th>描述</th>
						<td><input name="remark" class="easyui-textbox" data-options="multiline:true,height:50"></td>
					</tr>
				</table>
			</form>
		</fieldset>
	</div>
	
	<script type="text/javascript">
	
		$(function(){
			/**
	  		 * myGroup所属单位下来菜单初始化
	  		 */
	  		$('#unit_SaveUI_unitSelect').combobox({
	  			url:'${pageContext.request.contextPath}/myGroup/findAll.do',
	  			valueField:'id',//基础数据值名称绑定到该下拉列表框
	  		    textField:'name',//基础数据字段名称绑定到该下拉列表框
	  		    editable:false,//定义用户是否可以直接输入文本到字段中
	  		    required:true
	  		});
		});
		
		/**点击确定按钮方法定义 */
		var unit_saveUI_submitForm = function($dialog,$grid){
			
			$('#unit_saveUI_form').form('enableValidation');
			if($('#unit_saveUI_form').form('validate')){//如果表单验证通过
				$('#unitSaveUI_OKbtn').linkbutton('disable');//确定按钮禁用
				var url=null;
				if($('#unit_saveUI_form_id').val().length>0){
					url='${pageContext.request.contextPath}/unit/edit.do';//若隐藏id有值，则是修改，反之是新增
				}else{
					url='${pageContext.request.contextPath}/unit/save.do';
				}
				$.post(url,$('#unit_saveUI_form').serialize(),function(r){
					if(r.success){
						if($('#unit_saveUI_form_id').val().length>0){//更新操作情况
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
		   					$('#unitSaveUI_OKbtn').linkbutton('enable');//开启取消按钮禁用
		   				});
					}
				},'json');
			}
		};
		
		/**
	  	 *编辑单位信息，回显数据 
	  	 */
	  	var unit_saveUI_editForm = function($data){
  			$.messager.progress({
  				text : '数据加载中....'
  			});
  			$('#unit_saveUI_form').form('load',{//表单内数据赋值
  				id : $data.id,
  				name : $data.name,
  				fullName:$data.fullName,
  				tel: $data.tel,
  				remark:$data.remark
  			});
  			
  			if($data.state==0){//用于是否允许收文的单选按钮回显
  				$("input[name='state']")[0].checked=true;
  			}else{
  				$("input[name='state']")[1].checked=true;
  			}
  			
  			if($data.myGroup!=null){
//   				$('#unit_saveUI_form').form('load',{//表单内数据赋值
//   					myGroup.id:$data.myGroup.id//给所属机构赋值
//   				});
				$('#unit_SaveUI_unitSelect').combobox('setValue',$data.myGroup.id);
  			}
  			$.messager.progress('close');//关闭数据加载提示窗口
		};
	</script>
</body>
</html>