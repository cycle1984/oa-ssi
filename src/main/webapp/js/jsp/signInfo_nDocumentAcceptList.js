var refleshtime =1000*60*10 ;//定时的时间
var ref;//定时器
/**
 * 定时刷新
 */
var rf = function (){
	$('#signInfo_nDocumentAcceptList_grid').datagrid('load');
};
var setreflesh = function(rf,refleshtime){
	ref = setTimeout(rf,refleshtime);//定时（refleshtime），执行一次rf方法
};
//var setreflesh = function(){
//	ref = setInterval(rf,refleshtime);//定时（refleshtime），执行一次rf方法 
//};

/**
 * 确定签收
 */
var signInfo_nDocumentAcceptList_acceptDialog_submit = function(rowData){
	if($('#signInfo_nDocumentAcceptList_acceptDialog_form').form('validate')){
		$('#signInfo_nDocumentAcceptList_accept_OKbnt').linkbutton('disable');
		$.post(contextPath+'/signInfo/signDocument.do',{
			pwd:$('#accept_pwd').val(),
			id:rowData.id
		},function(r){
			if(r.success){
				$('#signInfo_nDocumentAcceptList_grid').datagrid('load');//重新加载待办公文列表
				$('#signInfo_yDocumentAcceptList_grid').datagrid('load');//重新加载已办公文列表
				$.messager.show({
					title : '提示',
					msg : r.msg
				});
				$('#signInfo_nDocumentAcceptList_acceptDialog').show().dialog('close');
				downDocumentDialog(rowData);
			}else{
				$.messager.alert('错误提示',r.msg,'error',function(){
					$('#accept_pwd').textbox('clear').textbox('textbox').focus();
					$('#signInfo_nDocumentAcceptList_accept_OKbnt').linkbutton('enable');
				});
			}
		},'json');
		
	}
}

/**
 * 签收所有
 */
var signAll = function(){
	$('#signInfo_nDocumentAcceptList_acceptDialog').show().dialog({
		title:'公文签收',
		width:600,
		height:160,
		modal:true,
		top:40,
		buttons:[{
			text:'确定签收',
			id:'signInfo_documentAcceptList_accept_signAll_OKbnt',
			iconCls:'icon-ok',
			handler:function(){
				
				$.post(contextPath+"/signInfo/signAllDocument.do",{
					pwd:$('#accept_pwd').val()
				},function(result){
					if(result.success){
						$('#signInfo_nDocumentAcceptList_grid').datagrid('load');//重新加载待办公文列表
						$('#signInfo_yDocumentAcceptList_grid').datagrid('load');//重新加载已办公文列表
						$.messager.show({
							title : '提示',
							msg : result.msg
						});
						$('#signInfo_nDocumentAcceptList_acceptDialog').dialog('close');
					}else{
						$.messager.alert('提示', result.msg, 'error');
					}
				},"json");
				
			}
		},{
			text:'关闭',
			handler:function(){
				$('#signInfo_nDocumentAcceptList_acceptDialog').hide().dialog('close');
			}
		}],
		onOpen : function() {
			$('form :input').keyup(function(event) {
				if (event.keyCode == 13) {//按下键盘上的enter执行
					
					$.post(contextPath+"/signInfo/signAllDocument.do",{
						pwd:$('#accept_pwd').val()
					},function(result){
						if(result.success){
							$('#signInfo_nDocumentAcceptList_grid').datagrid('load');//重新加载待办公文列表
							$('#signInfo_yDocumentAcceptList_grid').datagrid('load');//重新加载已办公文列表
							$.messager.show({
								title : '提示',
								msg : result.msg
							});
							$('#signInfo_nDocumentAcceptList_acceptDialog').dialog('close');
						}else{
							$.messager.alert('提示', result.msg, 'error');
						}
					},"json");
					
				}
			});
		}
	
	});
	$('#accept_pwd').textbox('clear').textbox('textbox').focus();
	
	
}

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
	
	$('#signInfo_nDocumentAcceptList_grid').datagrid({
		idField:'id',//指定标识字段
		url:contextPath+'/signInfo/receiveListGrid.do?history=false&state=false',//URL从远程站点请求数据
//		queryParams:{//自定义的参数,在请求远程数据的时候发送额外的参数, 
//			history:false,//查询的是否历史公文,
//			state:false//是否已签收
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
		},{
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
		}, {
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
					return '签收人:'+value;
				}else{
					return '<span style="color:red;">未签收</span>';
				}
			}
		}]],
		toolbar : '#signInfo_nDocumentAcceptList_toolbar',
		onLoadSuccess:function(data){//数据加载成功后执行的代码,检测是否有未签收的公文，有则弹出提示窗，循环播放提示音
			
			var num=0;
			for(var i=0;i<data.rows.length;i++){//遍历当前页数据，
				if(!data.rows[i].state){//签收状态，false进入
					num++;
				}
			}
			if(ref!=undefined){
				clearTimeout(ref);
			}
			
			//clearInterval(ref);//销毁前一个定时器，防止重复
			if(num>0){
				//获取当前网址，如： http://localhost:8083/uimcardprj/share/meun.jsp  
			    var curWwwPath=window.document.location.href;  
			    //获取主机地址之后的目录，如： uimcardprj/share/meun.jsp  
			    var pathName=window.document.location.pathname;  
			    var pos=curWwwPath.indexOf(pathName);  
			    //获取主机地址，如： http://localhost:8083  
			    var localhostPaht=curWwwPath.substring(0,pos);  
			    //获取带"/"的项目名，如：/uimcardprj  
			    var projectName=pathName.substring(0,pathName.substr(1).indexOf('/')+1);  
				$.messager.show({
					title:'提示信息',
					msg:'您有<span style="color:red">'+data.total+'</span>份新文件未签收请您及时办理!'
						+'<object height="0" width="0" data="'+localhostPaht+projectName+'/style/Alarm.mp3"></object></div>',
					timeout:5000,
					showType:'slide'
				});
				refleshtime = 1000*60*5;//文件都未签收的情况5分钟刷新一次
				setreflesh(rf,refleshtime);
			}else{//文件都签收的情况
				var i = $('#signInfo_nDocumentAcceptList_refleshtime').val();
				refleshtime=1000*60*10;//默认10分钟刷新一次
				if(i!=undefined&&i!=""){
					refleshtime=1000*60*i;
				}
				setreflesh(rf,refleshtime);
			}
		},
		onClickRow:function(index, row){//点击数据行的时候执行
			if(row.state){
				downDocumentDialog(row);
			}else{
				
				$('#signInfo_nDocumentAcceptList_acceptDialog').show().dialog({
					title:'公文签收',
					width:600,
					height:160,
					modal:true,
					top:40,
					buttons:[{
						text:'确定签收',
						id:'signInfo_nDocumentAcceptList_accept_OKbnt',
						iconCls:'icon-ok',
						handler:function(){
							signInfo_nDocumentAcceptList_acceptDialog_submit(row);
						}
					},{
						text:'关闭',
						handler:function(){
							$('#signInfo_nDocumentAcceptList_acceptDialog').hide().dialog('close');
						}
					}],
					onOpen : function() {
		    			$('form :input').keyup(function(event) {
		    				if (event.keyCode == 13) {//按下键盘上的enter执行
		    					signInfo_nDocumentAcceptList_acceptDialog_submit(row);
		    				}
		    			});
		    		}
				
				});
				$('#accept_pwd').textbox('clear').textbox('textbox').focus();
				$('#cyDocument_cyDocumentReceiveList_receiveDialog_id').val(row.id);//将公文ID赋值到隐藏的id表单
			}
		},
		onLoadError:function(){
			 
		},
	});
	
	$('#signInfo_nDocumentAcceptList_searchForm_unit_td').on('click', function(){
		$('#signInfo_nDocumentAcceptList_searchForm_unit').textbox('clear');
		dialog = sy.modalDialog({
			title:'选择单位查询',
			width : 350,
			top:'10%',
			href:contextPath+'/unit/goURL/unit/searchByUnit.do',
			buttons : [ {
				id:'signInfo_nDocumentAcceptList_searchForm_OKbtn',
				text : '确定',
				iconCls:'icon-ok',
				handler : function() {
					$('#signInfo_nDocumentAcceptList_searchForm_unit').textbox('setText',$('#unit_searchByUnit_unit').val());
					$('#signInfo_nDocumentAcceptList_searchForm_unit').textbox('setValue',$('#unit_searchByUnit_unit').val());
					dialog.dialog('close');
				}
			} ]
		});
	});
	
});
