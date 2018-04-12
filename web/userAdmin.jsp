<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	session.setAttribute("ctlr", "userAdmin.jsp");
	request.setCharacterEncoding("UTF-8");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ユーザ管理ページ</title>
</head>
<body>
    <form action="UserAdd" method="post" enctype="multipart/form-data">
	<p>ユーザID：<input type="text" name="user_id">
	<p>ユーザ名：<input type="text" name="user_name">
        <p>ニックネーム：<input type="text" name="user_sub_name">
	<p>パスワード：<input type="password" name="user_pass">
        <p>顔写真：<input type="file" name="photo">
	<p><input type="submit" value="ユーザ登録">
    </form>
<p><hr size="3">
<p>

<form action="UserDel" method="post">
        <jsp:include page="userDel.jsp" />
	<p><input type="submit" value="ユーザ削除" >
</form>
</body>
</html>