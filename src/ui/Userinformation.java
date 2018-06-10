package ui;

import javax.swing.*;
import java.awt.*;
import java.util.*;

public class Userinformation{
	private int width = MainPage.getWidth()-MainClass.mp.sbar.getWidth();
	private int height = MainPage.getHeight()-Banner.getHeight();
	
	public JPanel panel;
	private JPanel imagePanel=new JPanel() {
		 public void paint(Graphics g)
		    {
		        Toolkit tool = this.getToolkit();
		        //Image image = tool.getImage("F:\\文档\\GitHub\\BMS\\source\\headPicture.png");
		        Image image = tool.getImage("./source/headPicture.png");
		        g.drawImage(image, 0, 0, 100, 100, this);
	     }
	};
	
	//构造函数 
	public Userinformation() {
		panel = new JPanel() {
            protected void paintComponent(Graphics g) {    
                ImageIcon icon = new ImageIcon("./source/background4.jpg");    
                Image img = icon.getImage();    
                g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), icon.getImageObserver());    
            }    
		};
		imagePanel.setBackground(null);
		imagePanel.setOpaque(false);
		initLabel();	
		panel.setBackground(new Color(255,251,240));
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
	private JLabel HAVE_BORROWED;
	private JLabel HAVEBORROWEDtext;
	
	void initLabel() {
		panel.setLayout(null);
		
		imagePanel.setBounds((width-100)/2,50,100,100);
		panel.add(imagePanel);
		
		int startWidth=(width-300)/2+20;
		int startHeight=(height-190)/2;
        
		ID=new JLabel("User ID:");
		ID.setFont(new Font("consolas",Font.PLAIN,19));
		ID.setBounds(startWidth,startHeight,150,30);
		panel.add(ID);
		IDtext=new JLabel(MainClass.db.getId());
		IDtext.setFont(new Font("consolas",Font.PLAIN,19));
		IDtext.setBounds(startWidth+180,startHeight,150,30);
		panel.add(IDtext);
		
		NAME=new JLabel("User Name:");
		NAME.setFont(new Font("consolas",Font.PLAIN,19));
		NAME.setBounds(startWidth,startHeight+40,150,30);
		panel.add(NAME);
		NAMEtext=new JLabel(MainClass.db.getName());
		NAMEtext.setFont(new Font("consolas",Font.PLAIN,19));
		NAMEtext.setBounds(startWidth+180,startHeight+40,150,30);
		panel.add(NAMEtext);
		
		IS_MANAGER=new JLabel("Is Manager:");
		IS_MANAGER.setFont(new Font("consolas",Font.PLAIN,19));
		IS_MANAGER.setBounds(startWidth,startHeight+80,150,30);
		panel.add(IS_MANAGER);
		if(MainClass.db.getIs_manager()==1)
			ISMANAGERtext=new JLabel("Yes");
		else
			ISMANAGERtext=new JLabel("No");
		ISMANAGERtext.setFont(new Font("consolas",Font.PLAIN,19));
		ISMANAGERtext.setBounds(startWidth+180,startHeight+80,150,30);
		panel.add(ISMANAGERtext);
		
		MAX_NUM=new JLabel("Max Number:");
		MAX_NUM.setFont(new Font("consolas",Font.PLAIN,19));
		MAX_NUM.setBounds(startWidth,startHeight+120,150,30);
		panel.add(MAX_NUM);
		MAXNUMtext=new JLabel("8");
		MAXNUMtext.setFont(new Font("consolas",Font.PLAIN,19));
		MAXNUMtext.setBounds(startWidth+180,startHeight+120,150,30);
		panel.add(MAXNUMtext);
		
		HAVE_BORROWED=new JLabel("Borrow Number:");
		HAVE_BORROWED.setFont(new Font("consolas",Font.PLAIN,19));
		String num = "0";
		ArrayList count = MainClass.db.dbGet("select count(bid) as number from borrow where uid="+MainClass.db.getId());
		Iterator ait = count.iterator();
		while(ait.hasNext()) {
			Map map = (Map)ait.next();
			num=String.valueOf(map.get("number"));
		}
		HAVE_BORROWED.setBounds(startWidth,startHeight+160,150,30);
		panel.add(HAVE_BORROWED);
		HAVEBORROWEDtext=new JLabel(num);
		HAVEBORROWEDtext.setFont(new Font("consolas",Font.PLAIN,19));
		HAVEBORROWEDtext.setBounds(startWidth+180,startHeight+160,150,30);
		panel.add(HAVEBORROWEDtext);
		
		//panel.setOpaque(false);
		panel.setVisible(true);
	}
}
