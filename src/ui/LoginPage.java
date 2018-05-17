package ui;

import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage implements ActionListener{
	private JFrame jf=new JFrame("Login Page");
	private JPanel jp_main;
	private JPanel jp_center=new JPanel();
	private JPanel jp_top=new JPanel();
	private JPanel jp_bow=new JPanel();//用这两个调整主面板界面
	
	private JLabel jl_usr=new JLabel("用户名");
	private JLabel jl_pwd=new JLabel("密码");
	private JTextField jtf_usr=new JTextField();
	private JPasswordField jpf=new JPasswordField();
	
	private JButton jb_login=new JButton("登陆");
	private JButton jb_register=new JButton("注册");
	
	private int width=600;
	private int height=400;
	
	@SuppressWarnings("serial")
	public LoginPage() {
		jp_main=new JPanel(new BorderLayout()){  
            @Override  
            public void paintComponent(Graphics g) {  
                Graphics2D g2=(Graphics2D)g;   
                super.paintComponents(g);  
                Image image=Toolkit.getDefaultToolkit().getImage("F:\\Eclipse\\JAVA\\BookManager\\source\\PureColor.jpg");  
                g2.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);  
            }  
         }; 
		jp_main=new JPanel(new BorderLayout());
        initCenter();
        
        jp_top.setPreferredSize(new Dimension(jf.getWidth(),30));
        jp_bow.setPreferredSize(new Dimension(jf.getWidth(),20));
        jp_top.setOpaque(false);
        jp_bow.setOpaque(false);
      
        jp_main.add(jp_top,BorderLayout.NORTH);
        jp_main.add(jp_bow,BorderLayout.SOUTH);
	 	jp_main.add(jp_center,BorderLayout.CENTER);
        
        //获得屏幕高度宽度，用来设置居中
        Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();  
        jf.setLocation((scr.width-jf.getWidth())/2-width/2,(scr.height-jf.getHeight())/2-height/2); 
        
	 	jf.setSize(width,height);
        jf.setContentPane(jp_main);
	 	jf.setVisible(true);
	 	jf.setResizable(false);
	 	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initCenter() {
		jp_center.setOpaque(false);
        //jl_usr.setBounds(,30,60,20);  
        jl_pwd.setBounds(80,55,60,20); 
        jp_center.add(jl_usr);
        jp_center.add(jl_pwd);
	}
	
	public void setJPanel() {
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
