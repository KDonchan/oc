/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sql;
import java.sql.Connection;
import java.sql.SQLException;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class SqlMain {

	public static Connection MakeConnection(String wServer_name,String wDatabase_name,String wUser_name,String wUser_password) throws SQLServerException{
		Connection wConnection = null;
		SQLServerDataSource wda = new	SQLServerDataSource();
		wda.setServerName(wServer_name);
		wda.setDatabaseName(wDatabase_name);
		wda.setUser(wUser_name);
		wda.setPassword(wUser_password);wConnection = wda.getConnection();
		return (wConnection);
	}

	public static Connection MakeConnection(String wServer_name,String wDatabase_name) throws SQLServerException{
		Connection wConnection = null;
		SQLServerDataSource wda = new SQLServerDataSource();
		wda.setServerName(wServer_name);
		wda.setDatabaseName(wDatabase_name);
		wda.setIntegratedSecurity(true);
		wConnection = wda.getConnection();
		return(wConnection);
	}
        
        public static Connection MakeConnection(HttpServletRequest request) throws SQLServerException{
            Connection wcon = null;
            ServletContext application = request.getServletContext();
            String wServer = application.getInitParameter("a_sqlServer");
            String wDatabase = application.getInitParameter("a_database");
            wcon = MakeConnection(wServer, wDatabase);
            return wcon;
        }
}