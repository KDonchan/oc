/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author taiken
 */
public class UserAdd extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession();
        session.removeAttribute("headerMessage");
        session.removeAttribute("s_sectionPage");
        DiskFileItemFactory factory = new DiskFileItemFactory();
        ServletFileUpload sfu = new ServletFileUpload(factory);
        ServletContext application = getServletContext();
        String wServer = application.getInitParameter("a_sqlServer");
        String wDatabase = application.getInitParameter("a_database");
        String wuser_id=null,wuser_name=null,wuser_pass=null,wuser_sub_name=null;
        byte[] wuser_photo=null;
        try{
        Connection wCon = sql.SqlMain.MakeConnection(wServer,wDatabase);
        if(wCon != null){
            try {
                List list = sfu.parseRequest(request);
                Iterator iterator = list.iterator();
                while(iterator.hasNext()){
                    FileItem item = (FileItem)iterator.next();
                    if(item.isFormField()){
                        if(item.getFieldName().equals("user_id")) wuser_id = new String(item.get(), "UTF-8");
                        if(item.getFieldName().equals("user_name")) wuser_name =  new String(item.get(), "UTF-8");
                        if(item.getFieldName().equals("user_pass")) wuser_pass =  new String(item.get(), "UTF-8");
                        if(item.getFieldName().equals("user_sub_name")) wuser_sub_name =  new String(item.get(), "UTF-8");
                    }
                    else{
                        wuser_photo = item.get();
                        if(wuser_photo.length ==0)wuser_photo=null;
                        System.out.println(item.getFieldName() );
                    }
                }
                //2016-06-09 String wsql = "insert into user_tbl values(?,?,?)";
                String wsql = "insert into oc_user(user_id,user_name,user_pass,user_sub_name,user_photo,card_flg) values(?,?,?,?,?,0)";
                if(wuser_photo==null)
                    wsql = "insert into oc_user(user_id,user_name,user_pass,user_sub_name,card_flg) values(?,?,?,?,0)";
                PreparedStatement st = wCon.prepareStatement(wsql);
                //ここから
                st.setString(1, wuser_id);
                st.setString(2, wuser_name);
                st.setString(3, wuser_pass);
                st.setString(4, wuser_sub_name);
                if(wuser_photo!=null)
                    st.setBytes(5, wuser_photo);
                if(wuser_id !=null && ! wuser_id.isEmpty())
                {
                    st.executeUpdate();
                    session.setAttribute("s_headerMsg", "ユーザ登録完了しました。");
                    session.setAttribute("s_user_id", wuser_id);
                    session.setAttribute("s_sectionPage", "login.jsp");
                } 
                else
                {
                    session.setAttribute("s_headerMsg", "ユーザ登録できませんでした。<br>ユーザIDは必ず入力してください。");
                    session.setAttribute("s_sectionPage", "userAdmin.jsp");
                }
                //ここまでソースを追加
            }
            catch (SQLException e) {
                session.setAttribute("s_headerMsg", e.getMessage());
                session.setAttribute("s_headerErrCode",e.getErrorCode());
            } catch (FileUploadException ex) {
                Logger.getLogger(UserAdd.class.getName()).log(Level.SEVERE, null, ex);
            }
            }
        }catch(SQLServerException ex){
            session.setAttribute("s_headerMsg", ex.getMessage());
            session.setAttribute("s_headerErrCode", ex.getErrorCode());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("Menu");
        dispatcher.forward(request, response);
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
