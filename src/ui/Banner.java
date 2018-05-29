package ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Banner {
	public JPanel jp;
	private static int width=MainPage.getWidth();
	private static int height=40;
	
	public Banner() {		
		jp=new JPanel(){  
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
         
		jp.setVisible(true);
		jp.setLocation(0,0);
		jp.setPreferredSize(new Dimension(width,height));
	}
	
	public static int getHeight() {
		return height;
	}
	public static int getWidth() {
		return width;
	}
	
}
