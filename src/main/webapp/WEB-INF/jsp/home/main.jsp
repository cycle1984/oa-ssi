<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
		<!-- 管理员默认显示 -->
		<c:if test="${sessionScope.userSession.loginName=='admin'}">
		 	<div title="发文管理">
		    	<jsp:include page="../document/publishList.jsp"></jsp:include>
			</div>
		</c:if>
		
		<c:if test="${sessionScope.userSession.loginName!='admin'}">
		 	<div title="待办公文列表">
		        <jsp:include page="../signInfo/nDocumentAcceptList.jsp" ></jsp:include>
		    </div>
		    <div title="已办公文列表">
		        <jsp:include page="../signInfo/yDocumentAcceptList.jsp" ></jsp:include>
		    </div>
		</c:if>
		
		
	</div>
</body>
</html>