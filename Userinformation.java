package ui;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Userinformation{
	private String name;
	private String id;
	private int is_manager;
	public JPanel panel=new JPanel() {
		 public void paintComponent(Graphics g) {  
	         Graphics2D g2=(Graphics2D)g;   
	         super.paintComponents(g);  
	         Image image = new ImageIcon("C:\\Users\\Mr Xu\\eclipse-workspace\\图书管理\\src\\图书管理之用户信息\\PureColor.jpg").getImage();   //注意修改路径  
	         g2.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);  
	     }  
	};
	
	//构造函数 
	public Userinformation() {
		initLabel();	
	}
	
	//设置标签内容
	private JLabel ID;
	private JLabel IDtext;
	private JLabel NAME;
	private JLabel NAMEtext;
	private JLabel IS_MANAGER;
	private JLabel ISMANAGERtext;
	private JLabel MAX_NUM;
	private JLabel MAXNUMtext;
	private JLabel HAVE_BRROWED;
	private JLabel HAVEBORROWEDtext;
	void initLabel() {
		panel.setLayout(null);
        
		ID=new JLabel("学生学号:");
		ID.setBounds(80,0,100,50);
		panel.add(ID);
		IDtext=new JLabel(id);
		IDtext.setBounds(160,0,100, 50);
		panel.add(IDtext);
		
		NAME=new JLabel("学生姓名:");
		NAME.setBounds(80,100,100,50);
		panel.add(NAME);
		NAMEtext=new JLabel(name);
		NAMEtext.setBounds(160,100,100,50);
		panel.add(NAMEtext);
		
		IS_MANAGER=new JLabel("是否管理:");
		IS_MANAGER.setBounds(80,200,100,50);
		panel.add(IS_MANAGER);
		if(is_manager==1)
			ISMANAGERtext=new JLabel("是管理员");
		else
			ISMANAGERtext=new JLabel("非管理员");
		ISMANAGERtext.setBounds(160,200,100,50);
		panel.add(ISMANAGERtext);
		
		MAX_NUM=new JLabel("最大书目:");
		MAX_NUM.setBounds(80,300,100,50);
		panel.add(MAX_NUM);
		MAXNUMtext=new JLabel("8");
		MAXNUMtext.setBounds(160,300,100,50);
		panel.add(MAXNUMtext);
		
		HAVE_BRROWED=new JLabel("已借书目:");
		String num = "0";
		ArrayList count = MainClass.db.dbGet("select count(bid) as number from borrow where uid="+id);
		Iterator ait = count.iterator();
		while(ait.hasNext()) {
			Map map = (Map)ait.next();
			num=(String)map.get("number");
		}
		HAVE_BRROWED.setBounds(80,400,100,50);
		panel.add(HAVE_BRROWED);
		HAVEBORROWEDtext=new JLabel(num);
		HAVEBORROWEDtext.setBounds(160,400,100,50);
		panel.add(HAVEBORROWEDtext);
		panel.setOpaque(false);
		panel.setVisible(true);
	}
}
