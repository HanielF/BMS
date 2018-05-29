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
		String[] s1= {"所有书籍","添加书籍","查询书籍","图书借还"};//图书管理，在所有书籍那里设置删除和修改功能
		String[] s2= {"个人资料"};//用户管理可以在个人资料那里更新信息
		String[] s3= {"所有用户","添加用户","用户查询"};//在所有用户哪里添加删除和修改以及设置管理员的功能，管理才有权限打开所有用户页面和添加用户
		
		Hashtable hstable1=new Hashtable();
		hstable1.put("图书管理", s1);
		hstable1.put("管理员信息", s2);
		hstable1.put("用户管理", s3);
		tree = new JTree(hstable1);
		tree.setFont(new Font("楷体",Font.PLAIN,18));
		scrollPane.setPreferredSize(new Dimension(width,height));
		scrollPane.setViewportView(tree);
		
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
				MainPage.cl.show(MainPage.jp,"jb1");
			}
			else if(node.toString().equals("所有用户")) {
				MainPage.cl.show(MainPage.jp, "sus");
			}
			else if(node.toString().equals("图书借还")) {
				MainPage.cl.show(MainPage.jp, "bbr");
			}
			else {
				MainPage.cl.show(MainPage.jp,"jl1");
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
