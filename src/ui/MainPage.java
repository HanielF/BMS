package ui;
/*the mainpage of the bms,using borderlayout
 * include a sidebar in west which is a menu and welcome in north
 * the east part is null while the center is some information 
 * and operations that correspond to menu choice
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainPage {
	public static JFrame jf = new JFrame();
	private JPanel jp_main;//Frame的contentPane
	public  static CardLayout cl = new CardLayout();
	public  static JPanel jp= new JPanel(cl);//除了侧边栏的显示Panel
	
	private static int width = 800;
	private static int height = 600;
	
	public static Banner bn = new Banner();
	public static Sidebar sbar = new Sidebar();
	public static ShowBooks sbs = new ShowBooks();
	public static ShowUsers sus = new ShowUsers();
	public static DeleteUser delu=new DeleteUser();
	public static BookReturn bret = new BookReturn();
	public static BookBorrowReturn bbr=new BookBorrowReturn();
	public static AddBooks abs=new AddBooks();
	public static AddUser aus=new AddUser();
	public static Userinformation uinfo=new Userinformation();
	public static ShowBorrow sbow = new ShowBorrow();

	MainPage() {
		initJpMain();
		initJp();
		initJf();

	    jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void initJf() {
		//因为JFrame不可以直接添加，并且布局也是通过contentPane设置	
		jf.setContentPane(jp_main);
		
		//set the page in center of screen
	    Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();  
	    jf.setSize(width,height);
	    jf.setLocation(scr.width/2-jf.getWidth()/2,scr.height/2-jf.getHeight()/2);  

	    jf.setTitle("Book Manager System");
		jf.pack();
		jf.setVisible(false);
	}
	
	//整个Frame的panel初始化
	public void initJpMain() {
		/*jp_main=new JPanel(new BorderLayout()) {
            public void paintComponent(Graphics g) {  
                Graphics2D g2=(Graphics2D)g;   
                super.paintComponents(g);  
                Image image = new ImageIcon("./source/background.jpg").getImage(); 
                g2.drawImage(image,0,0,this.getWidth(),this.getHeight(),this);  
            }  
		};*/
		jp_main=new JPanel(new BorderLayout());
		jp_main.add(sbar.scrollPane,BorderLayout.WEST);
		jp_main.add(jp,BorderLayout.CENTER);
		jp_main.add(bn.jp,BorderLayout.NORTH);
        //jp_main.setOpaque(false);
	}
	
	//除了侧边栏的主页面
	public void initJp() {
		jp.setSize(width-sbar.getWidth(),height-Banner.getHeight());
		jp.add("sbs",sbs.jp);
		jp.add("sus",sus.jp);
		jp.add("bbr",bbr.jp_bor_ret);
		jp.add("abs",abs.jp_addBooks);
		jp.add("aus",aus.jp_addUser);
		jp.add("uinfo",uinfo.panel);
		jp.add("sbow",sbow.jp);
		jp.add("delu",delu.jp);
		jp.add("bret",bret.jp);
		jp.setOpaque(false);
	}
	
	public static int getWidth() {
		return width;
	}
	public static int getHeight() {
		return height;
	}
	
}
