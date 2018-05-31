package ui;

<<<<<<< HEAD
=======
import model.*;
import java.util.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.io.*;
import java.sql.ResultSet;

>>>>>>> dev
import jdbc.Database;

public class MainClass {
<<<<<<< HEAD
	//public static ArrayList<Book> books = new ArrayList<Book>();
	//public static ArrayList<User> users = new ArrayList<User>();
	//public static Formatter formatter= new Formatter(System.out);
	public static Database db = new Database();
	public static MainPage mp = new MainPage();
	public static ForgetPwd fp = new ForgetPwd();
	public static LoginPage lp = new LoginPage();
	public static SignUp su = new SignUp();
=======
	public static ArrayList<Book> books = new ArrayList<Book>();
	public static ArrayList<User> users = new ArrayList<User>();
	public static Formatter formatter= new Formatter(System.out);
	public static Database db = new Database();
	public static MainPage mp = new MainPage();
	
	public MainClass() {
		mp.jf.setVisible(true);
	}
	
	public void showMenu() {
		formatter.format("Please choose an operation to continue\n");
		formatter.format("%-4d Show All Books\t",1);
		formatter.format("%-4d Add A Book\t\t",2);
		formatter.format("%-4d Delete A Book\n",3);
		formatter.format("%-4d Find A Book\t",4);
		formatter.format("%-4d Update A Book\t",5);
		formatter.format("%-4d Print Menu\n",6);
		//formatter.close();
	}
>>>>>>> dev
	
	public MainClass() {
		mp.jf.setVisible(true);
		lp.jf.setVisible(true);
		fp.jf.setVisible(false);
		su.jf.setVisible(false);
	}
	
	public static void main(String []args) {
		MainClass mc = new MainClass();
<<<<<<< HEAD

		//mc.db.dbUpdate("insert into books values('boom','zhangle','0.1','1000003',1)");
		//ForgetPwdPage fp = new ForgetPwdPage();
		//SignUpPage sup = new SignUpPage();
=======
		//LoginPage lp = new LoginPage();
>>>>>>> dev
		
		/*JFrame f=new JFrame();
		Sidebar sbar=new Sidebar();
		f.getContentPane().add(sbar.scrollPane,BorderLayout.WEST);
		f.setVisible(true);
		f.pack();
		f.setSize(400, 400);
		*/
		/*System.out.println("=======Welcome to the Book Manager System!=======");
		mc.showMenu();
		Scanner sn= new Scanner(System.in);
		
		while(true) {
			int menuChoice;
			System.out.print("Please enter you choice:  ");
			
			menuChoice=sn.nextInt();
			mc.doOperation(menuChoice);
		}
		 */
	}

}


