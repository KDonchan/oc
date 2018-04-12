<%-- 
    Document   : userDisp2
    Created on : 2017/03/01, 15:26:15
    Author     : j-knakagami2
--%>

<%@page import="beans.UserBean"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    if(session.getAttribute("s_loginUserBean")!=null){
        UserBean user = (UserBean)session.getAttribute("s_loginUserBean");
        if(user.isLogin_flg()){
            session.setAttribute("s_photo", user.getUser_photo());
    %>
<table border='1'>
    <tr><th width='50'>選択</th><th width='200'>ユーザId</th><th width='300'>名前</th><th width='150'>ﾆｯｸﾈｰﾑ</th><th width='100'>写真</th></tr>
    <tr><td><input type='checkbox' name='<%=user.getUser_id()%>'></td><td><%=user.getUser_id()%></td>
        <td><%=user.getUser_name()%></td>
        <td><%=user.getUser_sub_name()%></td>
        <td><img src='JpegDisplay2' width='100'></td>
    </tr>
</table> 
<%
    }
else{
    session.setAttribute("s_sectionPage", "login.jsp");
    session.setAttribute("s_headerMsg", "ログインしてください");
    response.sendRedirect("index.jsp");
}
    }
else{
    session.setAttribute("s_sectionPage", "login.jsp");
    session.setAttribute("s_headerMsg", "ログインしてください");
    response.sendRedirect("Menu?s_sectionPage=login.jsp");
}
%>