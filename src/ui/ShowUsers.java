package ui;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ShowUsers {
	private JTable jt;
	public JScrollPane jsp;
	
	private int width = MainPage.getWidth()-MainClass.mp.sbar.getWidth();
	private int height = MainPage.getHeight()-Banner.getHeight();
	
	private Vector rowData,columnNames;	
	private ResultSet rs=null;
	
	public ShowUsers() {
		rowData = new Vector();
		columnNames = new Vector();
		ArrayList arr = new ArrayList();

		columnNames.add("Name");
		columnNames.add("ID");
		columnNames.add("IsManager");
		
		//从键值获取map中值
		arr=MainClass.db.dbGet("select uname,uid,upwd,ismanager from users");
		try {
			Iterator ait = arr.iterator();
			while(ait.hasNext()) {
				Map map = (Map)ait.next();
				Vector tmp = new Vector();

				tmp.add(map.get("uname"));
				tmp.add(map.get("uid"));
				if(Integer.parseInt(String.valueOf(map.get("ismanager")))==1)
					tmp.add("Yes");
				else
					tmp.add("No");
				
				rowData.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//init jt and jsp
		jt = new JTable(rowData,columnNames);
		jt.setFont(new Font("consolas",Font.PLAIN,18));
		jt.getTableHeader().setFont(new Font("consolas",Font.PLAIN,19));
		jt.setRowHeight(25);
		
		jsp = new JScrollPane(jt);		
		jsp.setPreferredSize(new Dimension(width,height));
		jsp.setVisible(true);
	}
}