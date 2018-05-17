package ui;
/*the mainpage of the bms,using borderlayout
 * include a sidebar in west which is a menu and welcome in north
 * the east part is null while the center is some information 
 * and operations that correspond to menu choice
 */

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MainPage extends JFrame {
	private Sidebar sbar;
	private JFrame frame;
	
	public MainPage() {
		frame = new JFrame("Book Manager System");
		sbar = new Sidebar();
		Container contentPane = frame.getContentPane();//因为JFrame不可以直接添加，并且布局也是通过contentPane设置
		contentPane.add(sbar.getScrollPane(),BorderLayout.WEST);
		contentPane.add(new TextField(),BorderLayout.CENTER);
		
		frame.pack();
		frame.setVisible(true);
		frame.setSize(400, 600);
		
		frame.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}
}
