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
	private JScrollPane scrollPane=new JScrollPane();
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public Sidebar() {
		String[] s1= {"所有书籍","添加书籍","查询书籍","图书借阅","图书归还"};//图书管理，在所有书籍那里设置删除和修改功能
		String[] s2= {"个人资料"};//用户管理可以在个人资料那里更新信息
		String[] s3= {"所有用户","添加用户","用户查询"};//在所有用户哪里添加删除和修改以及设置管理员的功能，管理才有权限打开所有用户页面和添加用户
		
		Hashtable hstable1=new Hashtable();
		hstable1.put("图书管理", s1);
		hstable1.put("管理员信息", s2);
		hstable1.put("用户管理", s3);
		
		tree = new JTree(hstable1);
		getScrollPane().setPreferredSize(new Dimension(150,400));
		getScrollPane().setViewportView(tree);
		tree.addTreeSelectionListener(this);
		//tree.putClientProperty(“Jtree.lineStyle”,  “None”);  
	}
	
	//监听接口函数实现
	public void valueChanged(TreeSelectionEvent e) {
		//Returns the last path element of the selection.  
		DefaultMutableTreeNode node = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent(); 
		
		if(node==null) 
			return;
		if(node.isLeaf())//叶子节点的监听
			   System.out.println(node.toString());
		
	}

	public JScrollPane getScrollPane() {
		return scrollPane;
	}
	
}
