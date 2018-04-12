<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
  request.setCharacterEncoding("UTF-8");
  response.setContentType("text/html; charset=UTF-8");
  String wSectionPage = null;
  String navPage=null;
  if(application.getInitParameter("a_indexSectionPage")!=null) wSectionPage = application.getInitParameter("a_indexSectionPage");
  if(session.getAttribute("s_sectionPage")!=null)  wSectionPage=session.getAttribute("s_sectionPage").toString();  
  if(application.getInitParameter("a_indexNavPage")!=null)
       navPage = application.getInitParameter("a_indexNavPage");
  if(session.getAttribute("s_navPage")!=null)
      navPage = (String)session.getAttribute("s_navPage");
%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/main.css">
<title>広コン　システムエンジニア</title>
</head>
<body>
<!-- ここから  -->
    <header><jsp:include page="headerDisp.jsp"/></header>
    <nav><jsp:include page="<%=navPage%>"/></nav>
    <section><jsp:include page="<%=wSectionPage%>"/></section>
    <hircom2><img src="./img/robo_r[1].png"/></hircom2>

<!-- ここまで -->
</body>
</html>