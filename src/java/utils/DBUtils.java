/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author quang
 */
public class DBUtils {
    public static final Connection getConnection() throws SQLException, ClassNotFoundException {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url= "jdbc:sqlserver://HOA-PC\\HOAQUANG:1433;databaseName=Workshop2";
        conn= DriverManager.getConnection(url, "sa", "12345");
        return conn;
    }
}
