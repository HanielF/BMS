package ui;
/*
 * 侧边栏，用JTree
 */
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;
import java.util.*;


public class Sidebar implements TreeSelectionListener {
	private JTree tree;
	public JScrollPane scrollPane=new JScrollPane();
	private int width = 150;
	private int height = MainPage.getHeight()-Banner.getHeight();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Sidebar() {	
		String[] s1;
		String[] s3;
		String[] s2= {"个人资料"};//用户管理可以在个人资料那里更新信息
		Hashtable hstable1=new Hashtable();
		if(MainClass.db.getIs_manager()==1) {
			//图书管理，在所有书籍那里设置删除和修改功能
			s1= new String[5];
			s1[0]="所有书籍";
			s1[1]="添加书籍";
			s1[2]="所有借阅";
			s1[3]="图书归还";
			s1[4]="图书借阅";
			
			//在所有用户哪里添加删除和修改以及设置管理员的功能，管理才有权限打开所有用户页面和添加用户
			s3= new String[3];
			s3[0]="所有用户";
			s3[1]="添加用户";
			s3[2]="删除用户";
			hstable1.put("图书管理", s1);
			hstable1.put("管理员信息", s2);
			hstable1.put("用户管理", s3);
		}else {
			s1= new String[3];
			s1[0]="所有书籍";
			s1[1]="我的借阅";
			s1[2]="图书借还";
			s3= new String[2];
			hstable1.put("图书管理", s1);
			hstable1.put("读者信息", s2);
		}
		
		tree = new JTree(hstable1);
		tree.setFont(new Font("楷体",Font.PLAIN,18));
		scrollPane.setPreferredSize(new Dimension(width,height));
		scrollPane.setViewportView(tree);		
		tree.setVisible(true);
		scrollPane.setVisible(true);
		
		//extend all nodes
		for(int i=0;i<tree.getRowCount();i++) {
			tree.expandRow(i);
		}
		
		tree.addTreeSelectionListener(this);
		//tree.putClientProperty(“Jtree.lineStyle”,  “None”);  
	}
	

	public void valueChanged(TreeSelectionEvent e) {
		//Returns the last path element of the selection.  
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent(); 
		
		if(node==null) 
			return;
		if(node.isLeaf()) {
			if(node.toString().equals("所有书籍")){	
				MainPage.cl.show(MainPage.jp,"sbs" );
			}
			else if(node.toString().equals("个人资料")) {
				MainPage.cl.show(MainPage.jp,"uinfo");
			}
			else if(node.toString().equals("所有用户")) {
				MainPage.cl.show(MainPage.jp, "sus");
			}
			else if(node.toString().equals("图书借还")||node.toString().equals("图书借阅")) {
				MainPage.cl.show(MainPage.jp, "bbr");
			}
			else if(node.toString().equals("添加书籍")) {
				MainPage.cl.show(MainPage.jp, "abs");
			}
			else if(node.toString().equals("添加用户")) {
				MainPage.cl.show(MainPage.jp, "aus");
			}
			else if(node.toString().equals("所有借阅")||node.toString().equals("我的借阅")) {
				MainPage.cl.show(MainPage.jp, "sbow");
			}
			else if(node.toString().equals("删除用户")) {
				MainPage.cl.show(MainPage.jp, "delu");
			}
			else if(node.toString().equals("图书归还")) {
				MainPage.cl.show(MainPage.jp, "bret");
			}
			else {
				MainPage.cl.show(MainPage.jp,"sbs");
			}
		}
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}
