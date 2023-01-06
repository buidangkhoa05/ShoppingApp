/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package khoabd.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.sql.DataSource;

public class DBHelper implements Serializable {

    public static Connection makeConnection() throws SQLException, NamingException /*ClassNotFoundExceptionZZ*/ {
        /*
      1. Load driver
      2. Create Url connection string
        prototype : protocol:serverName ://ip:port;databaseName=name
      3.Open connection
         */

//        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
//        String url = "jdbc:sqlserver://localhost:1433;databaseName=SE1603";
//        Connection cn = DriverManager.getConnection(url, "sa", "12345");
//
//        return cn;
//        //1 get context server- JNDI java naming  directory interface
        Context serverContext = new InitialContext();

//        //2. look up data source of container 
        Context tomcatContext = (Context) serverContext.lookup("java:comp/env");

//        //3. look up data define ds
        DataSource ds = (DataSource) tomcatContext.lookup("Database");

//        //4 open connection
        Connection con = ds.getConnection();

        return con;
    }

    public static Properties getSiteMaps(String filePath, ServletContext context) throws IOException {
        if (filePath == null) {
            return null;
        }
        if (filePath.trim().isEmpty()) {
            return null;
        }
        Properties result = new Properties();
        InputStream is = null;

        try {
            is = context.getResourceAsStream(filePath);
            result.load(is);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return result;
    }
    
    public static String getUrl(ServletContext context, String key) {
        //get attribute from context scope
        Properties siteMaps = (Properties) context.getAttribute("SITE_MAP");
        if (key == null) {
            return siteMaps.getProperty("");
        }
        return siteMaps.getProperty(key);
    }
}
