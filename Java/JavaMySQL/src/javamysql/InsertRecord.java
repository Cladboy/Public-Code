/*
 * InsertRecord.java
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
public class InsertRecord {
    
    /** Creates a new instance of InsertRecord */
    public InsertRecord() {
    }
    
     public  void insert(Connection con) {
        try {
            // insert
            Statement insertStmt = con.createStatement();
            String insertSQL = " Insert into student values ('1234', 'DTrump', 'SmartGenius', 100)";
            int res = insertStmt.executeUpdate(insertSQL);
            System.out.println("The Number or records inserted is      " +res);
            // You May need to uncomment if Autocommit is not set
            //con.commit();
            insertStmt.close();
       }catch (Exception io) {
                System.out.println("error"+io);
       };   
  
     }//insert
}
