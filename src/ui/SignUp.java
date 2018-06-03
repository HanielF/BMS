package ui;

import javax.swing.*;

import jdbc.Database;
import ui.SignUp.SubmitActionListener;

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

public class SignUp {
	public static JFrame jf=new JFrame("Sign Up Page");
	private JPanel jp_main;
	
	private JLabel jl_title=new JLabel("Please Input Your Information");
	private JLabel jl_name=new JLabel("Name");
	private JLabel jl_id=new JLabel("ID");
	private JLabel jl_pwd=new JLabel("Input Password");
	private JLabel jl_pwd_conf=new JLabel("Confirm Password");
	private JLabel jl_ques=new JLabel("Password Question");
	private JLabel jl_answ=new JLabel("Question Answer");
	
	private JTextField jtf_name=new JTextField();
	private JTextField jtf_id=new JTextField();
	private JPasswordField jpf_pwd=new JPasswordField();
	private JPasswordField jpf_pwd_conf=new JPasswordField();
	private Choice ch_ques=new Choice();
	private JTextField jtf_answ=new JTextField();

	private JButton jb_submit=new JButton("Submit");
	
	private int width=500;
	private int height=450;
	
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
				SignUp.jf.dispose();
			}
		});
	}
	
	//事件侦听器类
	class SubmitActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			//获取用户填入的信息
			String name=jtf_name.getText();
			String id=jtf_id.getText();
			String pwd=new String(jpf_pwd.getPassword());
			String pwd_conf=new String(jpf_pwd_conf.getPassword());
			int ques=ch_ques.getSelectedIndex()+1;
			String answ=jtf_answ.getText();
			int aut=0;
			
			//判断ID是否与已存在ID重复
			boolean repeatflag=false;
			ArrayList arr=MainClass.db.dbGet("select uid from users");
			Iterator ait = arr.iterator();
			while (ait.hasNext()) {
				Map map=(Map)ait.next();
				if (id.equals(map.get("uid"))) {
					repeatflag=true;
					break;
				}
			}
			
			
			if (name.length()==0 || id.length()==0 || pwd.length()==0 || pwd_conf.length()==0) {  //有一项为空，产生提示信息
				showwrong("Something is NULL! Please input again.");
			}
			else if (!pwd.equals(pwd_conf)) {  //密码与确认密码不同，产生提示信息
				showwrong("Password doesn't equal to confirming password! Please input again.");
			}
			else if (repeatflag){  //ID已存在，产生提示信息
				showwrong("The id has existed! Please input again.");
			}
			else {
				//信息输入正确，加入数据库
				MainClass.db.dbUpdate("insert into users values('"+name+"','"+id+"','"+pwd+"',"+aut+","+ques+",'"+answ+"')");
				showSuccess();
			}
		}

	}
	
	public SignUp() {
		drawMain();
		initMain();
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
        		if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                }
        	}
        });
        jpf_pwd.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                }
        	}
        });
        jpf_pwd_conf.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                }
        	}
        });
        ch_ques.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                }
        	}
        });
        jtf_answ.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER){
        			jb_submit.doClick();
                }
        	}
        });
        jb_submit.addActionListener(new SubmitActionListener());
        jf.addWindowListener(new WindowAdapter() {});
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
	
	private void drawMain() {
		jp_main=new JPanel(){    
            public void paintComponent(Graphics g) {  
                Graphics2D g2=(Graphics2D)g;   
                super.paintComponents(g);  
                Image image = new ImageIcon("F:\\文档\\大二下学期\\Java\\BookManager\\source\\PureColor.jpg").getImage();   //注意修改路径  
                g2.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);  
            }  
         }; 
	}
	
	private void initFrame() {
		jf.setSize(width,height);
        //获得屏幕高度宽度，用来设置居中
        Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();  
        jf.setLocation((scr.width-jf.getWidth())/2,(scr.height-jf.getHeight())/2);   

        jf.setContentPane(jp_main);
	 	jf.setVisible(true);
	 	jf.setResizable(false);
	 	jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void initMain() {
		jp_main.setOpaque(false);
		jp_main.setLayout(null);
		
		//设置各组件位置、字体
		jl_title.setBounds(100, 20, 300, 40);
		jl_title.setFont(new Font("consolas",Font.PLAIN,16));
		
		jl_name.setBounds(80, 60, 150, 30);
		jl_name.setFont(new Font("consolas",Font.PLAIN,16));
        jtf_name.setBounds(230,60,190,30);
        
        jl_id.setBounds(80, 100, 150, 30);
		jl_id.setFont(new Font("consolas",Font.PLAIN,16));
        jtf_id.setBounds(230,100,190,30);
        
        jl_pwd.setBounds(80,140,150,30);
        jl_pwd.setFont(new Font("consolas",Font.PLAIN,16));
        jpf_pwd.setBounds(230,140,190,30);
        
        jl_pwd_conf.setBounds(80,180,150,30);
        jl_pwd_conf.setFont(new Font("consolas",Font.PLAIN,16));
        jpf_pwd_conf.setBounds(230,180,190,30);
        
        jl_ques.setBounds(80, 220, 150, 30);
        jl_ques.setFont(new Font("consolas",Font.PLAIN,16));
        ch_ques.setBounds(230,220,190,30);
        ch_ques.setFont(new Font("consolas",Font.PLAIN,16));
        ch_ques.add("NULL");
        ch_ques.add("the name of your mother");
        ch_ques.add("the name of your father");
        ch_ques.add("the name of your school");
        ch_ques.add("the name of your company");
        
        jl_answ.setBounds(80,260,150,30);
        jl_answ.setFont(new Font("consolas",Font.PLAIN,16));
        jtf_answ.setBounds(230,260,190,30);
        
        jb_submit.setBounds(190, 310, 120, 30);
        jb_submit.setFont(new Font("consolas",Font.PLAIN,19));
        
        //将组件加入面板中
        jp_main.add(jl_title);
        jp_main.add(jl_name);
        jp_main.add(jl_id);
        jp_main.add(jl_pwd);
        jp_main.add(jl_pwd_conf);
        jp_main.add(jl_ques);
        jp_main.add(jl_answ);
        jp_main.add(jtf_id);
        jp_main.add(jtf_name);
        jp_main.add(jpf_pwd);
        jp_main.add(jpf_pwd_conf);
        jp_main.add(ch_ques);
        jp_main.add(jtf_answ);
        //jp_main.add(ch_aut);
        jp_main.add(jb_submit);
        
        jp_main.setSize(width,height);
        jp_main.setVisible(true);
	}
}
