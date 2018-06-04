package ui;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class ShowBooks {
	private JLabel jl_hint;
	private JComboBox<String> jcb;
	private JTextField jtf;
	private JButton jb_search;
	
	private JTable jt;
	private JScrollPane jsp;
	public JPanel jp;//最外层
	private JPanel jp_top;
	
	private int width = MainPage.getWidth()-MainClass.mp.sbar.getWidth();
	private int height = MainPage.getHeight()-Banner.getHeight();
	private int top_height = 60;
	
	private Vector rowData,columnNames;	
	
	public ShowBooks() {
		initVec();
		initTable();
		jsp = new JScrollPane(jt);
		jsp.setPreferredSize(new Dimension(width,height-top_height));
		jsp.setVisible(true);
        jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jsp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		
		initTop();
		jp_top = new JPanel();
		jp_top.setLayout(null);
		jp_top.setPreferredSize(new Dimension(width,top_height));
		jp_top.setVisible(true);
		jp_top.add(jl_hint);
		jp_top.add(jcb);
		jp_top.add(jtf);
		jp_top.add(jb_search);
		
		jp = new JPanel(new BorderLayout());
		jp.setPreferredSize(new Dimension(width,height));
		jp.setVisible(true);
		jp.add(jp_top, BorderLayout.NORTH);
		jp.add(jsp,BorderLayout.CENTER);
		
		addListener();
	}
	
	public void initVec() {
		rowData = new Vector();
		columnNames = new Vector();
		columnNames.add("Book Name");
		columnNames.add("Author");
		columnNames.add("Price");
		columnNames.add("ID");
		columnNames.add("Number");
	}
	
	//调用setTable()
	public void initTable() {
		//init jt and jsp
		setTable("select * from books;");
		jt = new JTable(rowData,columnNames);
		jt.setFont(new Font("consolas",Font.PLAIN,18));
		jt.getTableHeader().setPreferredSize(new Dimension(width,27));
		jt.getTableHeader().setFont(new Font("consolas",Font.PLAIN,19));
		jt.setRowHeight(27);
		jt.setLocation(0,0);
		jt.setVisible(true);
		setTableColumnCenter(jt);
	}
	
	//设置表格内容居中
	public void setTableColumnCenter(JTable table){  
	    DefaultTableCellRenderer r = new DefaultTableCellRenderer();     
	    r.setHorizontalAlignment(JLabel.CENTER);     
	    table.setDefaultRenderer(Object.class, r);  
	}
	
	public void setTable(String query) {
		if(!rowData.isEmpty()) rowData.clear();
		ArrayList arr = new ArrayList();

		//迭代获取map中值
		arr=MainClass.db.dbGet(query);
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
	}

	public void updatePanel(String query) {
		setTable(query);
		jt.validate();
		jt.updateUI();
		jsp.validate();
		jsp.updateUI();
	}
	
	public void initTop() {
		jl_hint = new JLabel("查询方式");
		jl_hint.setSize(80,30);
		jl_hint.setLocation(80,top_height/2-jl_hint.getHeight()/2);
		jl_hint.setFont(new Font("consolas",Font.PLAIN,19));
		jl_hint.setVisible(true);
		
		String[] choice = {"全部","书名","ID","作者"};
		jcb = new JComboBox<String>(choice);
		jcb.setSelectedIndex(0);
		jcb.setEditable(false);
		jcb.setSize(100,30);
		jcb.setFont(new Font("consolas",Font.PLAIN,19));
		jcb.setLocation(180,top_height/2-jcb.getHeight()/2);
		jcb.setVisible(true);
		
		jtf = new JTextField();
		jtf.setSize(140,30);
		jtf.setLocation(300,top_height/2-jtf.getHeight()/2);
		jtf.setFont(new Font("consolas",Font.PLAIN,19));
		jtf.setVisible(true);
		
		jb_search = new JButton("查询书籍");
		jb_search.setSize(120,30);
		jb_search.setLocation(460,top_height/2-jb_search.getHeight()/2);
		jb_search.setFont(new Font("consolas",Font.PLAIN,19));
		jb_search.setVisible(true);
	}
	
	public void addListener() {
		jb_search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String mode = (String)jcb.getSelectedItem();
				String query = null;
				switch(mode) {
				case "全部":
					query = "select * from books;";
					break;
				case "书名":
					query = "select * from books where bname='"+jtf.getText()+"';";
					break;
				case "ID" :
					query = "select * from books where bid='"+jtf.getText()+"';";
					break;
				case "作者":
					query = "select * from books where author='"+jtf.getText()+"';";
					break;
				default:
					query = "select * from books;";
				}
				updatePanel(query);
			}
		});
		
        jtf.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        			jb_search.doClick();
        		}
        	}
        });
	}
}
