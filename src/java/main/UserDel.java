/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author taiken
 */
public class UserDel extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        resp.setContentType("text/html;charset=UTF-8");
		HttpSession session = req.getSession();
		String forwardPage = "index.jsp";
		RequestDispatcher dispatcher = req.getRequestDispatcher(forwardPage);
		session.setAttribute("s_header_message", "UserDel  エラーなし");
		session.setAttribute("s_header_errCode", "");
		ServletContext application = getServletContext();
		String wServer = application.getInitParameter("a_sqlServer");
		String wDatabase = application.getInitParameter("a_database");
		try{
                Connection wcon = sql.SqlMain.MakeConnection(wServer, wDatabase);
		if(wcon !=null){
			//2016-06-09 String wsql = "delete from user_tbl where user_id=?";
                        String wsql = "delete from oc_user where user_id=?";
			PreparedStatement stmt=null;
			Enumeration<String> names = req.getParameterNames();
			while(names.hasMoreElements()){
				String wUserName = names.nextElement();
				try {
					stmt = wcon.prepareStatement(wsql);
					//ここから
                                        stmt.setString(1, wUserName);
                                        stmt.executeUpdate();
                                        //ここまでソースを追加
				} catch (SQLException e) {
					System.out.println("User delete Err  user="+wUserName);
					System.out.println(e.getMessage());
					session.setAttribute("s_headerMsg", "UderDelエラー<br>" + e.getMessage());
                                        session.setAttribute("s_headerErrCode", e.getErrorCode());
				}

			}
		}
		else{

		}
                }catch(SQLServerException ex){
                        session.setAttribute("s_headerMsg", ex.getMessage());
                        session.setAttribute("s_headerErrCode", ex.getErrorCode());
                        }
		dispatcher.forward(req, resp);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
