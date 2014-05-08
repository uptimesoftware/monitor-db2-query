/*
 * Db2DatabaseConnection.java
 *
 * Created on March 18, 2007, 1:16 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package db2basic;
import java.sql.*;

public class Db2DatabaseConnection {
    
    private String url = null;
    //private String url = "jdbc:mysql://localhost:3306/test";
    private Connection con;
    public ResultSet rs = null;
  
    
    
    /**
     * Creates a new instance of Db2DatabaseConnection
     */
    public Db2DatabaseConnection(String dbHost, String dbPort, String dbName) {
        
        try {
            
            url = "jdbc:db2://" + dbHost + ":" + dbPort + "/" + dbName;
            Class.forName("com.ibm.db2.jcc.DB2Driver");
            
        } catch (Exception e){
            
            System.out.println("Error Received: " + e.getMessage());            
            
        }
    }
    
    public void Connect(String user, String password, String ssl) {
        java.util.Properties properties = new java.util.Properties();
        properties.put("user", user ) ;
        properties.put("password", password );
        properties.put("sslConnection", ssl);
        
        try {
            
            con = DriverManager.getConnection( url,  properties );
            
        }catch (Exception e) {

            System.out.println(e);
            // Catch Connection Exceptions
        }
        
    }
    
    public void Close() {
        
        try {
            con.close();
        } catch (Exception e) {
            
            // Close exception
        }
        
    }
    
    public ResultSet query(String sqlQuery) {
        
        Statement stmt;
        
        try {
            
            stmt = con.createStatement();
            rs = stmt.executeQuery( sqlQuery );
            
            
            
        } catch (Exception e) {
            System.out.println(e);
        }
        
        return rs;
    }
    
    
    
    
    
}

