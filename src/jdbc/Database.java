package jdbc;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.*;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.WindowConstants;
import ui.LoginPage;
import ui.MainPage;

public class Database {
	private String driver = "org.mariadb.jdbc.Driver" ;  
	private String url = "jdbc:mysql://127.0.0.1:3306/bms?useSSL=false&serverTimezone=GMT%2B8" ; //使用gmt+8时区并且设置useSSL=false
	private String usr;//数据库用户
	private String pwd;//数据库用户密码
	
    private String name;//登录用户名
    private int is_manager=0;
    private String id;//登录用户id
    private String upwd;//登录用户密码

    
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
    	if(query==null || query.equals("")) return null;

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
    //update tables but with change some values or attribute
    public void dbSet(String query) {
    	if(query==null || query.equals(""))return;
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
    
    //update tables with insert some records
	public void dbUpdate(
			String query) {
		if(query==null || query.equals(""))return;
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
				showDuplicateError();
			}
    	}
    }
    
	public int isManager(String query) {
		int flag = 0;
    	if(query==null || query.equals("")) return flag;
    	
    	Connection con=null;  
    	Statement stmt=null; 
    	ResultSet rs =null;

    	try {   
    		con = DriverManager.getConnection (url, usr, pwd);//获得connection对象
    		stmt = con.createStatement();  //获得statement对象
    		rs = stmt.executeQuery (query);
    		rs.next();
    		flag = rs.getInt("ismanager");
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
    	}
		return flag;
	}
    
	
	public void showDuplicateError() {
		JDialog jd = new JDialog(MainPage.jf);
		jd.setTitle("Duplicate Error");
		jd.setSize(350,160);
        Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();  
        jd.setLocation((scr.width-jd.getWidth())/2,(scr.height-jd.getHeight())/2);  
        jd.setVisible(true);
        jd.setLayout(null);
        
        JLabel jl = new JLabel("You have borrowed or returned this book!",JLabel.CENTER);
        JButton jb = new JButton("Conform");
        
        jb.setSize(120,30);
        jb.setLocation(jd.getWidth()/2-jb.getWidth()/2,80);
        jl.setSize(300,30);
        jl.setLocation(jd.getWidth()/2-jl.getWidth()/2, 30);
        
        jl.setFont(new Font("consolas",Font.PLAIN,19));
        jb.setFont(new Font("consolas",Font.PLAIN,19));
        
        jd.add(jb);
        jd.add(jl);
        
        jb.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jd.dispose();
        	}
        });
	}
	

    public void showSQLError(SQLException e) {
		JDialog jd_sql=new JDialog(LoginPage.jf,"SQL Error");
		jd_sql.setVisible(true);
		jd_sql.setBounds(600,350,300,160);
		Container jd_sql_con = jd_sql.getContentPane();
		
		jd_sql_con.setLayout(new BorderLayout());
		jd_sql_con.add(new JLabel("  SQLException: " + e.getMessage()+"\n  SQLState: " + e.getSQLState()+"\n  VendorError: " + e.getErrorCode()),BorderLayout.CENTER);
    }


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public int getIs_manager() {
		return is_manager;
	}


	public void setIs_manager(int is_manager) {
		this.is_manager = is_manager;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getUpwd() {
		return upwd;
	}


	public void setUpwd(String upwd) {
		this.upwd = upwd;
	}

}
