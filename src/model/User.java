package model;

import model.Book;
import java.util.*;

public class User {
	public static final int maxn=5;		//max size of array books	
	private String name;		    		//name of user
	private int userid;					//id of user
	private Book[] books;					//books borrowed by the user
	private int cnt;						//number of books user has borrowed
	
	public User(String uname,int uid)
	{
		name=uname;
		userid=uid;
		setBooks(new Book[maxn]);
		cnt=0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public Book[] getBooks() {
		return books;
	}

	public void setBooks(Book[] books) {
		this.books = books;
	}

	public int getCnt() {
		return cnt;
	}


}
