import java.sql.*;

public static void main(String[] args) {
    String user = "postgres";
    String password ="1234";

    try{
                 Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/pstgres", user, password);
                 System.out.println("Connected to the PostgreSQL server successfully."); 
    		 	 
    	 }catch (SQLException e) {
                  System.out.println(e.getMessage());
         }
    
}