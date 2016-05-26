<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>信息详情</title>
</head>
<body >

<div style="width:80%;margin:0px auto;">
	<div style="text-align: center;font-family:'微软雅黑';"><h1>${requestScope.news.title }</h1></div>
	<div style="text-align: center;font-family:'微软雅黑';">作者:${requestScope.news.unit.name }&nbsp;${requestScope.news.author }&nbsp;&nbsp;&nbsp; <fmt:formatDate value="${requestScope.news.createTime }" type="both" dateStyle="full"/></div>
	<hr style="border:1px dotted  #0000fff;"/>
	<div id="news_div_content" style="margin: 0 auto;">${requestScope.news.content }</div>
</div>
</body>
</html>