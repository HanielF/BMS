package jdbc;
import java.sql.*;  

public class Database {
    public static String driver = "org.mariadb.jdbc.Driver";  
    public static String url = "jdbc:mysql://127.0.0.1:3306/laravel";  
    public static String usr = "root";  
    public static String pwd = "asdfhjkl";  
  
    public static void main (String [] args) {  
    	Connection con;  
    	Statement sql;   
    	ResultSet rs;  

    	try {  

    		Class.forName (driver);  
    	} catch (ClassNotFoundException e) {    

    		System.out.println ("error!Class not found");  
    		return;  
    	}  
    	try {   
    		con = DriverManager.getConnection (url, usr, pwd);  
    		sql = con.createStatement ();  
    		rs = sql.executeQuery ("select * from articles");  
    		while (rs.next ()) {  
    			System.out.println (rs.getString (1));   
			    System.out.println (rs.getString (2));  
    		}  
    		con.close();  
    	} catch (SQLException e) {  
    		System.out.println ("error!SQL Exception");  
    		return;  
    	}  
    }

}
