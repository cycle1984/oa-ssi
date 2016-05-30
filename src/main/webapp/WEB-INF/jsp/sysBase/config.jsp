<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>系统配置</title>
    
  </head>
  
  <body>
  	<script type="text/javascript">
  		$(function(){
  			$('#configSubmit').bind('click', function(){    
  		        if($('#sysBase_config_form').form('validate')){
  		        	$.post('${pageContext.request.contextPath}/sysBase/edit.do',sy.serializeObject($('#sysBase_config_form')),function(r){
  		        		if(r.success){
  		        			$.messager.show({
  		        				title:'提示',
  		        				msg:r.msg,
  		        			});
  		        		}else{
  		        			$.messager.alert('失败提示',r.msg,'error');
  		        		}
  		        	},'json');
  		        }    
  		    });
  		});
  	</script>
  	<div class="easyui-panel" data-options="border:false,fit:true" style="padding: 2px">
  		
  		<form id="sysBase_config_form">
  			<input type="hidden" name="id" value="${sysBase.id }">
  		<table class="table">
  			<tr>
  				<th>公文存储路径</th>
  				<td><input name="path" value="${sysBase.path }" size="100px" class="easyui-textbox" data-options="required:true,prompt:'格式：D:/upload/'"></td>
  			</tr>
  			<tr>
  				<th>刷新频率</th>
  				<td><input name="refleshTime" value="${sysBase.refleshTime }" size="2px" class="easyui-textbox" data-options="required:true">分钟</td>
  			</tr>
  			<tr>
  				<td colspan="2" style="text-align: center;">
  					<a id="configSubmit" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">提交</a>
  				</td>
  			</tr>
  		</table>
  		</form>
  	</div>
    
  </body>
</html>
