import java.sql.*;

public class SqlConnect {
    
    public static Connection con = null;
    public static Connection startConnection(){
        
        
        try {
            
            Class.forName("com.mysql.jdbc.Driver").newInstance(); 
            String url = ""; 
            url += "jdbc:mysql://127.0.0.1/gritodb?"; 
            url += "user=root&password=hearmehoar1114"; 
            con = DriverManager.getConnection(url);
            System.out.println("Connection Successful");
            
        } catch (SQLException e) { 
                System.out.println(e.getMessage()); 
        } catch (ClassNotFoundException e) { 
                System.out.println(e.getMessage()); 
        } catch (Exception e) { 
                System.out.println(e.getMessage()); 
        }
        
        return con;
    }
    
    public static void closeConnection() {
        
        try {
         
            con.close(); 
            System.out.println("Connection closed.");
        } catch (SQLException e) { 
            System.out.println(e.getMessage()); 
        } catch (Exception e) { 
            System.out.println(e.getMessage()); 
        } 
        
    }       
    
}

