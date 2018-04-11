package library;

import java.util.ArrayList;

public class OverdueNotice {
	private Patron patron;
	private int todaysDate;

	/**
	 * Constructs an overdue notice for the given Patron. 
	 * @param patron
	 * @param todaysDate
	 */
	public OverdueNotice(Patron patron, int todaysDate) {
		this.patron = patron;
		this.todaysDate = todaysDate;
	}
	
	/**
	 * Returns as a String, in a nice, humanly readable format, an overdue notice. 
	 */
	@Override
	public String toString(){
		String s ="";
		s += "Patron: " + this.patron + '\n';
		ArrayList<Book> books = this.patron.getBooks();
		for (Book book : books) {
			if (book.getDueDate() < this.todaysDate) {
				s += "Title: " + book.getTitle() + "  || Due Date: " + book.getDueDate() + "  || Status: " + "Overdue\n";
			}
			else{
				s += "Title: " + book.getTitle() + "  || Due Date: " + book.getDueDate() + "  || Status: " + "Not Overdue\n";
			}
		}
		return s;
	}
	


}
