package jdbc;
import java.sql.*;  

public class Database {
	public static String driver = "com.mysql.cj.jdbc.Driver" ;   //最新官方支持
	public static String url = "jdbc:mysql://127.0.0.1:3306/bms?useSSL=false&serverTimezone=GMT%2B8" ; //使用gmt+8时区并且设置useSSL=false
    private static String usr ;  
    private static String pwd ;  
    private String sql;
  
    public void setUsr(String dbusr) {
    	usr=dbusr;
    }
    
    public Database() {  
    	usr= "root";
    	pwd=" gabirel ";
    	try {
    		Class.forName (driver);  //连接
    		System.out.println("success to load MySQL driver");
    	} catch (ClassNotFoundException e) {    
    		System.out.println ("error! Class not found!");  
    		return;  
    	}  
    }
    
    public Database(String dbusr,String dbpwd) {  
    	usr= dbusr;
    	pwd=dbpwd;
    	try {
    		Class.forName (driver);  //连接
    		System.out.println("success to load MySQL driver");
    	} catch (ClassNotFoundException e) {    
    		System.out.println ("error! Class not found!");  
    		return;  
    	}  
    }
    
    
    @SuppressWarnings("finally")
	public ResultSet dbGet() {
    	Connection con;  
    	Statement stmt;   
    	ResultSet rs = null;  

    	try {   
    		con = DriverManager.getConnection (url, usr, pwd);//获得connection对象
    		stmt = con.createStatement ();  //获得statement对象
    		rs = stmt.executeQuery ("select * from books"); 
    		
    		//Test function
    		while (rs.next ()) {  
    			System.out.print (rs.getString (1)+"\t");   
			    System.out.print (rs.getString (2));  
			    System.out.println();
    		}  
    		con.close();    		
    	} 
    	catch (SQLException e) {  
    		// handle any errors
    	    System.out.println("SQLException: " + e.getMessage());
    	    System.out.println("SQLState: " + e.getSQLState());
    	    System.out.println("VendorError: " + e.getErrorCode()); 
    		System.exit(0);
    	}
    	finally {
    		return rs;
    	}
    }

}
