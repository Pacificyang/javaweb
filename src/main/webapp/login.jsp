<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
	<h1>登陆页面</h1>
	<form action="${pageContext.request.contextPath}/login" method="post">
		用户名：<input type="text" name="username" value="${user.username}" /><span>${error}</span>
		<br />
		密码：<input type="password" name="password" />
		<br />
		<input type="submit" value="登陆" />
	</form>
	<a href="register.jsp">注册</a>
</body>
</html>