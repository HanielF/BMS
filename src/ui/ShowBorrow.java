package ui;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class ShowBorrow {
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
	
	public ShowBorrow() {
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
		columnNames.add("User ID");
		columnNames.add("Book Name");
		columnNames.add("Book ID");
		columnNames.add("Time");
		}
	
	//调用setTable()
	public void initTable() {
		//init jt and jsp
		String query;
		if(MainClass.db.getIs_manager()==1) {
			query="select users.uid,books.bname,books.bid,borrow.time from users,books,borrow where users.uid=borrow.uid and books.bid=borrow.bid;";
		}
		else {
			query="select users.uid,books.bname,books.bid,borrow.time from users,books,borrow where users.uid=borrow.uid and books.bid=borrow.bid and borrow.uid='"+MainClass.db.getId()+"';";
		}
		setTable(query);
		jt = new JTable(rowData,columnNames);
		jt.getTableHeader().setPreferredSize(new Dimension(width,27));
		jt.getTableHeader().setFont(new Font("consolas",Font.PLAIN,19));
		jt.setRowHeight(27);
		jt.setLocation(0,0);
		jt.setVisible(true);
		jt.setEnabled(false);
		
		jt.getColumnModel().getColumn(3).setPreferredWidth(185);
		setTableColumnCenter(jt);
	}
	
	//设置表格内容居中及字体
	public void setTableColumnCenter(JTable table){  
		DefaultTableCellRenderer r = new DefaultTableCellRenderer() {
	    	public Component getTableCellRendererComponent(JTable table,Object value,
	                boolean isSelected,boolean hasFocus,int row,int column){          
	    		JLabel jl=new JLabel();
	    		String str=value.toString();
	    		if (value!=null) {
	    			jl.setText(str);
	    			if (isChinese(str)) {
	    				jl.setFont(new Font("楷体",Font.BOLD,18));
	    			}
	    			else {
	    				jl.setFont(new Font("consolas",Font.PLAIN,18));
	    			}
	    			jl.setHorizontalAlignment(SwingConstants.CENTER);
	    		}
				return jl;   
			}
	    };     
	    table.setDefaultRenderer(Object.class, r);  
	}
	
	//判断字符串中是否有中文
	private boolean isChinese(char c) {  
        Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);  
        if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS  
                || ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A  
                || ub == Character.UnicodeBlock.GENERAL_PUNCTUATION  
                || ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION  
                || ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {  
            return true;  
        }  
        return false;  
    }
    private boolean isChinese(String strName) {  
        char[] ch = strName.toCharArray();  
        for (int i = 0; i < ch.length; i++) {  
            char c = ch[i];  
            if (isChinese(c)) {  
                return true;  
            }  
        }  
        return false;  
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

				//tmp.add(map.get("uname"));
				tmp.add(map.get("uid"));
				tmp.add(map.get("bname"));
				tmp.add(map.get("bid"));
				tmp.add(map.get("time"));
				rowData.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void initTop() {
		jl_hint = new JLabel("Search Way:");
		jl_hint.setSize(120,30);
		jl_hint.setLocation(60,top_height/2-jl_hint.getHeight()/2);
		jl_hint.setFont(new Font("consolas",Font.PLAIN,19));
		jl_hint.setVisible(true);
		
		if(MainClass.db.getIs_manager()==1) {
				String[] choice = {"All","User Name","User ID","Book Name","Book ID","Date"};
				jcb = new JComboBox<String>(choice);
		}else {
			String[] choice = {"All","Book Name","Book ID","Date"};
			jcb = new JComboBox<String>(choice);
		}
		
		jcb.setSelectedIndex(0);
		jcb.setEditable(false);
		jcb.setSize(120,30);
		jcb.setFont(new Font("consolas",Font.PLAIN,19));
		jcb.setLocation(180,top_height/2-jcb.getHeight()/2);
		jcb.setVisible(true);
		
		jtf = new JTextField();
		jtf.setSize(140,30);
		jtf.setLocation(320,top_height/2-jtf.getHeight()/2);
		jtf.setVisible(true);
		jtf.setEditable(false);
		
		jb_search = new JButton("Search");
		jb_search.setSize(100,30);
		jb_search.setLocation(480,top_height/2-jb_search.getHeight()/2);
		jb_search.setFont(new Font("consolas",Font.PLAIN,19));
		jb_search.setVisible(true);
	}
	
	public void updatePanel(String query) {
		setTable(query);
		jt.validate();
		jt.updateUI();
		jsp.validate();
		jsp.updateUI();
	}
	
	public void showNoInputError() {
		final JDialog jd = new JDialog(MainClass.mp.jf);
		jd.setTitle("Input Error");
		jd.setSize(350,160);
        Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();  
        jd.setLocation((scr.width-jd.getWidth())/2,(scr.height-jd.getHeight())/2);  
        jd.setVisible(true);
        jd.setLayout(null);
        
        JLabel jl = new JLabel("Please input some information!",JLabel.CENTER);
        JButton jb = new JButton("Conform");
        
        jb.setSize(120,30);
        jb.setLocation(jd.getWidth()/2-jb.getWidth()/2,80);
        jl.setSize(300,30);
        jl.setLocation(jd.getWidth()/2-jl.getWidth()/2, 30);
        
        jl.setFont(new Font("consolas",Font.PLAIN,19));
        jb.setFont(new Font("consolas",Font.PLAIN,19));
        
        jd.add(jb);
        jd.add(jl);
        
        jb.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		jd.dispose();
        	}
        });
	}
	
	public void addListener() {
		jb_search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String mode = (String)jcb.getSelectedItem();
				StringBuffer query=new StringBuffer("select users.uid,books.bname,books.bid,borrow.time from users,books,borrow where users.uid=borrow.uid and books.bid=borrow.bid ");
				if(MainClass.db.getIs_manager()==0) {
					query.append("and borrow.uid='"+MainClass.db.getId()+"' ");
				}
				if(!mode.equals("All") && jtf.getText().equals("")) {
					showNoInputError();
				}
				else {
					switch(mode) {
					case "All":
						break;
					case "User Name":
						query.append("and users.uname='"+jtf.getText()+"';");
						break;
					case "User ID":
						query.append("and borrow.uid='"+jtf.getText()+"';");
						break;
					case "Book Name":
						query.append("and books.bname='"+jtf.getText()+"';");
						break;
					case "Book ID" :
						query.append("and borrow.bid='"+jtf.getText()+"';");
						break;
					case "Date":
						query.append("and borrow.time='"+jtf.getText()+"';");
						break;
					}
					updatePanel(query.toString());
				}
			}
		});
		
        jtf.addKeyListener(new KeyAdapter() {
        	public void keyPressed(KeyEvent e) {
        		if (e.getKeyCode() == KeyEvent.VK_ENTER) {
        			jb_search.doClick();
        		}
        	}
        });
        
        jtf.getDocument().addDocumentListener(new DocumentListener() {
        	public void insertUpdate(DocumentEvent e) {
        		if (isChinese(jtf.getText())) {
        			jtf.setFont(new Font("楷体",Font.BOLD,19));
        		}
        		else {
        			jtf.setFont(new Font("consolas",Font.PLAIN,19));
        		}
        	}
			public void changedUpdate(DocumentEvent arg0) {}
			public void removeUpdate(DocumentEvent arg0) {}
        });
        
        jcb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (e.getSource()==jcb) {
					int index = jcb.getSelectedIndex();
					switch (index) {
					case 0:
						jtf.setEditable(false);
						break;
					case 1:
						jtf.setEditable(true);
						break;
					case 2:
						jtf.setEditable(true);
						break;
					case 3:
						jtf.setEditable(true);
						break;
					case 4:
						jtf.setEditable(true);
						break;
					case 5:
						jtf.setEditable(true);
						break;
					}
				}
			}
		});
	}
	
	
}
