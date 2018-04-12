/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author j-knakagami2
 */
public class UserBean {
   private String user_id;
    private String user_name;
    private String user_sub_name;
    private byte[] user_photo;
    private String user_pass;
    private boolean card_flg;
    private boolean admin_flg;
    private boolean login_flg;
    private SQLException sql_err;
    //コンストラクタ
    
   public UserBean() {
        login_flg=false;
        admin_flg = false;
        card_flg=false;
        sql_err = null;
    }   

    public String getUser_sub_name() {
        return user_sub_name;
    }

    public byte[] getUser_photo() {
        return user_photo;
    }

    public boolean isCard_flg() {
        return card_flg;
    }

    public void setUser_sub_name(String user_sub_name) {
        this.user_sub_name = user_sub_name;
    }

    public void setUser_photo(byte[] user_photo) {
        this.user_photo = user_photo;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public void setUser_pass(String user_pass) {
        this.user_pass = user_pass;
    }

    public boolean isAdmin_flg() {
        return admin_flg;
    }
    
    public String getUser_id(){
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_pass() {
        return user_pass;
    }

    public boolean isLogin_flg() {
        return login_flg;
    }

    public SQLException getSql_err() {
        return sql_err;
    }
    
    public boolean loginCheck(HttpServletRequest request) throws SQLException
    {
        login_flg=false;
        if(user_id!=null && user_pass !=null){
        Connection wcon = sql.SqlMain.MakeConnection(request);
        if(wcon !=null){
            String wsql = "select * from oc_user where"
                        +" user_id=? and user_pass=?";
            PreparedStatement stmt = wcon.prepareStatement(wsql);
            stmt.setString(1, user_id);
            stmt.setString(2, user_pass);
            ResultSet rs = stmt.executeQuery();
            if(rs.next())
            {
                user_id = rs.getString("user_id").trim();
                user_pass = rs.getString("user_pass").trim();
                user_name = rs.getString("user_name").trim();
                user_sub_name = rs.getString("user_sub_name").trim();
                user_photo = rs.getBytes("user_photo");
                card_flg = rs.getBoolean("card_flg");
                admin_flg = rs.getBoolean("admin_flg");
                login_flg = true;
            }
        }
        }
        return login_flg;
    }

    public static List<UserBean> allUserGet(HttpServletRequest request) throws SQLException
    {
        List<UserBean> users=null;
        Connection wcon= sql.SqlMain.MakeConnection(request);
        if(wcon !=null){            
            String wsql="select * from oc_user";
            Statement stmt = wcon.createStatement();
            ResultSet rs =  stmt.executeQuery(wsql);
            users = new ArrayList<>();
            UserBean wUser;
            while(rs.next()){
                wUser = new UserBean();
                wUser.setUser_id( rs.getString("user_id").trim());
                wUser.setUser_name(rs.getString("user_name").trim());
                wUser.setUser_pass(rs.getString("user_pass").trim());
                wUser.setUser_sub_name(rs.getString("user_sub_name").trim());
                wUser.setUser_photo(rs.getBytes("user_photo"));
                users.add(wUser);
            }
        }
        return users;
    }
/*    
    public boolean userAdd(HttpServletRequest request){
        boolean flg=false;
        Connection wcon = SqlMain.MakeConnection(request);
        if(user_id !=null && user_pass !=null && user_name !=null
                && wcon !=null){  
            String wsql = "insert oc_user values(?,?,?,null,?,?,?)";
            try {
                PreparedStatement stmt =wcon.prepareStatement(wsql);
                stmt.setString(1, user_id);
                stmt.setString(2, user_name);
                stmt.setString(3, user_pass);
                stmt.setString(4, user_mail_adr);
                stmt.setString(5, user_pop_pass);
                stmt.setString(6, user_pop_server);
                stmt.executeUpdate();
                flg = true;
                sql_err = null;
                } catch (SQLException ex) {
                sql_err = ex;
            }           
        }
        return flg;
    }
*/
    
}
