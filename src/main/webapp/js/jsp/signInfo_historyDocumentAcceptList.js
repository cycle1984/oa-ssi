
/**
 * 公文详情(下载)窗口
 */
var downDocumentDialog = function(row){
	var dialog = sy.modalDialog({
		title:'公文详情',
		href:contextPath+'/signInfo/getByID.do?id='+row.id,
		width:600,
		top:40
	});
}

$(function(){
	
	$('#signInfo_historyDocumentAcceptList_grid').datagrid({
		idField:'id',//指定标识字段
		url:contextPath+'/signInfo/receiveListGrid.do?history=true',//URL从远程站点请求数据
//		queryParams:{//自定义的参数,在请求远程数据的时候发送额外的参数, 
//			history:true//查询的是否历史公文,
//		},
		fit:true,//当设置为true的时候面板大小将自适应父容器
		fitColumns:true,//适应网格的宽度，防止水平滚动
		striped : true,//是否显示斑马线
		rownumbers : true,//显示一个行号列
		pagination : true,//DataGrid控件底部显示分页工具栏
		singleSelect : true,//如果为true，则只允许选择一行
		border:false,//是否显示面板边框
		pageSize : 20,//每页显示记录数
		sortName : 'document.createDatetime',
		sortOrder : 'desc',
		pageList : [10, 20, 30, 40, 50, 100, 500],//在设置分页属性的时候 初始化页面大小选择列表
		rowStyler:function(index,row){
			if(row.document){
				if(row.document.level=="特提"&&row.signUserName!="本单位发布"){
					return 'color:red;';
				}
				if(row.signUserName=="本单位发布"){
					return 'color:green;';
				}
			}
			
		},
		columns:[[{
			field : 'document.createDatetime',
			title : '发布时间',
			width : 80,
			sortable : true,
			formatter:function(value,row,index){
				if(row.document){
					return getFormatDate(new Date(row.document.createDatetime));
				}else {
					return value;
				}

			}
		},{
			field : 'document.docNum',
			title : '发文字号',
			width : 80,
			sortable : true,
			formatter:function(value,row,index){
				if(row.document){
					return row.document.docNum;
				}else {
					return value;
				}

			}
		}, {
			field : 'document.level',
			title : '等级',
			width : 50,
			sortable : true,
			formatter:function(value,row,index){
				if(row.document){
					return row.document.level;
				}else {
					return value;
				}

			}
		},{
			field : 'document.documentTitle',
			title : '标题',
			width : 300	,
			align:'left',//公文名称左对齐
			halign:'center',//标题居中对齐
			sortable : true,
			formatter:function(value,row,index){
				if(row.document){
					return row.document.documentTitle;
				}else {
					return value;
				}

			}
		}, {
			field : 'document.publishUnit.name',
			title : '发布单位',
			width : 100,
			sortable : true,
			formatter:function(value,row,index){
				if(row.document){
					if(row.document.publishUnit){
						return row.document.publishUnit.name;
					}
				}else {
					return value;
				}

			}
		}, {
			field : 'document.publishUserName',
			title : '发布人',
			width : 100,
			sortable : true,
			formatter:function(value,row,index){
				if(row.document){
					return row.document.publishUserName;
				}else {
					return value;
				}

			}
		},{
			field : 'signUserName',
			title : '签收情况',
			width : 80,
			formatter:function(value,row,index){
				if(row.signUserName){
					if(row.signUserName=="本单位发布"){
						return value;
					}
					return '签收人:'+value;
				}else{
					return '<span style="color:red;">未签收</span>';
				}
			}
		}]],
		toolbar : '#signInfo_historyDocumentAcceptList_toolbar',
		onClickRow:function(index, row){//点击数据行的时候执行
			if(row.state){
				downDocumentDialog(row);
			}else{
				alert("已经超过签收时限");
			}
		},
		onLoadError:function(){
			 
		},
	});
	//按发文单位查询框点击事件
	$('#signInfo_historyDocumentAcceptList_searchForm_unit_id').on('click', function(){
		dialog = sy.modalDialog({
			title:'选择单位查询',
			width : 640,
			top:'10%',
			href:contextPath+'/unit/goURL/unit/searchByUnit.do',
			buttons : [ {
				id:'historyDocumentAcceptList_searchForm_OKbtn',
				text : '确定',
				iconCls:'icon-ok',
				handler : function() {
					$('#signInfo_historyDocumentAcceptList_searchForm_unit').textbox('setText',$('#unit_searchByUnit_unit').val());
					$('#signInfo_historyDocumentAcceptList_searchForm_unit').textbox('setValue',$('#unit_searchByUnit_unit').val());
					dialog.dialog('close');
				}
			} ]
		});
	});
	
});
