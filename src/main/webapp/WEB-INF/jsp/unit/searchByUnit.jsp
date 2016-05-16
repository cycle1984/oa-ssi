<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>选择单位查询页面</title>
</head>
<body>
	<input id="unit_searchByUnit_unit" class="easyui-textbox" data-options="prompt:'可直接输入'" style="width: 100%">
	<div class="easyui-tabs" style="height:400px">
		<div title="群组列表" style="padding:10px">
			<ul id="document_searchByUnit_tree">
			</ul>
		</div>
		<div title="热度排序" >
			<table id="unit_searchByUnit_table"></table>
		</div>
		<!-- <div title="按热度排序"  style="padding:10px">
			开发中。。。
		</div> -->
	</div>
	
	<script type="text/javascript">
		$(function(){
			$('#document_searchByUnit_tree').tree({
				url : '${pageContext.request.contextPath}/unit/findMyGroupAndUnitTree.do',
				parentField : 'pid',
				onClick:function(node){//因为onCheck和onClick捆绑在一起，所以2个需要一样的设置
					
			    	//var node = $('#document_searchByUnit_tree').tree('getSelected');//获得选择的节点
			    	var text = '';//文本框内容
		    		var isLeaf = $('#document_searchByUnit_tree').tree('isLeaf',node.target);
					if(isLeaf){
						text=node.text;
					}else{
						$(this).tree(node.state === 'closed' ? 'expand' : 'collapse', node.target);  
				        node.state = node.state === 'closed' ? 'open' : 'closed';
					}
			    	$('#unit_searchByUnit_unit').textbox('setText',text).textbox('setValue',text);
			    },
			    onSelect:function(node){
			    	var isLeaf = $('#document_searchByUnit_tree').tree('isLeaf',node.target);
					if(isLeaf){
					}else{
						$(this).tree(node.state === 'closed' ? 'expand' : 'collapse', node.target);  
				        node.state = node.state === 'closed' ? 'open' : 'closed';
					}
			    },
			    onLoadSuccess:function(node, data){
			    	addTreeTitle(data);
			    }
			});
		});
		//给菜单增加title属性，用于提示单位全称
		function addTreeTitle(row){
		    $.each(row,function(idx,val){
		    	//attributes不为空，则需要加title属性
		    	if(val.attributes!=null){
		    		var domId = val.domId;
		    		$("#"+domId).attr("title",val.attributes.title);
		    	}
		        if(val.children){
		            addTreeTitle(val.children);//如果还有子节点则继续遍历
		        }
		    });
		}
	</script>
	
</body>
</html>