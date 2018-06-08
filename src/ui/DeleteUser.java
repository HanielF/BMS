package ui;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class DeleteUser {
	public JPanel jp=new JPanel(new BorderLayout());
	
	private JPanel jp_top=new JPanel(null);
	private JScrollPane jsp;
	private JTable jt;
	
	private JLabel jl_hint;
	private JComboBox<String> jcb;
	private JTextField jtf;
	private JButton jb_search;
	
	private Vector columnNames=new Vector();
	private Vector rowData=new Vector();
	
	private int width = MainPage.getWidth()-MainClass.mp.sbar.getWidth();
	private int height = MainPage.getHeight()-Banner.getHeight();
	private int top_height = 60;
	
	public DeleteUser() {
		initColumn();
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
		
		jp.setPreferredSize(new Dimension(width,height));
		jp.setVisible(true);
		jp.add(jp_top,BorderLayout.NORTH);
		jp.add(jsp,BorderLayout.CENTER);
		
		addListener();
	}
	
	
	//初始化列名向量
	private void initColumn() {
		columnNames.add("Name");
		columnNames.add("ID");
		columnNames.add("isManager");
		columnNames.add("Operation");
	}
	
	//初始化表格
	private void initTable() {
		setData("select uname,uid,ismanager from users");
		jt=new JTable(rowData,columnNames);
		jt.setFont(new Font("consolas",Font.PLAIN,18));
		jt.getTableHeader().setPreferredSize(new Dimension(width,27));
		jt.getTableHeader().setFont(new Font("consolas",Font.PLAIN,19));
		jt.setRowHeight(27);
		jt.setLocation(0,0);
		jt.setVisible(true);
		setTableColumnCenter(jt);
		setOperationColor(jt);
	}
	
	//初始化顶部panel
	public void initTop() {
		jl_hint = new JLabel("Search Way:");
		jl_hint.setSize(120,30);
		jl_hint.setLocation(60,top_height/2-jl_hint.getHeight()/2);
		jl_hint.setFont(new Font("consolas",Font.PLAIN,19));
		jl_hint.setVisible(true);
		
		String[] choice = {"All","Name","ID","Manager","Borrower"};
		jcb = new JComboBox<String>(choice);
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
	
	//设置数据向量rowData
	@SuppressWarnings("unchecked")
	private void setData(String query) {
		if(!rowData.isEmpty()) rowData.clear();
		ArrayList arr = new ArrayList();
		arr=MainClass.db.dbGet(query);
		try {
			Iterator ait = arr.iterator();
			while(ait.hasNext()) {
				Map map = (Map)ait.next();
				Vector tmp = new Vector();
	
				tmp.add(map.get("uname"));
				tmp.add(map.get("uid"));
				switch(Integer.parseInt(String.valueOf(map.get("ismanager")))) {
				case 0:
					tmp.add("No");
					break;
				case 1:
					tmp.add("Yes");
					break;
				default:
					tmp.add("No");
					break;
				}
				tmp.add("Delete");
				
				rowData.add(tmp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//设置表格内容居中以及字体
	private void setTableColumnCenter(JTable table){  
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
	
	//设置Operation列颜色
	private void setOperationColor(JTable table) {
		TableColumn tableColumn = table.getColumn("Operation");   
        DefaultTableCellRenderer cellRanderer = new DefaultTableCellRenderer(); 
        cellRanderer.setHorizontalAlignment(JLabel.CENTER);
        cellRanderer.setForeground(Color.RED);  
        tableColumn.setCellRenderer(cellRanderer);
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
	
	//更新面板
	private void updatePanel(String query) {
		setData(query);
		jt.validate();
		jt.updateUI();
		setOperationColor(jt);
		jsp.validate();
		jsp.updateUI();
	}
	
	//添加事件侦听器
	private void addListener() {
		jt.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row=jt.getSelectedRow();
				int column = jt.getSelectedColumn();
				if (column==3) {
					String id=String.valueOf(jt.getValueAt(row,1));
					showPrompt(id);
				}
			}
		});
		
		jb_search.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				String mode = (String)jcb.getSelectedItem();
				String query = null;
				switch(mode) {
				case "All":
					query = "select uname,uid,ismanager from users;";
					break;
				case "Name":
					if(jtf.getText().equals("")) {
						showNoInputError();
					}
					else {
						query = "select uname,uid,ismanager from users where uname='"+jtf.getText()+"';";
					}
				case "ID" :
					if(jtf.getText().equals("")) {
						showNoInputError();
					}
					else {
						query = "select uname,uid,ismanager from users where uid='"+jtf.getText()+"';";
					}
					break;
				case "Manager":
					query = "select uname,uid,ismanager from users where ismanager='1';";
					break;
				case "Borrower":
					query = "select uname,uid,ismanager from users where ismanager='0';";
					break;
				default:
					query = "select uname,uid,ismanager from users;";
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
						jtf.setEditable(false);
						break;
					case 4:
						jtf.setEditable(false);
						break;
					}
				}
			}
			
		});
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
	
	//显示提示窗口
	private void showPrompt(final String id) {
		final JDialog jd_prompt=new JDialog(MainPage.jf,"Prompt");
		jd_prompt.setVisible(true);
		jd_prompt.setSize(700,150);
		Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();  
        jd_prompt.setLocation((scr.width-jd_prompt.getWidth())/2,(scr.height-jd_prompt.getHeight())/2);   
		Container con=jd_prompt.getContentPane();
		con.setLayout(null);
		
		JLabel jl_prompt=new JLabel("Are you sure to delete this user whose ID is "+id+"?");
		jl_prompt.setFont(new Font("consolas",Font.PLAIN,19));
		JPanel jp_prompt=new JPanel();
		jp_prompt.setBounds(0,20,700,30);
		jp_prompt.add(jl_prompt);
		con.add(jp_prompt);
		
		JButton jb_confirm=new JButton("Confirm");
		jb_confirm.setFont(new Font("consolas",Font.PLAIN,19));
		jb_confirm.setBounds((jd_prompt.getWidth()-120)/2, 60, 120, 30);
		con.add(jb_confirm);
		
		jb_confirm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//删除该用户
				MainClass.db.dbUpdate("delete from users where uid="+id);
				updatePanel("select uname,uid,ismanager from users;");
				jd_prompt.dispose();
			}
		});
	}
}
