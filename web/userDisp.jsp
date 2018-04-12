<%-- 
    Document   : userDisp
    Created on : 2016/06/09, 10:13:15
    Author     : oc_system
--%>

<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="sql.SqlMain"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    boolean findFlg=false;
request.setCharacterEncoding("UTF-8");
	response.setContentType("text/html; charset=UTF-8");

	session.removeAttribute("s_loginFlg");
        String wServer = application.getInitParameter("a_sqlServer");
	String wDatabase = application.getInitParameter("a_database");
        String wUser_id = request.getParameter("user_id");
        String wUser_pass = request.getParameter("user_password");
        String nextPage="index.jsp";
	out.println("Server="+wServer + "  Database="+wDatabase);
	Connection con = SqlMain.MakeConnection(wServer,wDatabase);
        if(con != null){		
            try{
                //体験授業修正箇所
                String wsql = "select * from oc_user where user_id=? and user_pass=?";
                //体験授業修正箇所
                
		PreparedStatement st = con.prepareStatement(wsql);
                System.out.println("SQL →" + wsql);
                System.out.println("ID=" + wUser_id);
                st.setString(1, wUser_id);
                st.setString(2, wUser_pass);
		ResultSet rs = st.executeQuery();
		if(rs.next()){
                    findFlg=true;
                    session.setAttribute("s_loginFlg", findFlg);
                    byte[] images = rs.getBytes("user_photo");
                    session.setAttribute("jpeg_img", images);
                    String wHTML="<table border='1'><tr><th width='50'>選択</th><th width='200'>ユーザId</th><th width='300'>名前</th>"
                                + "<th width='150'>ﾆｯｸﾈｰﾑ</th><th width='100'>写真</th></tr>";
                    String id = rs.getString("user_id");
                    out.println(wHTML);
                    out.println("<tr>");
                    out.println("<td><input type='checkbox' name='" + rs.getString("user_id")+"'></th>");
                    out.println("<td>" +id+"</td>");
                    out.println("<td>" + rs.getString("user_name")+"</td>");
                    out.println("<td>" + rs.getString("user_sub_name")+"</td>");
                    out.println("<td><img src=JpegDisplay?id=" + id +" width='100'></td></tr>");
                    out.println("</table>");                        
		}
                else
                {
                    System.out.println("ログインできませんでした");
                    session.setAttribute("s_sectionPage", "login.jsp");
                    session.setAttribute("s_headerMsg", "ユーザIDかパスワードが違います");
                    RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
                    dispatcher.forward(request, response);
                }
		}catch(SQLException errs){
			System.out.println("会員表示エラー：" + errs.getMessage());
		}    
        }
%>

