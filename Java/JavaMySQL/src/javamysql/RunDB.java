/*
 * RunDB.java
 *
 * 
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package javamysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;


/**
 *
 * @author 
 */
public class RunDB {
    
    /** Creates a new instance of RunDB */
    public RunDB() {
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
	
            
               
        
         Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        
        //Connection con = DriverManager.getConnection(url, userName , password );
        
        // You Must replace YourName with your name and YourPassword with your password in the driver url 
        
        Connection con = DriverManager.getConnection("jdbc:mysql://157.190.43.7:3306/estateagent5thed?user=YourName&password=YourPassword" );
        System.out.println ("Database connection established");
        Statement s = con.createStatement ();
        
        //Simple Query 
        ResultSet rs = s.executeQuery ("SELECT * FROM staff");
        while (rs.next ())
        {
            String staffNoVal = rs.getString ("StaffNo");
            System.out.println("StaffNo is : "+staffNoVal);
          }
        
        /*
         * This executes a simple query using a Statement object over
         * the Connection object 'con'.
         *
         *  UNcomment Next 2 lines
         */
        SQLQuery sqlQuery = new SQLQuery();
        sqlQuery.queryStatement(con);

        
        /*
         * This executes a simple query using a PreparedStatement object over
         * the Connection object 'con'.
        *
         *  UNcomment Next 2 lines
         */
        //SQLQueryPrepared sqlQueryPrepared = new SQLQueryPrepared();
        //sqlQueryPrepared.queryStatement(con);
        
        /*
         * This executes an Insert into a table using the Connection object 'con'.
         * Your need to uncomment the line - ir.insert(con);
        *
         *  UNcomment Next 2 lines
         */
        
        //InsertRecord ir = new InsertRecord();
        //ir.insert(con);
        
        /*
         * This executes an Update into a table using the Connection object 'con'.
         * Your need to uncomment the line - ur.update(con);
        *
         *  UNcomment Next 2 lines
         */
        
        //UpdateRecord ur = new UpdateRecord();
        //ur.update(con);
        
        
        /*
         * This executes an Delete into a table using the Connection object 'con'.
         * Your need to uncomment the line - dr.delete(con); 
        *
         *  UNcomment Next 2 lines
         */
        //DeleteRecord dr = new DeleteRecord();
        //dr.delete(con);        
        
        

        }catch (Exception ex) {
            System.out.println("SQLException: " + ex.getMessage());
            
        };
    
    }
    
}
