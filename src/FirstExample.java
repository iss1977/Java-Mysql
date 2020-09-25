//STEP 1. Import required packages
import java.sql.*;
import java.sql.Connection; import java.sql.DriverManager; import java.sql.SQLException; import java.util.Properties;


public class FirstExample {

    // connection data
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:MySQL://localhost/employees";
    static final String USER = "sebastian";
    static final String PASS = "123456";

    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try{
            // this is not needed anymore
            //            Class.forName("com.mysql.cj.jdbc.Driver");

            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            System.out.println(conn.toString());

            //execute query
            System.out.println("Creating statement...");
            stmt = conn.createStatement();
            System.out.println(stmt.toString());
            String sql;
            sql = "SELECT first_name as first, last_name as last FROM employees";
            ResultSet rs = stmt.executeQuery(sql);

            // extract data
            while(rs.next()){
                String first = rs.getString("first");
                String last = rs.getString("last");

                //Display values
                System.out.print("First: " + first);
                System.out.println(", Last: " + last);
            }

            //clean-up
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            //Handle errors for JDBC
            se.printStackTrace();
        }catch(Exception e){
            //Handle errors for Class.forName
            e.printStackTrace();
        }finally{
            //finally block used to close resources
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }// nothing we can do
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }//end finally try
        }//end try
        System.out.println("Goodbye!");
    }//end main
}//end FirstExample