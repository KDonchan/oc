<%-- 
    Document   : loginMenu
    Created on : 2017/03/16, 15:11:38
    Author     : j-knakagami2
--%>

<%@page import="beans.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    boolean flg=false;
    if(session.getAttribute("s_loginUserBean")!=null)
        flg = ((UserBean)session.getAttribute("s_loginUserBean")).isLogin_flg();
    if(flg){
    %>
<ul>
        <li><a href="Menu?r_sectionPage=userAdd.jsp&addFlg=0">ユーザ情報更新</a></li>
        <li><a href="Menu?r_sectionPage=userAdmin.jsp">ユーザ管理ページ</a></li>
         <li><a href="Logout">ログアウト</a></li>
</ul>

<%
}
else{
session.setAttribute("s_sectionPage", "login.jsp");
session.setAttribute("s_headerMsg", "ログインしてください");
session.setAttribute("s_navPage", "mainMenu.jsp");
response.sendRedirect("index.jsp");
}
%>