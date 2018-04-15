package model;

public class Book {  
	private String bookname;	//name of the book
	private String author;		//author of the book
	private float price;		//price of the book
	private int bookid;			//id of the book
	private int cnt;			//number of the book left
	
	public Book(String bname,int bid,String bauthor,float bprice,int bcnt)
	{
		bookname=bname;
		setAuthor(bauthor);
		setPrice(bprice);
		setId(bid);
		setCnt(bcnt);
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getId() {
		return bookid;
	}

	public void setId(int id) {
		this.bookid = id;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}
	
}  