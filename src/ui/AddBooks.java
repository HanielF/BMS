package ui;

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

public class AddBooks {
	public JPanel jp_addBooks=new JPanel();
	private JLabel jl_name=new JLabel("Book Name");
	private JLabel jl_author=new JLabel("Book Author");
	private JLabel jl_price=new JLabel("Book Price");
	private JLabel jl_id=new JLabel("Book ID");
	private JLabel jl_num=new JLabel("Book Number");
	
	private JTextField jtf_name=new JTextField();
	private JTextField jtf_author=new JTextField();
	private JTextField jtf_price=new JTextField();
	private JTextField jtf_id=new JTextField();
	private JTextField jtf_num=new JTextField();
	
	private JButton jb_add=new JButton("Add Book");
	
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
				jtf_author.setText("");
				jtf_price.setText("");
				jtf_id.setText("");
				jtf_num.setText("");
				jd_success.dispose();
			}
		});
	}
	
	//add事件侦听器类
	class AddActionListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			String name=jtf_name.getText();
			String author=jtf_author.getText();
			String id=jtf_id.getText();
			float price=Float.parseFloat(jtf_price.getText());
			int num=Integer.parseInt(jtf_num.getText());
			
			if (name.length()==0 || author.length()==0 || id.length()==0) {
				showwrong("Input wrong! Please input again.");
			}else {
				//判断book ID是否已存在
				boolean flag=true;
				ArrayList arr=MainClass.db.dbGet("select bid from books");
				Iterator ait = arr.iterator();
				while(ait.hasNext()) {
					Map map = (Map)ait.next();
					if (id.equals(map.get("bid"))) {
						flag=false;
						break;
					}
				}
				
				if (!flag) {
					showwrong("Book ID has existed! Please input again.");
				}else if (price <= 0){
					showwrong("Price must be more than 0! Please input again.");
				}else if (num<1) {
					showwrong("Number must be more than 0! Please input again.");
				}else {
					MainClass.db.dbUpdate("insert into books values('"+name+"','"+author+"',"+price+",'"+id+"',"+num+")");
					showSuccess();
				}
			}
		}
	}
	
	public AddBooks() {
		jp_addBooks.setLayout(null);
		
		jl_name.setFont(new Font("consolas",Font.PLAIN,19));
		jl_name.setBounds(100,150,130,30);
		jtf_name.setBounds(225,150,300,30);
		jl_author.setFont(new Font("consolas",Font.PLAIN,19));
		jl_author.setBounds(100,190,130,30);
		jtf_author.setBounds(225,190,300,30);
		jl_price.setFont(new Font("consolas",Font.PLAIN,19));
		jl_price.setBounds(100,230,130,30);
		jtf_price.setBounds(225,230,300,30);
		jl_id.setFont(new Font("consolas",Font.PLAIN,19));
		jl_id.setBounds(100,270,130,30);
		jtf_id.setBounds(225,270,300,30);
		jl_num.setFont(new Font("consolas",Font.PLAIN,19));
		jl_num.setBounds(100,310,130,30);
		jtf_num.setBounds(225,310,300,30);
		jb_add.setFont(new Font("consolas",Font.PLAIN,19));
		jb_add.setBounds(265,380,120,30);
		
		jp_addBooks.add(jl_name);
		jp_addBooks.add(jl_author);
		jp_addBooks.add(jl_price);
		jp_addBooks.add(jl_id);
		jp_addBooks.add(jl_num);
		jp_addBooks.add(jtf_name);
		jp_addBooks.add(jtf_author);
		jp_addBooks.add(jtf_price);;
		jp_addBooks.add(jtf_id);
		jp_addBooks.add(jtf_num);
		jp_addBooks.add(jb_add);
		
		jtf_name.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                }
        	}
        });
		jtf_author.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    e.consume();
                    KeyboardFocusManager.getCurrentKeyboardFocusManager().focusNextComponent();
                }
        	}
        });
		jtf_price.addKeyListener(new KeyAdapter() {
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
		jtf_num.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    jb_add.doClick();
                }
        	}
        });
		jb_add.addActionListener(new AddActionListener());
		
		jp_addBooks.setVisible(true);
	}
}
