<%@ page language="java" contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" isELIgnored="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>菜单</title>
</head>
<body>
	<div>
		<ul id="home_west_tree">
		</ul>
	</div>
	<script type="text/javascript">
			$('#home_west_tree').tree({
				url : '${pageContext.request.contextPath}/sys/findAllMenu.do',
				parentField : 'pid',
				onClick : function(node) {
					if (node.attributes.url) {
						var url = '${pageContext.request.contextPath}/'+node.attributes.url+".do";
						addTab({
							title : node.text,
							href : url,
							closable : true,
							fit:true
						});
					}

				}
			});
	</script>
</body>
</html>