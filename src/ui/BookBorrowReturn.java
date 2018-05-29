package ui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class BookBorrowReturn {
	public static JPanel jp_bor_ret=new JPanel();
	private JButton jb_borrow=new JButton("Borrow");
	private JButton jb_return=new JButton("Return");
	
	private JLabel jl_uid=new JLabel("User ID");
	private JLabel jl_bid=new JLabel("Book ID");
	
	private JTextField jtf_uid=new JTextField();
	private JTextField jtf_bid=new JTextField();
	
	//borrow按键事件侦听器类
	class BorrowActionListener implements ActionListener {
		public void actionPerformed(ActionEvent arg0) {
			
		}
	}
	
	public BookBorrowReturn() {
		jp_bor_ret.setLayout(new FlowLayout(FlowLayout.CENTER));
		
		jp_bor_ret.add(jl_uid);
		jp_bor_ret.add(jtf_uid);
		jp_bor_ret.add(jl_bid);
		jp_bor_ret.add(jtf_bid);
		jp_bor_ret.add(jb_borrow);
		jp_bor_ret.add(jb_return);
		
		jb_borrow.addActionListener(new BorrowActionListener());
		
		jp_bor_ret.setVisible(true);
	}
}
