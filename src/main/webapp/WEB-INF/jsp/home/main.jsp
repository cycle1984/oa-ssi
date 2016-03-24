<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main内容显示区域</title>
</head>
<body>
	<script type="text/javascript">
	    function addTab(opts){
	    
	    	var t = $('#home_main_tabs');
	    	if(t.tabs('exists',opts.title)){
	    		t.tabs('select',opts.title);
	    	}else{
	    		t.tabs('add',opts);
	    	}
	    	
	    }
	    
	</script>
	<div id="home_main_tabs" class="easyui-tabs" data-options="fit:true,plain:false">
		<div title="Tab1" style="padding:20px;">   
        tab1   
    	</div> 
	</div>
</body>
</html>