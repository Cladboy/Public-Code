/*
 * UpdateRecord.java
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
public class UpdateRecord {
    
    /** Creates a new instance of UpdateRecord */
    public UpdateRecord() {
    }
    
    
     public  void update(Connection con) {
        try {
            //update
            Statement updateStmt = con.createStatement();
            String updateSQL = " Update student set age = 10 where sid ='1234'";
            int res = updateStmt.executeUpdate(updateSQL);
            System.out.println("The Number or records updated is      " +res);
            // You May need to uncomment if Autocommit is not set
            //con.commit();
            updateStmt.close();
       }catch (Exception io) {
                System.out.println("error"+io);
       };   
  
     }//update
    
}
