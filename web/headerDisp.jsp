<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String errCode=null,errMessage = null;
	if(session.getAttribute("s_headerMsg")!= null){
		errMessage = session.getAttribute("s_headerMsg").toString();
                session.removeAttribute("s_headerMsg");
        }
	else errMessage = "";
	if(session.getAttribute("s_headerErrCode") !=null){
            errCode = session.getAttribute("s_headerErrCode").toString();
            session.removeAttribute("s_headerErrCode");
        }
     	else errCode="";
%>

エラーコード:<%=errCode %>
<p><%=errMessage %>