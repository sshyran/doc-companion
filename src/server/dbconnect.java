/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package server;
import java.sql.*;
/**
 *
 * @author Pegasus
 */
public class dbconnect {
  private  Connection con=null;
  public dbconnect()
  {try
  {
      Class.forName("com.mysql.jdbc.Driver");
  }catch (ClassNotFoundException ex)
  {System.out.println(ex.toString());}
    try
   {con = DriverManager.getConnection("jdbc:mysql://localhost:3306/doccompanion", "root", "admin");
   System.out.println("Connection set up to"+con.getMetaData().getURL());
   }catch(SQLException e)
   {System.out.println(e.toString());}
    
  }
  public ResultSet exeqcommand(String sqlstm) throws SQLException
  {
     
         Statement st=con.createStatement();
         ResultSet exeqrs = st.executeQuery(sqlstm);
        /*while(exeqrs.next())
            {
               // System.out.print(exeqrs.getString("login_id"));
                System.out.println(exeqrs.getString("password"));
            }*/
         return exeqrs;
     
  }
  public int updt(String sqlstm) throws SQLException{
      int rows;
      Statement st=con.createStatement();
      rows=st.executeUpdate(sqlstm);
      return rows;
  }
}
