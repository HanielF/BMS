package ui;

import java.awt.Choice;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.KeyboardFocusManager;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;

import ui.AddBooks.AddActionListener;

public class AddUser {
	public JPanel jp_addUser=new JPanel();
	
	private JLabel jl_name=new JLabel("User Name");
	private JLabel jl_id=new JLabel("User ID");
	private JLabel jl_pwd=new JLabel("Input Password");
	private JLabel jl_pwd_conf=new JLabel("Confirm Password");
	private JLabel jl_ques=new JLabel("Password Question");
	private JLabel jl_answ=new JLabel("Question Answer");
	private JLabel jl_aut=new JLabel("Authorization");
	
	private JTextField jtf_name=new JTextField();
	private JTextField jtf_id=new JTextField();
	private JPasswordField jpf_pwd=new JPasswordField();
	private JPasswordField jpf_pwd_conf=new JPasswordField();
	private Choice ch_ques=new Choice();
	private JTextField jtf_answ=new JTextField();
	private Choice ch_aut=new Choice();
	
	private JButton jb_addUser=new JButton("Add User");
	
	//显示输入信息有误窗口
	private void showwrong(String wronginf) {
		final JDialog jd_wrong=new JDialog(MainPage.jf,"Wrong!");
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
		final JDialog jd_success=new JDialog(MainPage.jf,"Success!");
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
				jtf_name.setText("");
				jtf_id.setText("");
				jpf_pwd.setText("");
				jpf_pwd_conf.setText("");
				jtf_answ.setText("");
				jd_success.dispose();
			}
		});
	}
	
	//Add按键的事件侦听器类
	class AddActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String name=jtf_name.getText();
			String id=jtf_id.getText();
			String pwd=new String(jpf_pwd.getPassword());
			String pwd_conf=new String(jpf_pwd_conf.getPassword());
			int ques=ch_ques.getSelectedIndex()+1;
			String answ=jtf_answ.getText();
			int aut=ch_aut.getSelectedIndex();
			
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
	
	public AddUser() {
		jp_addUser.setLayout(null);
		
		jl_name.setFont(new Font("consolas",Font.PLAIN,19));
		jl_name.setBounds(85,120,180,30);
		jtf_name.setBounds(265,120,300,30);
		
		jl_id.setFont(new Font("consolas",Font.PLAIN,19));
		jl_id.setBounds(85,160,180,30);
		jtf_id.setBounds(265,160,300,30);
		
		jl_pwd.setFont(new Font("consolas",Font.PLAIN,19));
		jl_pwd.setBounds(85,200,180,30);
		jpf_pwd.setBounds(265,200,300,30);
		
		jl_pwd_conf.setFont(new Font("consolas",Font.PLAIN,19));
		jl_pwd_conf.setBounds(85,240,180,30);
		jpf_pwd_conf.setBounds(265,240,300,30);
		
		jl_ques.setFont(new Font("consolas",Font.PLAIN,19));
		jl_ques.setBounds(85,280,180,30);
		ch_ques.setFont(new Font("consolas",Font.PLAIN,16));
		ch_ques.setBounds(265,280,300,30);
		ch_ques.add("NULL");
        ch_ques.add("the name of your mother");
        ch_ques.add("the name of your father");
        ch_ques.add("the name of your school");
        ch_ques.add("the name of your company");
        
		jl_answ.setFont(new Font("consolas",Font.PLAIN,19));
		jl_answ.setBounds(85,320,180,30);
		jtf_answ.setBounds(265,320,300,30);
		
		jl_aut.setFont(new Font("consolas",Font.PLAIN,19));
		jl_aut.setBounds(85,360,180,30);
		ch_aut.setFont(new Font("consolas",Font.PLAIN,16));
		ch_aut.setBounds(265,360,300,30);
		ch_aut.add("brower");
        ch_aut.add("manager");
        
		jb_addUser.setFont(new Font("consolas",Font.PLAIN,19));
		jb_addUser.setBounds(265,410,120,30);
		
		jp_addUser.add(jl_name);
		jp_addUser.add(jtf_name);
		jp_addUser.add(jl_id);
		jp_addUser.add(jtf_id);
		jp_addUser.add(jl_pwd);
		jp_addUser.add(jpf_pwd);
		jp_addUser.add(jl_pwd_conf);
		jp_addUser.add(jpf_pwd_conf);
		jp_addUser.add(jl_ques);
		jp_addUser.add(ch_ques);
		jp_addUser.add(jl_answ);
		jp_addUser.add(jtf_answ);
		jp_addUser.add(jl_aut);
		jp_addUser.add(ch_aut);
		jp_addUser.add(jb_addUser);
		
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
                    e.consume();
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                }
        	}
        });
		ch_aut.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER){
        			jb_addUser.doClick();
                }
        	}
        });
		
		jb_addUser.addActionListener(new AddActionListener());
		
		jp_addUser.setVisible(true);
	}
}
