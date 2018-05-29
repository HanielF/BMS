package ui;

import ui.MainClass;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;

public class LoginPage {
	public static JFrame jf=new JFrame("Login Page");
	private JPanel jp_main;
	
	private JLabel jl_usr=new JLabel("Account");
	private JLabel jl_pwd=new JLabel("Password");
	private JLabel jl_title=new JLabel("Login System");
	
	private JTextField jtf_usr=new JTextField();
	private JPasswordField jpf=new JPasswordField();	
	
	private JButton jb_login=new JButton("Sign in");
	private JButton jb_register=new JButton("Sign Up");
	private JButton jb_forget=new JButton("Forget Password? ");	
	
	private String id;
	private String pwd;
	private int width=400;
	private int height=270;
	
	@SuppressWarnings("serial")
	public LoginPage() {
        drawMain();
        initMain();
        initFrame();
     
        jb_login.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
                id=jtf_usr.getText();
                pwd=new String(jpf.getPassword());
                if(id.equals("") || pwd.equals(""))
                	showLoginError();
                else if(MainClass.db.loginJudge(id, pwd)) {
                	MainClass.mp.jf.setVisible(true);
                	MainClass.db.setCur_user(id);
                	jf.dispose();
                }else {
                	showLoginError();
                }
        	}
        });
        	
        jb_forget.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e) {
        		MainClass.fp.jf.setVisible(true);
        	}
        });
        
        jb_register.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		MainClass.su.jf.setVisible(true);
        	}
        });
	}
	
	public void showLoginError() {
		JDialog jd = new JDialog(jf);
		jd.setTitle("Login Error");
		jd.setSize(350,150);
        Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();  
        jd.setLocation((scr.width-jd.getWidth())/2,(scr.height-jd.getHeight())/2);  
        jd.setVisible(true);
        jd.setLayout(null);
        
        JLabel jl = new JLabel("Account Or Password Error!",JLabel.CENTER);
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
	
	public void drawMain() {
		jp_main=new JPanel(){  
            @Override  
            public void paintComponent(Graphics g) {  
                Graphics2D g2=(Graphics2D)g;   
                super.paintComponents(g);  
                Image image = new ImageIcon("./source/PureColor.jpg").getImage(); 
                //Image image = new ImageIcon("F:\\Eclipse\\JAVA\\BookManager\\source\\PureColor.jpg").getImage(); 
                //Image image=Toolkit.getDefaultToolkit().getImage("F:\\Eclipse\\JAVA\\BookManager\\source\\PureColor.jpg");  
                g2.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);  
            }  
         }; 
	}
	
	public void initFrame() {
		jf.setSize(width,height);
        //获得屏幕高度宽度，用来设置居中
        Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();  
        jf.setLocation((scr.width-jf.getWidth())/2,(scr.height-jf.getHeight())/2);   

        jf.setContentPane(jp_main);
	 	jf.setVisible(true);
	 	jf.setResizable(false);
	 	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initMain() {
		jp_main.setOpaque(false);
        jp_main.setLayout(null);
		
		//x,y,width,height
		jl_title.setBounds(145,10,150,30);
		jl_title.setFont(new Font("Arial",Font.BOLD,19));
		
		jl_usr.setBounds(60, 60, 100, 30);
		jl_usr.setFont(new Font("consolas",Font.PLAIN,16));
        jtf_usr.setBounds(140,60,190,30);     
        
        jl_pwd.setBounds(60,100,100,30);
        jl_pwd.setFont(new Font("consolas",Font.PLAIN,16));
        jpf.setBounds(140,100,190,30);
  
        jb_register.setBounds(80, 150, 100, 30);
        jb_register.setFont(new Font("Lucida Family",Font.PLAIN,15));
        jb_login.setBounds(220, 150, 100, 30);
        jb_login.setFont(new Font("Lucida Family",Font.PLAIN,15));
        
        jb_forget.setBounds(115,190,160,30);
        jb_forget.setBackground(new Color(0));
        jb_forget.setBorderPainted(false);
        jb_forget.setOpaque(false); 
        jb_forget.setFont(new Font("Lucida Family",Font.PLAIN,13));

        jp_main.add(jl_title);
        jp_main.add(jl_usr);
        jp_main.add(jtf_usr);
        jp_main.add(jl_pwd);
        jp_main.add(jpf);
        jp_main.add(jb_login);
        jp_main.add(jb_register);
        jp_main.add(jb_forget);
        
        jp_main.setSize(width,height);
        jp_main.setVisible(true);
	}


}
