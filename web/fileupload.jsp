<%-- 
    Document   : fileupload
    Created on : 2016/07/26, 16:55:49
    Author     : j-knakagami2
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
    <form action="UserAdd2" method="post" enctype="multipart/form-data">
	<p>ユーザID：<input type="text" name="user_id">
	<p>ユーザ名：<input type="text" name="user_name">
        <p>ニックネーム：<input type="text" name="user_sub_name">
	<p>パスワード：<input type="password" name="user_pass">
        <p>顔写真：<input type="file" name="photo">
	<p><input type="submit" value="ユーザ登録">
    </form>
    </body>
</html>
