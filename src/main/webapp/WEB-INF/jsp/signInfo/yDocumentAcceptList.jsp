<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>已办公文列表</title>
</head>
<body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jsp/signInfo_yDocumentAcceptList.js"></script>
	
	<!-- 公文全部签收情况下，刷新的频率 -->
	<input type="hidden" id="signInfo_yDocumentAcceptList_refleshtime" value="${session.config.refleshTime }">
	
	<!-- 菜单栏 -->
	<div id="signInfo_yDocumentAcceptList_toolbar" style="display: none;">
		 <table>
			<tr>
				<td><form id="signInfo_yDocumentAcceptList_searchForm" style="margin:0px;"><!--style="margin:0px;"可以处理加form表单后，tr间隙变大的问题  -->
						<table style="font-size: 13px;">
							<tr>
								<td>公文标题(可模糊查询)</td>
								<td><input name="document.documentTitle" style="width: 120px; " class="easyui-textbox"/></td>
								<td>发文单位</td>
								<td id="signInfo_yDocumentAcceptList_searchForm_unit_td"><input id="signInfo_yDocumentAcceptList_searchForm_unit" name="document.publishUnit.name" style="width: 80px" class="easyui-textbox"></input></td>
								<td>发布时间</td>
								<td><input type="text" name="createDatetime_start" class="easyui-datebox" data-options="showSeconds:false,editable:false" style="width: 120px;"/>-<input name="createDatetime_end" type="text" class="easyui-datebox"  data-options="showSeconds:false,editable:false" style="width: 120px;"/></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="$('#signInfo_yDocumentAcceptList_grid').datagrid('load',sy.serializeObject($('#signInfo_yDocumentAcceptList_searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#signInfo_yDocumentAcceptList_searchForm input').val('');$('#signInfo_yDocumentAcceptList_grid').datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form></td>
			</tr>
		</table>
	</div>
	<table id="signInfo_yDocumentAcceptList_grid"></table>
	
</body>
</html>