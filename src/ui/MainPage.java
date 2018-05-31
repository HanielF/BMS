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
	public static CardLayout cl = new CardLayout();
	public static JPanel jp= new JPanel(cl);
<<<<<<< HEAD
  
=======
	public static Sidebar sbar = new Sidebar();
	public static ShowBooks sbs = new ShowBooks();
	public static ShowUsers sus = new ShowUsers();
	//public static JScrollPane jsp = new JScrollPane();
>>>>>>> dev
	public JLabel jl1=new JLabel("WHAT");
	public JButton jb1=new JButton("fuck");
	
	private static int width = 800;
	private static int height = 600;
	
	public static Banner bn = new Banner();
	public static Sidebar sbar = new Sidebar();
	public static ShowBooks sbs = new ShowBooks();
	public static ShowUsers sus = new ShowUsers();
	public static BookBorrowReturn bbr = new BookBorrowReturn();


	public MainPage() {
		initJsp();
		
		//因为JFrame不可以直接添加，并且布局也是通过contentPane设置	
		Container contentPane = jf.getContentPane();
		contentPane.add(sbar.scrollPane,BorderLayout.WEST);
		contentPane.add(jp,BorderLayout.CENTER);
<<<<<<< HEAD
		contentPane.add(bn.jp,BorderLayout.NORTH);
		
		//set the page in center of screen
	    Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();  
	    jf.setSize(width,height);
	    jf.setLocation(scr.width/2-jf.getWidth()/2,scr.height/2-jf.getHeight()/2);  

=======
		
		//set the page in center of screen
	    Dimension scr=Toolkit.getDefaultToolkit().getScreenSize();  
	    jf.setSize(650,500);
	    jf.setLocation(scr.width/2-jf.getWidth()/2,scr.height/2-jf.getHeight()/2);  
	    
>>>>>>> dev
	    jf.setTitle("Book Manager System");
		jf.pack();
		jf.setVisible(false);
		
	    jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
	
	public void initJsp() {
<<<<<<< HEAD
		jp.setSize(width-sbar.getWidth(),height-Banner.getHeight());
		jp.add("sbs",sbs.jsp);
		jp.add("sus",sus.jsp);
		jp.add("bbr",bbr.jp_bor_ret);
=======
		jp.add("sbs",sbs.jsp);
		jp.add("sus",sus.jsp);
>>>>>>> dev
		jp.add("jl1",jl1);
		jp.add("jb1",jb1);
	}
	
<<<<<<< HEAD
	public static int getWidth() {
		return width;
	}
	public static int getHeight() {
		return height;
	}
	
=======
	public static void next() {
		cl.next(jp);
	}
>>>>>>> dev
	
}