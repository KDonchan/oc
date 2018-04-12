<%@page import="com.microsoft.sqlserver.jdbc.SQLServerException"%>
<%@page import="sql.SqlMain"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<table border="1">
<tr><th width="50">選択</th>
<th width="200">ユーザId</th>
<th width="300">名前</th>
<th width="100">写真</th>
</tr>
<%
	request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	String wServer = application.getInitParameter("a_sqlServer");
	String wDatabase = application.getInitParameter("a_database");

	out.println("Server="+wServer + "  Database="+wDatabase);
        try{
	Connection con = SqlMain.MakeConnection(wServer,wDatabase);
	if(con != null){
		try{
		Statement st = con.createStatement();
		//2016-06-09 古いソース String wsql = "select * from user_tbl";
                String wsql = "select * from oc_user";
		ResultSet rs = st.executeQuery(wsql);
		while(rs.next()){
                    String id = rs.getString("user_id");
			out.println("<tr>");
			out.println("<td><input type='checkbox' name='" + rs.getString("user_id")+"'></th>");
			out.println("<td>" + rs.getString("user_id")+"</td>");
                        out.println("<td>" + rs.getString("user_name")+"</td>");
                        out.println("<td><img src=JpegDisplay?id=" +id + " width='100'></td></tr>");
                        /*2016-06-09古いソース
                        out.println("<td><input type='checkbox' name='" + rs.getString("user_id")+"'></th>");
                        out.println("<td>" + rs.getString("user_id")+"</td>");
			out.println("<td>" + rs.getString("user_name") +"</td>");
                        */
		}
		}catch(SQLException errs){
			System.out.println(errs.getMessage());
		}

	}
        }catch(SQLServerException ex){
            session.setAttribute("s_headerMsg", ex.getMessage());
            session.setAttribute("s_sectionPage", "login.jsp");
            response.sendRedirect("index.jsp");
        }
%>
</table>