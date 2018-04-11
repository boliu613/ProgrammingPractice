package library;

public class Book {

	String title;
	String author;
	int dueDate;
	
	/**
	 * constructs a class Book, with given title and author
	 * initial Book is not checked out, dueDate is set to -1
	 * @param title
	 * @param author
	 */
	public Book(String title, String author) {
		this.title = title;
		this.author = author;
		this.dueDate = -1;
	}
	
	/**
	 * returns book's title
	 * @return
	 */
	public String getTitle() {
		return this.title;
	}
	
	/**
	 * returns book's author
	 * @return
	 */
	public String getAuthor() {
		return this.author;
	}
	
	/**
	 * returns the date that this book is due
	 * @return
	 */
	public int getDueDate() {
		return this.dueDate;
	}
	
	/**
	 * sets the due date of this Book to specified date
	 * @param date
	 */
	public void checkOut(int date) {
		this.dueDate = date;
	}
	
	/**
	 * sets due date of this Book to -1
	 */
	public void checkIn() {
		this.dueDate = -1;
	}
	
	/**
	 * returns a string of form title, by author
	 */
	@Override
	public String toString() {
		return this.title + ", by " + this.author + '\n';
	}

}