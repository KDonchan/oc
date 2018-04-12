<%-- 
    Document   : userDisps
    Created on : 2017/03/01, 16:24:19
    Author     : j-knakagami2
--%>

<%@page import="java.io.ByteArrayOutputStream"%>
<%@page import="java.io.OutputStream"%>
<%@page import="javax.imageio.ImageIO"%>
<%@page import="java.awt.image.BufferedImage"%>
<%@page import="java.io.BufferedInputStream"%>
<%@page import="java.io.ByteArrayInputStream"%>
<%@page import="java.io.InputStream"%>
<%@page import="beans.UserBean"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<UserBean> users=null;
    users = UserBean.allUserGet(request);
    if(users !=null){
        for(UserBean user : users){           
%>
            <p>ユーザ名：<%=user.getUser_name()%>
                <img src="JpegDisplay?id=<%=user.getUser_id().trim()%>" width="100"/>
            </p>
            
            <%
        }
    }   
            %>