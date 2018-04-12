/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package image;

import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author taiken
 */
@WebServlet(name = "JpegDisplay", urlPatterns = {"/JpegDisplay"})
public class JpegDisplay extends HttpServlet {

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
        response.setContentType("image/jpeg");
        //ServletOutputStream out = response.getOutputStream();
        try{
        Connection wcon = sql.SqlMain.MakeConnection(request);
        if(wcon !=null){
            try {
                String id= request.getParameter("id");
                Statement st = wcon.createStatement();
                String wsql ="select user_photo from oc_user where user_id ='" + id + "'";
                ResultSet rs = st.executeQuery(wsql);
                rs.next();
                InputStream is = rs.getBinaryStream("user_photo");
                BufferedInputStream bis = new BufferedInputStream(is);
                BufferedImage out = ImageIO.read(bis);
                if(request.getParameter("id") !=null){
                    OutputStream os = response.getOutputStream();
                    ImageIO.write(out, "jpg", os);
                }else{
                    System.out.println("jpeg is null !!");
                }   } catch (SQLException ex) {
                System.out.println("select user_phoot err:" + ex.getMessage());
            } catch (Exception ex2){
                System.out.println("photo Data Err:" + ex2.getMessage());
            }
            } 
        else{
            System.out.println("JpegDisplay Connection is Null");
        }
        }catch(SQLServerException ex){
            System.out.println("JpegGet Err:" + ex.getMessage());
        }
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
