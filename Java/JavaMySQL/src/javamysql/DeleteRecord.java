/*
 * DeleteRecord.java
 *
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
public class DeleteRecord {
    
    /** Creates a new instance of DeleteRecord */
    public DeleteRecord() {
    }
    
     public  void delete(Connection con) {
        try {
            //delete
            Statement deleteStmt = con.createStatement();
            String deleteSQL = " Delete from  student where sid ='1234' or name = 'DTrump'";
            int res = deleteStmt.executeUpdate(deleteSQL);
            System.out.println("The Number or records deleted is      " +res);
            // You May need to uncomment if Autocommit is not set
            //  con.commit();   
            deleteStmt.close();
       }catch (Exception io) {
                System.out.println("error"+io);
       };   
  
     }//delete
    
}
