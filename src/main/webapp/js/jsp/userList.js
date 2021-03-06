var userGrid = $('#user_list_grid');
$(function(){
	userGrid.datagrid({
		idField:'id',//指定标识字段
		url:contextPath+'/user/grid.do',//URL从远程站点请求数据
		fit:true,//当设置为true的时候面板大小将自适应父容器
		fitColumns:true,//适应网格的宽度，防止水平滚动
		striped : true,//是否显示斑马线
		rownumbers : true,//显示一个行号列
		pagination : true,//DataGrid控件底部显示分页工具栏
		singleSelect : false,//如果为true，则只允许选择一行
		border:false,//是否显示面板边框
//		sortName : 'role.id',
//		sortOrder : 'desc',
		pageSize : 20,//每页显示记录数
		pageList : [10, 20, 30, 40, 50, 100, 500],//在设置分页属性的时候 初始化页面大小选择列表
		columns:[[{
			field : 'id',
			title : '主键',
			width : 100,
			checkbox : true
		}, {
			field : 'loginName',
			title : '登录名',
			width : 100,
			sortable : true
		}, {
			field : 'name',
			title : '姓名',
			width : 100,
			sortable : true
		}, {
			field : 'gender',
			title : '性别',
			width : 35 	,
			align:'center',
			sortable : true
		}, {
			field : 'tel',
			title : '办公室电话',
			width : 100,
			sortable : true
		},  {
			field : 'phone',
			title : '手机号码',
			width : 100,
			sortable : true
		}, {
			field : 'dep',
			title : '部门',
			width : 100,
			sortable : true
		}, {
			field : 'unit.id',
			title : '所属单位',
			width : 100,
			sortable : true,
			formatter: function(value,row,index){//value：字段值,row：行记录数据,index: 行索引
				if(row.unit){
					return row.unit.name;
				}else {
					return value;
				}
			}
		}, {
			field : 'unit.myGroup.name',//排序时向后台发送的参数
			title : '所属机构',
			width : 100,
			sortable : true,
			formatter: function(value,row,index){//value：字段值,row：行记录数据,index: 行索引
				if(row.unit){
					if(row.unit.myGroup){
						return row.unit.myGroup.name;
					}
				}else {
					return value;
				}
			}
		}, {
			field : 'createDatetime',
			title : '创建时间',
			width : 150,
			align : 'center',
			sortable : true,
			formatter: function(value,row,index){
				if (value){
					return getFormatDate(new Date(value));
				} else {
					return value;
				}
			}
		},  {
			field : 'role.id',
			title : '权限级别',
			width : 100,
			sortable : true,
			formatter: function(value,row,index){
				if (row.role){
					return row.role.name;
				} else {
					return value;
				}
			}
		},	{
			field : 'remark',
			title : '备注',
			width : 100
		}]],
		rowStyler:function(index,row){
			if(row.role){
				if(row.role.name=="待审核用户")
				return 'color:green;';
			}
		},
		toolbar:'#user_list_toolbar'//工具面板
	});
	
	
	//按单位查询输入框点击事件
	$('#user_list_toolbar_form_unit_td').on('click', function(){
		$('#user_list_toolbar_form_unit').textbox('clear');
		dialog = sy.modalDialog({
			title:'选择单位查询',
			width : 350,
			top:'10%',
			href:contextPath+'/unit/goURL/unit/searchByUnit.do',
			buttons : [ {
				id:'document_searchByUnit_OKbtn',
				text : '确定',
				iconCls:'icon-ok',
				handler : function() {
					$('#user_list_toolbar_form_unit').textbox('setText',$('#unit_searchByUnit_unit').val());
					$('#user_list_toolbar_form_unit').textbox('setValue',$('#unit_searchByUnit_unit').val());
					dialog.dialog('close');
				}
			} ]
		});
	});
	
});

/**
 * 添加用户
 */
var addFunUser = function(){
	var dialog = sy.modalDialog({//创建一个模式化的dialog
		title:'添加用户',
		width : 640,//dialog宽度
		top:'10%',//dialog离页面顶部的距离
		href:contextPath+'/user/goURL/user/saveUI.do',//从URL读取远程数据并且显示到面板。注意：内容将不会被载入，直到面板打开或扩大，在创建延迟加载面板时是非常有用的
		buttons: [ {
			id:'userSaveUI_OKbtn',
			text : '确定',
			iconCls:'icon-ok',
			handler : function() {
				user_saveUI_submitForm(dialog,userGrid);
			}
		} ],
		onLoad:function(){
			$("#user_saveUI_loginName").textbox('textbox').focus();
		}
	});
};

/**
 * 编辑用户
 */
var editFunUser = function(){
	var arr = userGrid.datagrid('getSelections');//返回所有被选中的行，当没有记录被选中的时候将返回一个空数组
	if (arr.length != 1) {
		$.messager.show({
			title : '提示信息',
			msg : '请您选择记录，一次只能选择一条记录进行修改！'
		});
	}else{
		var dialog = sy.modalDialog({
			title:'编辑用户信息',
			width : 640,
			top:'10%',
			href:contextPath+'/user/goURL/user/saveUI.do?id='+arr[0].id,
			buttons : [ {
				id:'userSaveUI_OKbtn',
				text : '确定',
				iconCls:'icon-ok',
				handler : function() {
					user_saveUI_submitForm(dialog,userGrid);//定义在saveUI.jsp
				}
			} ],
			onLoad:function(){
				user_saveUI_editForm(arr[0]);//回显数据
			},
			onBeforeClose:function(){//关闭窗口后，取消勾选
				userGrid.datagrid('uncheckAll');
			}
			
		});
	}
};

/**
 * 批量删除
 */
var deleteFunUser = function(){
	var rows = userGrid.datagrid('getChecked');//在复选框被选中的时候返回所有行
	var ids =new Array();
	if (rows.length > 0) {
		$.messager.confirm('提示信息', '即将删除' + rows.length + '条数据,确认删除？',function(r) {
			if(r){//点击确认进入
				//给数组ids赋值
				for (var i = 0; i < rows.length; i++) {
					ids[i]= rows[i].id;
				}
				$.ajax({
					url : contextPath+'/user/delete.do',
					data : {
						ids : ids
					},
					dataType : 'json',
					success : function(r) {
						userGrid.datagrid('uncheckAll');//取消勾选当前页中的所有行
						userGrid.datagrid('load');
						$.messager.show({
							title : '提示',
							msg : r.msg
						});
					}
				});
			}
		});
	}else{
		$.messager.show({
			title : '提示',
			msg : "请选择要删除的数据"
		});
	}
};

/**
 *重置密码
 */
var resetPwdFun = function(){
	var rows = userGrid.datagrid("getChecked");//获取已经选择的用户数据
	var ids =new Array();
	if(rows.length>0){
		$.messager.confirm('提示信息', '即将重置' + rows.length + '条密码,确认重置？',
		function(r){
			if(r){
				for (var i = 0; i < rows.length; i++) {
					ids[i]= rows[i].id;
				}
				$.post(contextPath+'/user/initPassword.do',{ids : ids},function(r){
					if(r.success){
						userGrid.datagrid('reload');
						userGrid.datagrid('uncheckAll');
						$.messager.show({
							title : '提示',
							msg : r.msg
						});
					}else{
	    				$.messager.alert('提示', r.msg,'error');
	    			}
				},'json');
			}
		});
	}else {
		$.messager.show({
			title : '提示',
			msg : "请选择要重置的数据"
		});
	}
};

/**
 * 批量审核
 */
var checkUserFun = function(){
	var rows = userGrid.datagrid("getChecked");//获取已经选择的用户数据
	var ids =new Array();
	if(rows.length>0){
		for (var i = 0; i < rows.length; i++) {
			ids[i]= rows[i].id;
		}
		var dialog = sy.modalDialog({//创建一个模式化的dialog
			title:'批量审批',
			width : 200,//dialog宽度
//			top:'10%',//dialog离页面顶部的距离
			href:contextPath+'/user/goURL/user/setRoleJsp.do',//从URL读取远程数据并且显示到面板。注意：内容将不会被载入，直到面板打开或扩大，在创建延迟加载面板时是非常有用的
			buttons: [ {
				id:'user_setRole_OKbtn',
				text : '确定',
				iconCls:'icon-ok',
				handler : function() {
					user_setRole_submitForm(dialog,userGrid,ids);
				}
			} ]
		});
	}else {
		$.messager.show({
			title : '提示',
			msg : "请选择要审核的数据"
		});
	}
}