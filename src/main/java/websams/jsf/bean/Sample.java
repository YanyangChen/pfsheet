package websams.jsf.bean;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Sample
{
  public static void main(String[] args) throws ClassNotFoundException
  {
    // load the sqlite-JDBC driver using the current class loader
    Class.forName("org.sqlite.JDBC");

    Connection connection = null;
    try
    {
      // create a database connection
      connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
      Statement statement = connection.createStatement();
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

//      statement.executeUpdate("drop table if exists subject");
//      statement.executeUpdate("create table subject (CHIN string, ENG string, GENS string, LILE string, PE string)");
//      statement.executeUpdate("insert into subject values('chi1', 'eng1', 'gens1', 'lile1', 'pe1')");
//      statement.executeUpdate("insert into subject values('chi2', 'eng2', 'gens2', 'lile2', 'pe2')");
      ResultSet rs = statement.executeQuery("select * from subject");
      while(rs.next())
      {
        // read the result set
        System.out.println("CHIN = " + rs.getString("CHIN"));
        System.out.println("ENG = " + rs.getString("ENG"));
        System.out.println("GENS = " + rs.getString("GENS"));
        System.out.println("LILE = " + rs.getString("LILE"));
        System.out.println("PE = " + rs.getString("PE"));
        
      }
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory", 
      // it probably means no database file is found
      System.err.println(e.getMessage());
    }
    finally
    {
      try
      {
        if(connection != null)
          connection.close();
      }
      catch(SQLException e)
      {
        // connection close failed.
        System.err.println(e);
      }
    }
  }
}