package ui;

import javax.swing.*;

import jdbc.Database;
import ui.ForgetPwd.Submit1ActionListener;
import ui.ForgetPwd.Submit2ActionListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

public class ForgetPwd {
	public static JFrame jf=new JFrame("Forget Password Page");
	private JPanel jp_main1;  //main1面板用于获取用户名和ID，并验证是否匹配
	private JPanel jp_main2;  //main2面板用于获取密保答案和新密码
	private JPanel jp_title1=new JPanel();
	private JPanel jp_title2=new JPanel();
	
	private JLabel jl_title1=new JLabel("Please input the account");
	private JLabel jl_name=new JLabel("Name");
	private JLabel jl_id=new JLabel("ID");
	private JLabel jl_title2=new JLabel("Set password");
	private JLabel jl_ques=new JLabel("Question");
	private JLabel jl_question;
	private JLabel jl_answ=new JLabel("Answer");
	private JLabel jl_new_pwd=new JLabel("New Password");
	private JLabel jl_pwd_conf=new JLabel("Confirm Password");
	
	private JTextField jtf_name=new JTextField();
	private JTextField jtf_id=new JTextField();
	private JTextField jtf_answ=new JTextField();
	private JPasswordField jpf_new_pwd=new JPasswordField();
	private JPasswordField jpf_pwd_conf=new JPasswordField();
	
	private JButton jb_submit1=new JButton("Next");
	private JButton jb_submit2=new JButton("Submit");
	
	private int width=500;
	private int height=320;
	
	//显示输入信息有误窗口
	private void showwrong(String wronginf) {
		final JDialog jd_wrong=new JDialog(jf,"Wrong!");
		jd_wrong.setVisible(true);
		jd_wrong.setSize(500,150);
		Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();  
        jd_wrong.setLocation((scr.width-jd_wrong.getWidth())/2,(scr.height-jd_wrong.getHeight())/2);   
		Container con=jd_wrong.getContentPane();
		con.setLayout(null);
		
		JLabel jl_wrong=new JLabel(wronginf);
		jl_wrong.setFont(new Font("consolas",Font.PLAIN,19));
		JPanel jp_wrong=new JPanel();
		jp_wrong.setBounds(0,20,500,30);
		jp_wrong.add(jl_wrong);
		con.add(jp_wrong);
		
		JButton jb_confirm=new JButton("Confirm");
		jb_confirm.setFont(new Font("consolas",Font.PLAIN,19));
		jb_confirm.setBounds(190, 60, 120, 30);
		con.add(jb_confirm);
		
		jb_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jd_wrong.dispose();
			}
		});
	}
	
	//显示操作成功对话框
	private void showSuccess() {
		final JDialog jd_success=new JDialog(jf,"Success!");
		jd_success.setVisible(true);
		jd_success.setSize(500,150);
		Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();  
        jd_success.setLocation((scr.width-jd_success.getWidth())/2,(scr.height-jd_success.getHeight())/2);   
		Container con=jd_success.getContentPane();
		con.setLayout(null);
		
		JLabel jl_success=new JLabel("Success!");
		jl_success.setFont(new Font("consolas",Font.PLAIN,19));
		JPanel jp_success=new JPanel();
		jp_success.setBounds(0,20,500,30);
		jp_success.add(jl_success);
		con.add(jp_success);
		
		JButton jb_confirm=new JButton("Confirm");
		jb_confirm.setFont(new Font("consolas",Font.PLAIN,19));
		jb_confirm.setBounds(190, 60, 120, 30);
		con.add(jb_confirm);
		
		jb_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jd_success.dispose();
			}
		});
	}
	
	//事件侦听器类——main1面板中的submit按钮
	class Submit1ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//获取用户填入的信息
			String name=jtf_name.getText();
			String id=jtf_id.getText();
			
			//验证ID是否存在、用户名与ID是否匹配
			boolean existFlag=false;
			ArrayList arr = MainClass.db.dbGet("select uid from users");
			Iterator ait = arr.iterator();
			while(ait.hasNext()) {
				Map map = (Map)ait.next();
				if (id.equals(map.get("uid"))) {
					existFlag=true;
					break;
				}
			}
			
			if (!existFlag) {
				showwrong("ID doesn't exist! Please input again.");
			}else {
				arr=MainClass.db.dbGet("select uname from users where uid="+id);
				ait = arr.iterator();
				Map map = (Map)ait.next();
				if (!name.equals(map.get("uname"))) {
					showwrong("Name doesn't match with id! Please input again.");
				}else {
					initMain2();
					jf.setContentPane(jp_main2);
				}
			}
		}
	}
	
	//事件侦听器类——main2面板中的submit按钮
	class Submit2ActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String answ;
			String new_pwd=new String(jpf_new_pwd.getPassword());
			String pwd_conf=new String(jpf_pwd_conf.getPassword());
			
			//判断密保答案是否正确
			ArrayList arr=MainClass.db.dbGet("select uans from users where uid="+jtf_id.getText());
			Iterator ait = arr.iterator();
			Map map = (Map)ait.next();
			if (jl_question.getText().equals("You don't need to answer")) {
				answ=String.valueOf(map.get("uans"));
			}else {
				answ=jtf_answ.getText();
			}
			if (!answ.equals(map.get("uans"))) {  //密保答案不正确，产生提示信息
				showwrong("The answer is wrong! Please input again.");
			}else if (!new_pwd.equals(pwd_conf)){  //密码与确认密码不同，产生提示信息
				showwrong("Confirm password is wrong! Please input again.");
			}else {
				//输入信息正确，加入数据库
				MainClass.db.dbUpdate("update users set upwd='"+new_pwd+"' where uid="+jtf_id.getText());
				showSuccess();
			}
		}
	}
	
	//构造函数
	public ForgetPwd() {
		drawMain();
		initMain1();
        initFrame();
        
        //加入事件侦听器
        jtf_name.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                }
        	}
        });
        jtf_id.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        			jb_submit1.doClick();
        		}
        	}
        });
        jtf_answ.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                }
        	}
        });
        jpf_new_pwd.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                }
        	}
        });
        jpf_pwd_conf.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        			jb_submit2.doClick();
        		}
        	}
        });
        jb_submit1.addActionListener(new Submit1ActionListener());
        jf.addWindowListener(new WindowAdapter() {});
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void drawMain() {
		jp_main1=new JPanel(){    
            public void paintComponent(Graphics g) {  
                Graphics2D g2=(Graphics2D)g;   
                super.paintComponents(g);  
                Image image = new ImageIcon("./source/PureColor.jpg").getImage(); 
                //Image image = new ImageIcon("F:\\文档\\大二下学期\\Java\\BookManager\\source\\PureColor.jpg").getImage();   //注意修改路径  
                g2.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);  
            }  
         }; 
         jp_main2=new JPanel(){    
             public void paintComponent(Graphics g) {  
                 Graphics2D g2=(Graphics2D)g;   
                 super.paintComponents(g);  
                 Image image = new ImageIcon("./source/PureColor.jpg").getImage(); 
                 //Image image = new ImageIcon("F:\\文档\\大二下学期\\Java\\BookManager\\source\\PureColor.jpg").getImage();   //注意修改路径  
                 g2.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);  
             }  
          }; 
	}
	
	//初始化Frame框架
	private void initFrame() {
		jf.setSize(width,height);
        //获得屏幕高度宽度，用来设置居中
        Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();  
        jf.setLocation((scr.width-jf.getWidth())/2,(scr.height-jf.getHeight())/2);   

        jf.setContentPane(jp_main1);
	 	jf.setVisible(true);
	 	jf.setResizable(false);
	 	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	//初始化main1面板
	private void initMain1() {
		jp_main1.setOpaque(false);
		jp_main1.setLayout(null);
		
		//设置各组件位置、字体
		jl_title1.setFont(new Font("consolas",Font.BOLD,18));
		jp_title1.setOpaque(false);
		jp_title1.setLayout(null);
		jl_title1.setBounds(120,35,260,30);
		jp_title1.setBounds(0,0,500,70);
		jp_title1.add(jl_title1);
		
		jl_name.setBounds(80, 80, 150, 30);
		jl_name.setFont(new Font("consolas",Font.PLAIN,16));
        jtf_name.setBounds(230,80,190,30);
        
        jl_id.setBounds(80, 120, 150, 30);
		jl_id.setFont(new Font("consolas",Font.PLAIN,16));
        jtf_id.setBounds(230,120,190,30);
        
        jb_submit1.setBounds(190, 200, 120, 30);
        jb_submit1.setFont(new Font("consolas",Font.PLAIN,19));
        
        //将组件加入面板中
        jp_main1.add(jp_title1);
        jp_main1.add(jl_name);
        jp_main1.add(jtf_name);
        jp_main1.add(jl_id);
        jp_main1.add(jtf_id);
        jp_main1.add(jb_submit1);
        
        jp_main1.setSize(width,height);
        jp_main1.setVisible(true);
	}
	
	//初始化main2面板
	private void initMain2() {
		jp_main2.setOpaque(false);
		jp_main2.setLayout(null);
		
		//设置各组件位置、字体
		jl_title2.setFont(new Font("consolas",Font.BOLD,19));		
		jl_title2.setBounds(180,20,200,30);
		jp_title2.setLayout(null);
		jp_title2.setBounds(0,0,500,70);
		jp_title2.setOpaque(false);
		jp_title2.add(jl_title2);
		
		jl_ques.setBounds(80, 60, 150, 30);
		jl_ques.setFont(new Font("consolas",Font.PLAIN,16));
		ArrayList arr=MainClass.db.dbGet("select uquestion from users where uid="+jtf_id.getText());
		Iterator ait = arr.iterator();
		Map map = (Map)ait.next();
		switch (Integer.parseInt(String.valueOf(map.get("uquestion")))) {
			case 1:
				jl_question=new JLabel("You don't need to answer");
				break;
			case 2:
				jl_question=new JLabel("the name of your mother");
				break;
			case 3:
				jl_question=new JLabel("the name of your father");
				break;
			case 4:
				jl_question=new JLabel("the name of your school");
				break;
			case 5:
				jl_question=new JLabel("the name of your company");
				break;
		}
		jl_question.setBounds(230,60,250,30);
		jl_question.setFont(new Font("consolas",Font.PLAIN,16));
		
		jl_answ.setBounds(80,100,150,30);
		jl_answ.setFont(new Font("consolas",Font.PLAIN,16));
		jtf_answ.setBounds(230,100,190,30);
		
		jl_new_pwd.setBounds(80,140,150,30);
        jl_new_pwd.setFont(new Font("consolas",Font.PLAIN,16));
        jpf_new_pwd.setBounds(230,140,190,30);
        
        jl_pwd_conf.setBounds(80,180,150,30);
        jl_pwd_conf.setFont(new Font("consolas",Font.PLAIN,16));
        jpf_pwd_conf.setBounds(230,180,190,30);
		
		jb_submit2.setBounds(190, 230, 120, 30);
	    jb_submit2.setFont(new Font("consolas",Font.PLAIN,19));
	    
	    //将组件加入面板中
	    jp_main2.add(jp_title2);
	    jp_main2.add(jl_ques);
	    jp_main2.add(jl_question);
	    jp_main2.add(jl_answ);
	    jp_main2.add(jtf_answ);
	    jp_main2.add(jl_new_pwd);
	    jp_main2.add(jpf_new_pwd);
	    jp_main2.add(jl_pwd_conf);
	    jp_main2.add(jpf_pwd_conf);
	    jp_main2.add(jb_submit2);
		
		jp_main2.setSize(width,height);
        jp_main2.setVisible(true);
        
        //加入事件侦听器
        jb_submit2.addActionListener(new Submit2ActionListener());
	}
}
