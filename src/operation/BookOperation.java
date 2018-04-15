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
	public int addBook(Book boo) {
		MainClass.books.add(boo);
		return MainClass.books.size();
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
	
	public void updateBook(int bid, Book boo) {
		for(int i=0; i<MainClass.books.size(); i++) {
			if(MainClass.books.get(i).getId()==bid) {
				MainClass.books.get(i).setBook(boo);
			}
		}
	
	public void printAllBooks() {

	}
		
	}


}
