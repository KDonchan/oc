<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="sql.SqlMain,java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<body>
<%
	Connection wConnection = SqlMain.MakConnection("332-server", "it3", "it3", "P@ssword");
	//Connection wConnection = SqlMain.MakeConnection("332-server", "it3");
	if(wConnection !=null){
  		Statement st = wConnection.createStatement();
  		ResultSet rs = st.executeQuery("select * from 社員データ");
  		while(rs.next()){
  			out.print(rs.getString("社員番号")+":" + rs.getString("氏名")+"<br>");
  		}
  	}

%>
</body>
</html>
