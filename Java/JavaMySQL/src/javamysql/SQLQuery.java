/*
 * SQLQuery.java
 *
 * 
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javamysql;


import java.sql.*;

/**
 *
 * @author ingres
 */
public class SQLQuery {
    
    /** Creates a new instance of SQLQuery */
    public SQLQuery() {
    }
    
    public  void queryStatement(Connection con) {
        try {
            Statement stmt = con.createStatement();
            String qry = "select * from branch";
            ResultSet rs = stmt.executeQuery(qry);
        
            System.out.println("Results of a Statement Object Query");  
            while( rs.next()) { 
                System.out.print( rs.getString("branchno"));     
                System.out.print("    " + rs.getString(2));          
                System.out.print("    " + rs.getString(3));        
                System.out.println("");      
            }
        rs.close();
        stmt.close();
       }catch (Exception io) {
                System.out.println("error"+io);
       };   
  
     }//query
    
}
