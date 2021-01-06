<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
	function checkUsername() {
		var username = document.getElementsByName("username")[0].value;
		var span01 = document.getElementsByTagName("span")[0];
		
		if(username == "") {
			//alert("用户名不能为空！");
			span01.innerHTML = "用户名不能为空！";
			return;
		}
		
		//创建请求对象
		var xmlHttp = new XMLHttpRequest();
		
		//设置事件
		xmlHttp.onreadystatechange = function() {
			if(xmlHttp.readyState == 4) {
				if(xmlHttp.status == 200) {
					//alert(xmlHttp.responseText);
					var data = xmlHttp.responseText;
					if(data == 0) {
						//不重复，可以使用
						span01.innerHTML = "用户名可以使用！";
					}
					else {
						//重复不允许使用
						span01.innerHTML = "用户名已经被注册！";
					}
				}
			}
		}
		
		//创建连接
		xmlHttp.open("GET","/myAJAX/RegisterAJAXServlet?username=" + username,true);
		
		//发送请求
		xmlHttp.send();
		
	}
</script>
</head>
<body>
	<h1>注册页面</h1>
	<form action="${pageContext.request.contextPath}/RegisterServlet" method="post">
		用户名：<input type="text" name="username" onblur="checkUsername();"/><span></span>
		<br />
		密码：<input type="password" name="password" />
		<br />
		<input type="submit" value="注册" />
	</form>
</body>
</html>