package jdbc;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.sql.*;
import java.util.*;

import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import ui.LoginPage;

public class Database {
	private String driver = "org.mariadb.jdbc.Driver" ;  
	private String url = "jdbc:mysql://127.0.0.1:3306/bms?useSSL=false&serverTimezone=GMT%2B8" ; //使用gmt+8时区并且设置useSSL=false
    private String usr ;  
    private String pwd ;   
    private String cur_user;
  
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
    
    //如果有其他数据库账户
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
    

    //判断是否有这个用户，用于登陆
    public Boolean loginJudge(String id,String upwd) {
    	if(id.equals("") || upwd.equals("") || id==null || upwd == null)return false;
    	
    	Connection con=null;  
    	Statement stmt=null; 
    	ResultSet rs = null;  
    	Boolean flag=false;
    	
    	try {
    		con = DriverManager.getConnection (url, usr, pwd);//获得connection对象
    		stmt = con.createStatement ();  //获得statement对象
    		rs = stmt.executeQuery ("select * from users where uid="+id); 
    		rs.next();
    		if(rs.getString(2).equals(id)&&rs.getString(3).equals(upwd)) {
    				flag=true;
    		}
    		else {
    			JDialog jd_login=new JDialog(LoginPage.jf,"Login Error");
    			jd_login.setVisible(true);
    			jd_login.setBounds(600,350,300,150);
    			Container dcon = jd_login.getContentPane();
    			
    			JLabel jb = new JLabel("      User Account or Password wrong!");
    			jb.setFont(new Font("Lucida Family",Font.PLAIN,15));
    			dcon.add(jb,BorderLayout.CENTER);
    			//jd_login.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    		}
    	}
    	catch (SQLException e) {
    		//showSQLError(e);	
    		e.printStackTrace();
    	}
    	finally {
			try {
				if(con!=null)con.close();
				if(stmt!=null)stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
    	return flag;
    }
    
    
    @SuppressWarnings("finally")
    //这里不可以直接返回局部ResultSet，会被销毁
	public ArrayList dbGet(String query) {
    	if(query==null) return null;
    	
    	Connection con=null;  
    	Statement stmt=null; 
    	ResultSet rs =null;
    	ArrayList arr = new ArrayList();
    	try {   
    		con = DriverManager.getConnection (url, usr, pwd);//获得connection对象
    		stmt = con.createStatement();  //获得statement对象
    		rs = stmt.executeQuery (query);
    		
    		ResultSetMetaData rsmd = rs.getMetaData();
    		int colCnt = rsmd.getColumnCount();
    		
    		while(rs.next()) {
    			HashMap hmp = new HashMap();
    			for(int i = 1;i<=colCnt;i++) {
    				hmp.put(rsmd.getColumnName(i), rs.getObject(i));
    			}
    			arr.add(hmp);		
    		}
    	} 
    	catch (SQLException e) {  
    		showSQLError(e);
    	}
    	finally {
			try {
				if(con!=null)con.close();
				if(stmt!=null)stmt.close();
				if(rs!=null)rs.close();
			} 
			catch (SQLException e) {
				showSQLError(e);
			}
			return arr;
    	}
    }
    
    
    //update tables
	public void dbUpdate(String query) {
		if(query==null)return;
    	Connection con=null;  
    	Statement stmt=null; 
    	
    	try {   
    		con = DriverManager.getConnection (url, usr, pwd);//获得connection对象
    		stmt = con.createStatement();  //获得statement对象
    		stmt.executeUpdate (query);
    	} 
    	catch (SQLException e) {  
    		showSQLError(e);
    	}
    	finally {
			try {
				if(con!=null)con.close();
				if(stmt!=null)stmt.close();
			} 
			catch (SQLException e) {
				showSQLError(e);
			}
    	}
    }
    
    
    public void showSQLError(SQLException e) {
		JDialog jd_sql=new JDialog(LoginPage.jf,"SQL Error");
		jd_sql.setVisible(true);
		jd_sql.setBounds(600,350,300,150);
		Container jd_sql_con = jd_sql.getContentPane();
		
		jd_sql_con.setLayout(new BorderLayout());
		jd_sql_con.add(new JLabel("  SQLException: " + e.getMessage()),BorderLayout.NORTH);
		jd_sql_con.add(new JLabel("  SQLState: " + e.getSQLState()),BorderLayout.CENTER);
		jd_sql_con.add(new JLabel("  VendorError: " + e.getErrorCode()),BorderLayout.SOUTH);
    }

	public String getCur_user() {
		return cur_user;
	}

	public void setCur_user(String cur_user) {
		this.cur_user = cur_user;
	}

}
