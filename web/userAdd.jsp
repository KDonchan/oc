<%-- 
    Document   : userAdd
    Created on : 2016/07/26, 15:16:20
    Author     : j-knakagami2
--%>

<%@page import="beans.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    boolean modFlg=false;
    if(session.getAttribute("s_loginUserBean")!=null)
        modFlg=( (UserBean)session.getAttribute("s_loginUserBean")).isLogin_flg();
    String submitText="ユーザ登録",actionPage="UserAdd";
    if(request.getParameter("addFlg")!=null){
        if(request.getParameter("addFlg").equals("0") &&
                modFlg ){
             submitText="更新";
             actionPage="UserMod";
        }
    }
    %>
<form action="<%=actionPage%>" method="post" enctype="multipart/form-data">
	<p>ユーザID：<input type="text" name="user_id">
	<p>ユーザ名：<input type="text" name="user_name">
        <p>ニックネーム：<input type="text" name="user_sub_name">
	<p>パスワード：<input type="password" name="user_pass">
            <%
                if(modFlg){
                    UserBean wUserBean = (UserBean)session.getAttribute("s_loginUserBean");
                    session.setAttribute("s_photo", wUserBean.getUser_photo());
                    %>
        <p><img src='JpegDisplay2' width='100'></p>
                    <%
                }
                %>
        <p>顔写真：<input type="file" name="photo">
	<p><input type="submit" value="<%=submitText%>">
</form>