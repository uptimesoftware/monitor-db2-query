/*
 * Main.java
 *
 * Created on May 11, 2007, 10:54 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package db2basic;
import java.sql.ResultSetMetaData;
import java.util.Arrays;

/**
 *
 * @author Chris
 */
public class Main {
    
    /** Creates a new instance of Main */
    public Main() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        if (args.length <= 6 ){
            System.err.println("Invalid number of arguments: " + args.length);
            System.err.println(Arrays.toString(args));
            System.exit(1);
        }
        
        /*
         * Args
         * 0 - Hostname
         * 1 - Port
         * 2 - Userid
         * 3 - Password
         * 4 - Database
         * 5 - SQL
         * 6 - Data type
         * 7 - SSL (optional)
         */
        
        String sql = null;
        String queryResult = "";
        int rowCount = 0 ;
        sql = args[5];
        String dataType = args[6].toLowerCase();
        String sslVal = "false" ;
        if (args.length > 7 ) {
            String sslArg = args[7].toLowerCase() ;
            if (sslArg.matches("\\s*ssl\\s*") ){
                sslVal = "true" ;
            }
        }
        
              
        
        try {
            
            Db2DatabaseConnection db   = new Db2DatabaseConnection(args[0], args[1], args[4]);
            
            db.Connect(args[2], args[3], sslVal);
            
            db.query(sql);
            
            ResultSetMetaData rsmd  = db.rs.getMetaData();
            
            while (db.rs.next()) {
                /*
                for (int i = 1; i <= rsmd.getColumnCount(); ++i) {
                    queryResult += db.rs.getString(i) + " ";
                }

                 */
                if ( rowCount == 0) {

                    queryResult = db.rs.getString(1) ;
                }
                rowCount++ ;
            }
            
            db.Close();

  
             if (queryResult.length()== 0 ){
                 System.out.println("WARN - Empty result");
                 System.exit(1);
             }
            
             if (dataType.matches("\\s*numeric\\s*")){
                 if (queryResult.matches("^\\s*\\d+\\s*$")){
                     System.out.println("Numeric " + queryResult) ;
                     System.out.println("Row " + rowCount ) ;
                     System.exit(0);
                 }else {
                     System.out.println("WARN - Result is not Numeric ") ;
                     System.out.println("Result = " + queryResult) ;
                     System.exit(1);
                 }
             } else if (dataType.matches("\\s*string\\s*")){
                 if (queryResult.matches(".*\\w+.*")){
                     System.out.println("String " + queryResult) ;
                     System.out.println("Row " + rowCount ) ;
                     System.exit(0);
                 }else {
                     System.out.println("WARN - Result is not String ") ;
                     System.out.println("Result = " + queryResult) ;
                     System.exit(1);
                 }
             }
            //System.out.println(args[6] + " " + queryResult);
            
          /*  if (queryResult.indexOf(args[4]) != -1){
                System.out.println("OK - Expression was matched");
                System.exit(0);
            }else {
                System.out.println("CRIT - No matches in result");
                System.exit(2);
            } */
            
        } catch(Exception e){
            System.err.println(e.getMessage());
            System.out.println("WARN - Monitor Failed");
            System.exit(1);
        }
        
    }
}
