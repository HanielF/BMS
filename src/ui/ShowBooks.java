package ui;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ShowBooks {
	private JTable jt;
	public JScrollPane jsp;
<<<<<<< HEAD
	
	private int width = MainPage.getWidth()-MainClass.mp.sbar.getWidth();
	private int height = MainPage.getHeight()-Banner.getHeight();
	
=======
>>>>>>> dev
	private Vector rowData,columnNames;	
	private ResultSet rs=null;
	
	public ShowBooks() {
		rowData = new Vector();
		columnNames = new Vector();
		ArrayList arr = new ArrayList();

		columnNames.add("Title");
		columnNames.add("Author");
		columnNames.add("Price");
		columnNames.add("ID");
		columnNames.add("Number");
<<<<<<< HEAD
		
		//迭代获取map中值
		arr=MainClass.db.dbGet("select * from books");
		try {
			Iterator ait = arr.iterator();
			while(ait.hasNext()) {
				Map map = (Map)ait.next();
				Vector tmp = new Vector();

				tmp.add(map.get("bname"));
				tmp.add(map.get("author"));
				tmp.add(map.get("price"));
				tmp.add(map.get("bid"));
				tmp.add(map.get("cnt"));
				
				rowData.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//init jt and jsp
		jt = new JTable(rowData,columnNames);
		jt.setFont(new Font("consolas",Font.PLAIN,18));
		jt.getTableHeader().setPreferredSize(new Dimension(width,27));;
		jt.getTableHeader().setFont(new Font("consolas",Font.PLAIN,19));
		jt.setRowHeight(27);
		
		jsp = new JScrollPane(jt);
		jsp.setPreferredSize(new Dimension(width,height));
		jsp.setVisible(true);
=======
		
		//迭代获取map中值
		arr=MainClass.db.dbGet("select * from books");
		try {
			Iterator ait = arr.iterator();
			while(ait.hasNext()) {
				Map map = (Map)ait.next();
				Iterator iter = map.entrySet().iterator();
				Vector tmp = new Vector();
				while(iter.hasNext()) {
					Map.Entry entry = (Map.Entry) iter.next();
					Object val = entry.getValue();
					tmp.add(val.toString());
				}
				rowData.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		//init jt and jsp
		jt = new JTable(rowData,columnNames);
		jsp = new JScrollPane(jt);
		
		jsp.setVisible(true);
		//jsp.setPreferredSize(new Dimension(MainClass.mp.getWidth()-130,MainClass.mp.getHeight()));
>>>>>>> dev
	}

}
