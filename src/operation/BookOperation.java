package operation;

import ui.MainClass;
import java.util.*;
import model.Book;

/*
 * main function:
 * 	addBook()
 * 	deleteBook()
 * 	findBook()
 * 	printAllBook()
 * 	updateBook()
 */
public class BookOperation {
	//return the size of books
	public int addBook() {
		Book boo=new Book();
		Scanner sn=new Scanner(System.in);
		System.out.println("Please enter the book information:");
		System.out.print("Book Name: ");
		boo.setBookname(sn.next());
		
		System.out.print("Book Author: ");
		boo.setAuthor(sn.next());
		
		System.out.print("Book Price: ");
		boo.setPrice(sn.nextFloat());
		
		boo.setId(MainClass.books.size()+1);
		
		System.out.print("Book count: ");
		boo.setCnt(sn.nextInt());
		
		MainClass.books.add(boo);
		return MainClass.books.size();
	}
	
	public int addBook(Book boo) {
		MainClass.books.add(boo);
		return MainClass.books.size();
	}
	
	
//return the position of the book in arraylist
	public int findBook() {
		int i;
		String bname;
		Scanner bsn=new Scanner(System.in);
		
		System.out.print("Please enter the book's name: ");
		bname=bsn.next();
		
		for(i=0;i<MainClass.books.size();i++) {
			if(MainClass.books.get(i).getBookname().equals(bname)) 
				MainClass.books.get(i).printBook();
		}
		return i;
	}
	
	//bid, which isn't continuous, is the id of the book 
	//return the size of books
	public int deleteBook(int bid) {
		for(int i=0; i<MainClass.books.size(); i++) {
			if(MainClass.books.get(i).getId()==bid) 
				MainClass.books.remove(i);
		}
		return MainClass.books.size();
	}
	
	public int deleteBook() {
		int id;
		System.out.println("Please enter the book id: ");
		Scanner sn=new Scanner(System.in);
		id=sn.nextInt();
		deleteBook(id);
		return MainClass.books.size();
	}
	
	
	public void updateBook() {
		System.out.print("Please enter the bootk's name: ");
		Scanner sn=new Scanner(System.in);
		String bname=sn.next();
		for(int i=0; i<MainClass.books.size(); i++) {
			if(MainClass.books.get(i).getBookname().equals(bname)) {
				MainClass.books.get(i).setBookname(bname);
				System.out.print("Please enter the updated author: ");
				MainClass.books.get(i).setAuthor(sn.next());
				System.out.print("Please enter the updated price: ");
				MainClass.books.get(i).setPrice(sn.nextFloat());
				System.out.print("Please enter the updated count: ");
				MainClass.books.get(i).setCnt(sn.nextInt());
			}
		}
	}
	
	public void printAllBooks() {
		for(int i=0;i<MainClass.books.size();i++) {
			MainClass.books.get(i).printBook();
		}
	}
		
	


}
