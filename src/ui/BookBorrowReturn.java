package ui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;

import javax.swing.*;

public class BookBorrowReturn {
	public JPanel jp_bor_ret=new JPanel();
	public JPanel jp_comp=new JPanel();
	private JButton jb_borrow=new JButton("Borrow");
	private JButton jb_return=new JButton("Return");
	
	private JLabel jl_uid=new JLabel("User ID");
	private JLabel jl_bid=new JLabel("Book ID");
	
	private JTextField jtf_uid=new JTextField();
	private JTextField jtf_bid=new JTextField();
	
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
		jl_wrong.setFont(new Font("Lucida Family",Font.PLAIN,15));
		JPanel jp_wrong=new JPanel();
		jp_wrong.setBounds(0,20,500,30);
		jp_wrong.add(jl_wrong);
		con.add(jp_wrong);
		
		JButton jb_confirm=new JButton("Confirm");
		jb_confirm.setFont(new Font("Lucida Family",Font.PLAIN,15));
		jb_confirm.setBounds(200, 60, 100, 30);
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
		jl_success.setFont(new Font("Lucida Family",Font.PLAIN,15));
		JPanel jp_success=new JPanel();
		jp_success.setBounds(0,20,500,30);
		jp_success.add(jl_success);
		con.add(jp_success);
		
		JButton jb_confirm=new JButton("Confirm");
		jb_confirm.setFont(new Font("Lucida Family",Font.PLAIN,15));
		jb_confirm.setBounds(200, 60, 100, 30);
		con.add(jb_confirm);
		
		jb_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jd_success.dispose();
			}
		});
	}
	
	//borrow按键事件侦听器类
	class BorrowActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			String uid=jtf_uid.getText();
			String bid=jtf_bid.getText();
			
			//验证uid与bid是否存在
			boolean uidExistFlag=false;
			boolean bidExistFlag=false;
			ArrayList arr=MainClass.db.dbGet("select uid from users");
			Iterator ait = arr.iterator();
			while(ait.hasNext()) {
				Map map = (Map)ait.next();
				if (uid.equals(map.get("uid"))) {
					uidExistFlag=true;
					break;
				}
			}
			arr=MainClass.db.dbGet("select bid from books");
			ait=arr.iterator();
			while(ait.hasNext()) {
				Map map = (Map)ait.next();
				if (bid.equals(map.get("bid"))) {
					bidExistFlag=true;
					break;
				}
			}
			if (!uidExistFlag) {
				showwrong("User ID doesn't exist! Please input again.");
			}else if (!bidExistFlag) {
				showwrong("Book ID doesn't exist! Please input again.");
			}else {
				//查询书籍是否有剩余
				boolean remainFlag=false;
				arr=MainClass.db.dbGet("select cnt from books where bid="+bid);
				ait=arr.iterator();
				if (ait.hasNext()) {
					Map map = (Map)ait.next();
					if (Integer.parseInt(String.valueOf(map.get("cnt")))>=1) {
						remainFlag=true;
					}
				}
				if (!remainFlag) {
					showwrong("There is no remaining book! Please input again.");
				}else {
					//输入正确，加入信息
					MainClass.db.dbUpdate("update books set cnt=cnt-1 where bid="+bid);
					SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					MainClass.db.dbUpdate("insert into borrow values('"+uid+"','"+bid+"','"+df.format(new Date())+"')");
					showSuccess();
				}
			}
		}
	}
	
	//return按键的事件侦听器类
	class ReturnActionListener implements ActionListener{
		public void actionPerformed(ActionEvent arg0) {
			String uid=jtf_uid.getText();
			String bid=jtf_bid.getText();
			
			//验证uid与bid是否存在于borrow表中
			boolean flag=false;
			ArrayList arr=MainClass.db.dbGet("select uid,bid from borrow");
			Iterator ait = arr.iterator();
			while(ait.hasNext()) {
				Map map = (Map)ait.next();
				if (uid.equals(map.get("uid")) && bid.equals(map.get("bid"))) {
					flag=true;
					break;
				}
			}
			if (!flag) {
				showwrong("The information is wrong! Please input again.");
			}else {
				MainClass.db.dbUpdate("delete from borrow where uid="+uid+" and bid="+bid);
				MainClass.db.dbUpdate("update books set cnt=cnt+1 where bid="+bid);
				showSuccess();
			}
		}
	}
	
	public BookBorrowReturn() {
		jp_bor_ret.setLayout(null);
		
		jl_uid.setBounds(150,140,50,30);
		jtf_uid.setBounds(200,140,300,30);
		jl_bid.setBounds(150,180,50,30);
		jtf_bid.setBounds(200,180,300,30);
		jb_borrow.setBounds(200,220,100,30);
		jb_return.setBounds(350,220,100,30);
		
		jp_bor_ret.add(jl_uid);
		jp_bor_ret.add(jtf_uid);
		jp_bor_ret.add(jl_bid);
		jp_bor_ret.add(jtf_bid);
		jp_bor_ret.add(jb_borrow);
		jp_bor_ret.add(jb_return);
		
		jb_borrow.addActionListener(new BorrowActionListener());
		jb_return.addActionListener(new ReturnActionListener());
		
		jp_bor_ret.setVisible(true);
	}
}
