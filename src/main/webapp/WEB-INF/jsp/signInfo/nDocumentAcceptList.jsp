<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>待办公文列表</title>
</head>
<body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jsp/signInfo_nDocumentAcceptList.js"></script>
	
	<!-- 公文全部签收情况下，刷新的频率 -->
	<input type="hidden" id="signInfo_nDocumentAcceptList_refleshtime" value="${session.config.refleshTime }">
	
	<!-- 菜单栏 -->
	<div id="signInfo_nDocumentAcceptList_toolbar" style="display: none;">
		 <table>
			<tr>
				<td><form id="signInfo_nDocumentAcceptList_searchForm" style="margin:0px;"><!--style="margin:0px;"可以处理加form表单后，tr间隙变大的问题  -->
						<table style="font-size: 13px;">
							<tr>
								<td>公文标题(可模糊查询)</td>
								<td><input name="document.documentTitle" style="width: 120px; " class="easyui-textbox"/></td>
								<td>发文单位</td>
								<td id="signInfo_nDocumentAcceptList_searchForm_unit_td"><input id="signInfo_nDocumentAcceptList_searchForm_unit" name="document.publishUnit.name" style="width: 80px" class="easyui-textbox"></input></td>
								<td>发布时间</td>
								<td><input type="text" name="createDatetime_start" class="easyui-datebox" data-options="showSeconds:false,editable:false" style="width: 120px;"/>-<input name="createDatetime_end" type="text" class="easyui-datebox"  data-options="showSeconds:false,editable:false" style="width: 120px;"/></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="$('#signInfo_nDocumentAcceptList_grid').datagrid('load',sy.serializeObject($('#signInfo_nDocumentAcceptList_searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#signInfo_nDocumentAcceptList_searchForm input').val('');$('#signInfo_nDocumentAcceptList_grid').datagrid('load',{});">重置过滤</a></td>
								
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-page_white_world',plain:true" onclick="signAll()">签收所有</a></td>
							</tr>
						</table>
					</form></td>
			</tr>
		</table>
	</div>
	<table id="signInfo_nDocumentAcceptList_grid"></table>
	
	<!-- 文件签收窗口 -->
	<div id="signInfo_nDocumentAcceptList_acceptDialog" style="display: none;padding: 10px;overflow: hidden;" >
	    <form  method="post" id="signInfo_nDocumentAcceptList_acceptDialog_form" style="margin:0px;">
	    <input id="signInfo_nDocumentAcceptList_acceptDialog_id" name="signInfo_nDocumentAcceptList_acceptDialog_id" type="hidden">
	    <table style="width: 100%; height: 100%;" class="table">
	        <tr>
	            <th style="text-align: center;">签收人</th><th style="text-align: center;">签收密码</th>
	        </tr>
	        <tr>
	            <td style="text-align: center;"><input name="name" type="text" value="<shiro:principal property="name"></shiro:principal>" class="easyui-textbox" data-options="required:true,iconCls:'icon-man',editable:false,disabled:true"></td>
	            <td style="text-align: center;"><input id="accept_pwd" name="pwd" type="password" class="easyui-textbox" data-options="required:true,iconCls:'icon-lock'"></td>
	        </tr>
	    </table>
	    </form>
	</div>
</body>
</html>