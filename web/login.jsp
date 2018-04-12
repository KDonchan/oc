<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

    <%
        String wUserID="";
        if(session.getAttribute("s_user_id")!=null){
            wUserID = session.getAttribute("s_user_id").toString();
            session.removeAttribute("s_user_id");
        }
        %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<form  action="LoginCheck" method="post">
	<p>ユーザID：<input type="text" value="<%=wUserID%>" name="user_id">
	<p>パスワード：<input type="password" name="user_pass">
	<p><input type="submit" value="ログイン">
	<p><hr>
        <p><a href="Menu?r_sectionPage=userAdd.jsp&addFlg=1">ユーザ新規登録はこちらから</a></p>
</form>