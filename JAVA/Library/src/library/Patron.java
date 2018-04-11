package library;

import java.util.*;

public class Patron {
	private String name;
	private Library library;
	private ArrayList<Book> books;
	/**
	 * Constructs a patron with the given name, and no books. 
	 * The patron must also have a reference to the Library object that he/she uses.
	 * @param name
	 * @param library
	 */
	public Patron(String name, Library library) {
		this.name = name;
		this.library = library;
		this.books = new ArrayList<Book>();
	}
	
	/**
	 * Returns this patron’s name.
	 * @return
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * Adds this book to the list of books checked out by this Patron.
	 * @param book
	 */
	public void take(Book book){
		if (books.size() < 3) {
			books.add(book);
		}
	}
	
	/**
	 * Removes this Book object from the list of books checked out by this Patron.
	 * @param book
	 */
	public void giveBack(Book book){
		books.remove(book);
	}
	
	/**
	 * Returns the list of Book objects checked out to this Patron (may be an empty list).
	 * @return
	 */
	public ArrayList<Book> getBooks(){
		return this.books;
	}	
	
	
	/**
	 * Returns this patron’s name. 
	 */
	@Override
	public String toString(){
		return this.name;
	}
}
