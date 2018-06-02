package ui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;

public class Banner {
	public JPanel jp;
	private JLabel jl_name;
	private JButton jb_exit ;
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
        jp.add(jl_name,BorderLayout.WEST);
        jp.add(jb_exit,BorderLayout.EAST);
 
		jp.setVisible(true);
		jp.setLocation(0,0);
		jp.setPreferredSize(new Dimension(width,height));	
	}
	
	public void initLabel() {
		jl_name=new JLabel("I'm Guest",FlowLayout.RIGHT);
		if(MainClass.db.getIs_manager()==1)
			jl_name.setText("    I'm Manager");
		else
			jl_name.setText("    I'm Borrower");
		
		jl_name.setFont(new Font("Arial",Font.BOLD,17));
		jl_name.setPreferredSize(new Dimension(200,30));
		jl_name.setVisible(true);

		jb_exit=new JButton("Exit");
		jb_exit.setFont(new Font("Arial",Font.BOLD,17));
		jb_exit.setPreferredSize(new Dimension(80,20));
        jb_exit.setBackground(new Color(0));
        jb_exit.setBorderPainted(false);
        jb_exit.setOpaque(false); 
        
        jb_exit.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	}
        });
	}

	public static int getHeight() {
		return height;
	}
	public static int getWidth() {
		return width;
	}
	
}
