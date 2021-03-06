<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公文发布</title>
</head>
<body>

	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jsp/documentPublishList.js"></script>
	<!-- 菜单栏 -->
	<div id="document_publishList_toolbar" style="display: none;">
		 <table>
			<tr>
				<td><form id="document_publishList_searchForm" style="margin:0px;"><!--style="margin:0px;"可以处理加form表单后，tr间隙变大的问题  -->
						<table style="font-size: 13px;">
							<tr>
								<td>公文标题</td>
								<td><input name="documentTitle" style="width: 120px; " class="easyui-textbox" data-options="prompt:'支持模糊查询'"/></td>
								<c:if test="${sessionScope.userSession.loginName=='admin'}">
									<td>发文单位</td>
									<td id="document_publishList_searchForm_unit_td"><input id="document_publishList_searchForm_unit" name="publishUnit.name" class="easyui-textbox" data-options="prompt:'支持模糊查询'" style="width:150px" ></input></td>
								</c:if>
								<td>发布时间</td>
								<td><input type="text" name="createDatetime_start" class="easyui-datebox" data-options="showSeconds:false,editable:false" style="width: 135px;"/>-<input id="document_publishList_QUERY_tcreatedatetime_D_LE" name="createDatetime_end" type="text" class="easyui-datebox"  data-options="showSeconds:false,editable:false" style="width: 135px;"/></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="filtersubmit()">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#document_publishList_searchForm input').val('');$('#document_publishList_grid').datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form></td>
			</tr>
			<tr>
				<td>
					<table>
						<tr>
							<shiro:hasPermission name="document:save">
								<td><a onclick="addFunDocumentPublish();" href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'icon-add',plain:true" title="公文添加">发布公文</a></td>
							</shiro:hasPermission>
							<shiro:hasPermission name="document:delete">
								<td><a onclick="delFunDocumentPublish();" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-remove'" title="公文删除">删除</a></td>
							</shiro:hasPermission>
							<td><a onclick="$('#document_publishList_grid').datagrid('reload');" href="javascript:void(0);" class="easyui-linkbutton" data-options="plain:true,iconCls:'icon-reload'">刷新</a></td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</div>
	<table id="document_publishList_grid"></table>
	<script type="text/javascript">
	var filtersubmit = function(){
		$('#document_publishList_grid').datagrid('load',sy.serializeObject($('#document_publishList_searchForm')));
	}
	</script>
</body>
</html>