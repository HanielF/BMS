package ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Banner {
	public JPanel jp;
	private JLabel jl_name;
	private JLabel jl_welcome;
	private JLabel jl_exit;
	private Image icon;
	private static int width=MainPage.getWidth();
	private static int height=40;
	
	public Banner() {		
		jp=new JPanel(new BorderLayout()){  
            @Override  
            public void paintComponent(Graphics g) {  
                Graphics2D g2=(Graphics2D)g;   
                super.paintComponents(g);  
                Image image = new ImageIcon("./source/PureColor.jpg").getImage(); 
                //windows下路径
                //Image image = new ImageIcon("F:\\Eclipse\\JAVA\\BookManager\\source\\PureColor.jpg").getImage();   
                g2.drawImage(image,0,0,width,height,this);  
            }  
         }; 
        initLabel();
        jp.add(jl_name,BorderLayout.EAST);
        //jp.add(jl_welcome);
        //jp.add(jl_exit);
 
		jp.setVisible(true);
		jp.setLocation(0,0);
		jp.setPreferredSize(new Dimension(width,height));
	}
	
	public void initLabel() {
		jl_name=new JLabel(MainClass.db.getCur_user(),FlowLayout.LEFT);
		//jl_name = new JLabel("I'm name",FlowLayout.LEFT);
		jl_name.setFont(new Font("Arial",Font.BOLD,16));
		jl_name.setPreferredSize(new Dimension(140,30));
		//jl_name.setLocation(width-jl_name.getWidth(),height/2-jl_name.getHeight()/2);
		jl_name.setVisible(true);
	}
	
	public static int getHeight() {
		return height;
	}
	public static int getWidth() {
		return width;
	}
	
}
