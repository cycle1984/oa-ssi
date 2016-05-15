<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>公文接收列表</title>
</head>
<body>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jsp/signInfo_historyDocumentAcceptList.js"></script>
	<!-- 菜单栏 -->
	<div id="signInfo_historyDocumentAcceptList_toolbar" style="display: none;">
		 <table>
			<tr>
				<td><form id="signInfo_historyDocumentAcceptList_searchForm" style="margin:0px;"><!--style="margin:0px;"可以处理加form表单后，tr间隙变大的问题  -->
						<table style="font-size: 13px;">
							<tr>
								<td>公文标题(可模糊查询)</td>
								<td><input name="document.documentTitle" style="width: 120px; " class="easyui-textbox"/></td>
								<td>发文单位</td>
								<td id="signInfo_historyDocumentAcceptList_searchForm_unit_id"><select id="signInfo_historyDocumentAcceptList_searchForm_unit"  name="document.publishUnit.name" class="easyui-textbox" style="width: 80px" ></select></td>
								<td>发布时间</td>
								<td><input type="text" name="createDatetime_start" class="easyui-datebox" data-options="showSeconds:false,editable:false" style="width: 120px;"/>-<input name="createDatetime_end" type="text" class="easyui-datebox"  data-options="showSeconds:false,editable:false" style="width: 120px;"/></td>
								<td><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom',plain:true" onclick="$('#signInfo_historyDocumentAcceptList_grid').datagrid('load',sy.serializeObject($('#signInfo_historyDocumentAcceptList_searchForm')));">过滤</a><a href="javascript:void(0);" class="easyui-linkbutton" data-options="iconCls:'ext-icon-zoom_out',plain:true" onclick="$('#signInfo_historyDocumentAcceptList_searchForm input').val('');$('#signInfo_historyDocumentAcceptList_grid').datagrid('load',{});">重置过滤</a></td>
							</tr>
						</table>
					</form></td>
			</tr>
		</table>
	</div>
	<table id="signInfo_historyDocumentAcceptList_grid"></table>
	
</body>
</html>