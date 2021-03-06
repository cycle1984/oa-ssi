var publishGrid = $('#document_publishList_grid');

$(function(){
	
	publishGrid.datagrid({
		idField:'id',//指定标识字段
		url:contextPath+'/document/publishGrid.do?history=false',//URL从远程站点请求数据
//		queryParams:{//在请求远程数据的时候发送额外的参数。 
//			history:false//查询的是否历史公文,自定义的参数
//		},
		fit:true,//当设置为true的时候面板大小将自适应父容器
		fitColumns:true,//适应网格的宽度，防止水平滚动
		autoRowHeight:true,//定义设置行的高度，根据该行的内容。设置为false可以提高负载性能。
		nowrap:false,//如果为true，则在同一行中显示数据。设置为true可以提高加载性能。
		striped : true,//是否显示斑马线
		rownumbers : true,//显示一个行号列
		pagination : true,//DataGrid控件底部显示分页工具栏
		singleSelect : false,//如果为true，则只允许选择一行
		border:false,//是否显示面板边框
		pageSize : 20,//每页显示记录数
		checkOnSelect:false,
		selectOnCheck:false,
		pageList : [10, 20, 30, 40, 50, 100, 500],//在设置分页属性的时候 初始化页面大小选择列表
		rowStyler:function(index,row){
			if(row.level=="特提"){
				return 'color:red;';
			}
		},
		sortName : 'createDatetime',
		sortOrder : 'desc',
		columns:[[{
			field : 'id',
			title : '主键',
			checkbox : true
		},{
			field : 'createDatetime',
			title : '发布时间',
			width : 60,
			align:'left',
			sortable : true,
			formatter: function(value,row,index){
				if (value){
					return getFormatDate(new Date(value));
				} else {
					return value;
				}
			}
		},{
			field : 'docNum',
			title : '发文字号',
			width : 80,
			halign:'center',
			sortable : true
		},{
			field : 'level',
			title : '等级',
			width : 20,
			halign:'center',
			sortable : true
		}, {
			field : 'documentTitle',
			title : '标题',
			width : 300	,
			halign:'center',
			sortable : true
		}, {
			field : 'publishUnit',
			title : '发布单位',
			width : 50,
			formatter: function(value,row,index){
				if(value){return value.name;}
			},
			sortable : true
		}, {
			field : 'publishUserName',
			title : '发布人',
			width : 50,
			sortable : true
		}]],
		onClickRow:function(index, row){
			viewSignInfos(row.id,index);
		},
		onLoadError:function(){
			 
		},
		toolbar:'#document_publishList_toolbar'//工具面板
	});
	//按单位查询输入框点击事件
	$('#document_publishList_searchForm_unit_td').on('click', function(){
		$('#document_publishList_searchForm_unit').textbox('clear');
		dialog = sy.modalDialog({
			title:'选择单位查询',
			width : 350,
			top:'10%',
			href:contextPath+'/unit/goURL/unit/searchByUnit.do',
			buttons : [ {
				id:'document_publishList_searchForm_OKbtn',
				text : '确定',
				iconCls:'icon-ok',
				handler : function() {
					$('#document_publishList_searchForm_unit').textbox('setText',$('#unit_searchByUnit_unit').val());
					$('#document_publishList_searchForm_unit').textbox('setValue',$('#unit_searchByUnit_unit').val());
					dialog.dialog('close');
				}
			} ]
		});
	});

});
/**
 * 发布公文
 */
var addFunDocumentPublish = function(){
	var dialog = parent.sy.modalDialog({
		title:'发布公文',
		width : 640,
		top:'10%',
		href:contextPath+'/document/goURL/document/saveUI.do',
		buttons : [ {
			id:'document_saveUI_OKbtn',
			text : '发布',
			iconCls:'icon-ok',
			handler : function() {
				document_saveUI_submit(dialog);//在document/saveUI.jsp页面定义
			}
		} ]
	});
};

/**
 *删除
 */
var delFunDocumentPublish = function(){
	var rows = $('#document_publishList_grid').datagrid('getChecked');//获得已选择的数据
	var ids = new Array();
	if(rows.length>0){
		$.messager.confirm('提示信息', '即将删除' + rows.length + '条数据,确认删除？',function(r){
			if(r){
				$.messager.progress({
					text : '数据删除中....'
				});
				for (var i = 0; i < rows.length; i++) {
					ids[i]= rows[i].id ;
				}
				$.post(contextPath+'/document/delete.do',{ids : ids},function(r){
					if(r.success){
						$('#document_publishList_grid').datagrid('load');
						$('#document_publishList_grid').datagrid('uncheckAll');
						$.messager.show({
							title : '提示',
							msg : r.msg
						});
						$.messager.progress('close');
					}else{
	    				$.messager.alert('提示', r.msg,'error');
	    				$.messager.progress('close');
	    				$('#document_publishList_grid').datagrid('load');
	    			}
				},'json');
				
			}
		});
	}else {
		$.messager.show({
			title : '提示',
			msg : "请选择要删除的记录"
		});
	}
};

/**
 * 查看签收信息列表
 */
var viewSignInfos = function(docId,index){
	var dialog = parent.sy.modalDialog({
		title:'文件签收情况表',
		href:contextPath+'/signInfo/signInfoList.do?docId='+docId,
		width:700,
		height:'70%',
		border:true,
		onClose:function(){
			publishGrid.datagrid('unselectRow',index);
			$(this).dialog('destroy');
		}
	});
};

