package ui;
import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class ShowBooks {
	private JTable jt;
	private JLabel jl_hint;
	private JComboBox<String> jcb;
	private JTextField jtf;
	private JButton jb_search;
	public JPanel jp;
	private JPanel jp_top;
	private JScrollPane jsp;
	
	private int width = MainPage.getWidth()-MainClass.mp.sbar.getWidth();
	private int height = MainPage.getHeight()-Banner.getHeight();
	
	private Vector rowData,columnNames;	
	
	public ShowBooks() {
		rowData = new Vector();
		columnNames = new Vector();
		ArrayList arr = new ArrayList();

		columnNames.add("Title");
		columnNames.add("Author");
		columnNames.add("Price");
		columnNames.add("ID");
		columnNames.add("Number");

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
		
		jsp = new JScrollPane();
		jsp.setLayout(null);
		initTable();
		initTop();
		
		jsp.add(jt);
		jsp.setPreferredSize(new Dimension(width,height));
		jsp.setVisible(true);
	}
	
	public void initTable() {
		//init jt and jsp
		jt = new JTable(rowData,columnNames);
		jt.setFont(new Font("consolas",Font.PLAIN,18));
		jt.getTableHeader().setPreferredSize(new Dimension(width,27));;
		jt.getTableHeader().setFont(new Font("consolas",Font.PLAIN,19));
		jt.setRowHeight(27);
		jt.setLocation(0,60);
		jt.setVisible(true);
	}

	public void initTop() {
		jl_hint = new JLabel();
		jl_hint.setText("查询方式");
		jl_hint.setPreferredSize(new Dimension(100,30));
		jl_hint.setLocation(100,15);
		jl_hint.setFont(new Font("consolas",Font.PLAIN,19));
		
		String[] choice = {"全部","书名","ID","作者"};
		jcb = new JComboBox<String>(choice);
		jcb.setSelectedIndex(0);
		jcb.setEditable(false);
		jcb.setPreferredSize(new Dimension(100,30));
		jcb.setFont(new Font("consolas",Font.PLAIN,19));
		jcb.setLocation(210,15);
		
		jtf = new JTextField();
		jtf.setPreferredSize(new Dimension(100,30));
		jtf.setLocation(330,15);
		jtf.setFont(new Font("consolas",Font.PLAIN,19));
		
		jb_search = new JButton("查询书籍");
		jb_search.setPreferredSize(new Dimension(120,30));
		jb_search.setLocation(600,15);
		jb_search.setFont(new Font("consolas",Font.PLAIN,19));
	}
}
