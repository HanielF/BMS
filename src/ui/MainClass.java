package ui;

import model.*;
import java.util.*;
import javax.swing.*;

import java.awt.BorderLayout;
import java.io.*;
import jdbc.Database;
import operation.*;

public class MainClass {
	public static ArrayList<Book> books = new ArrayList<Book>();
	public static ArrayList<User> users = new ArrayList<User>();
	static Formatter formatter= new Formatter(System.out);
	
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
	
	public void doOperation(int cho) {
		BookOperation bop=new BookOperation();
		switch (cho) {
		case 1:
			bop.printAllBooks();
			break;
		case 2:
			bop.addBook();
			break;
		case 3:
			bop.deleteBook();
			break;
		case 4:
			bop.findBook();
			break;
		case 5:
			bop.updateBook();
			break;
		case 6:
			
			showMenu();
			break;
		}
	}
	
	public static void main(String []args) {
		MainClass mc = new MainClass();
		MainPage mp = new MainPage();
		LoginPage lp=new LoginPage();
		
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


