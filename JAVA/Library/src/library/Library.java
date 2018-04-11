package library;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Library {
	HashMap<String, Patron> patronsOfLibrary;
	Patron currentPatron;
	ArrayList<Book> collection;
	ArrayList<Book> foundBooks;
	ArrayList<Book> patronBooks;
	Calendar calendar;

	private boolean okToPrint;
	boolean running;
	boolean operating;

	/**
	 * This is the constructor that will be used by the main method, once, to "create the library". 
	 * It also sets a private instance variable okToPrint to true.
	 */
	public Library() {
		patronsOfLibrary = new HashMap<String, Patron>();
		currentPatron = new Patron(null, this);
		collection = this.readBookCollection();
		foundBooks = new ArrayList<Book>();
		patronBooks = new ArrayList<Book>();
		calendar = new Calendar();

		okToPrint = true;
		running = true;
		operating = false;
	}

	private ArrayList<Book> readBookCollection() {
		File file = new File("books.txt");
		ArrayList<Book> collection = new ArrayList<Book>();
		try {
			FileReader fileReader = new FileReader(file);
			BufferedReader reader = new BufferedReader(fileReader);
			while (true) {
				String line = reader.readLine();
				if (line == null) break;
				line = line.trim();
				if (line.equals("")) continue; // ignore possible blank lines
				String[] bookInfo = line.split(" :: ");
				collection.add(new Book(bookInfo[0], bookInfo[1]));
			} 
			reader.close();
		}
		catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return collection;
	}

	/**
	 * This is a second constructor, used by the test methods. 
	 * It sets the private instance variable okToPrint to false.
	 * @param collection
	 */
	public Library(ArrayList<Book> collection) {
		patronsOfLibrary = new HashMap<String, Patron>();
		currentPatron = new Patron(null, this);
		this.collection = collection;
		foundBooks = new ArrayList<Book>();
		patronBooks = new ArrayList<Book>();
		calendar = new Calendar();

		okToPrint = false;
		running = true;
		operating = false;
	}	


	/**
	 * If the instance variable okToPrint is true, prints the message using System.out.print(message)
	 * otherwise this method returns without doing anything.
	 * @param message
	 */
	void print(String message){
		if (okToPrint) {
			System.out.print(message);
		}
	}

	/**
	 * If the instance variable okToPrint is true, prints the message using System.out.println(message)
	 * otherwise this method returns without doing anything.
	 * @param message
	 */
	void println(String message){
		if (okToPrint) {
			System.out.println(message);
		}
	}

	/**
	 * Starts the day (by advancing the calendar). 
	 * Then sends overdue notices to all patrons with overdue books.
	 * Sets an instance variable to indicate that the library is now open.
	 * Returns the list of notices that it got from calling createOverdueNotices.
	 * @return
	 */
	ArrayList<OverdueNotice> open(){
		this.operating = true;
		this.calendar.advance();
		println("Today is " + this.calendar.getDate() + ".");
		println("Good morning. Library is open.");
		ArrayList<OverdueNotice> todaysOverdueNotices = createOverdueNotices();
		for (OverdueNotice overdueNotice : todaysOverdueNotices) {
			println(overdueNotice.toString());
		}
		return todaysOverdueNotices;
	}


	/**
	 * Checks each Patron to see whether he/she has books which were due yesterday. 
	 * For each such patron, creates and returns a list of overdue notices. 
	 * @return
	 */	
	ArrayList<OverdueNotice> createOverdueNotices(){
		ArrayList<OverdueNotice> todaysOverdueNotices = new ArrayList<OverdueNotice>();
		for (Patron patron : patronsOfLibrary.values()) {
			for (Book book : patron.getBooks()) {
				if ((book.getDueDate() + 1) == this.calendar.getDate()) {
					todaysOverdueNotices.add(new OverdueNotice(patron, this.calendar.getDate()));
					break;
				}
			}
		}
		return todaysOverdueNotices;
	}

	/**
	 * Issues a library card to the person with this name. 
	 * It creates a Patron object, and saves it as the value in a HashMap, with the patrons name as the key.
	 * The created Patron object is returned as the value of the method.
	 * @param nameOfPatron
	 * @return
	 */
	Patron issueCard(String nameOfPatron){
		Patron newPatron = new Patron(nameOfPatron, this);
		for (Patron patron : patronsOfLibrary.values()) {
			if (patron.getName().equals(nameOfPatron)) {
				println("Library has already had this patron!");
				return newPatron;
			}
		}
		patronsOfLibrary.put(nameOfPatron, newPatron);
		return newPatron;
	}

	/**
	 * Begin checking books out to (or in from) the named patron. 
	 * look up the patrons name in the HashMap, and save the returned Patron object in currentPatron.
	 * This method returns the Patron object.
	 * @param nameOfPatron
	 * @return
	 */
	Patron serve(String nameOfPatron) {
		patronBooks.clear();

		currentPatron = this.patronsOfLibrary.get(nameOfPatron);		
		String s = "";
		if (currentPatron == null) {
			s += "Patron is not in system!\n";
		}
		else{
			s += "Now Serving: " + currentPatron.toString() + " ...\n";
			if (currentPatron.getBooks().size() == 0) {
				s += currentPatron.toString() + " currently has no book checked out.\n";
			}
			else{
				s += currentPatron.toString() + " currently has the following checked out:\n";
				for (int i = 0; i < currentPatron.getBooks().size(); i++) {
					s += Integer.toString(i+1) + ": " + currentPatron.getBooks().get(i).toString(); 
					patronBooks.add(currentPatron.getBooks().get(i));
				}

			}
		}
		print(s);

		return currentPatron;
	}

	/**
	 * The listed books are being returned by the current Patron.
	 * Return them to the collection and remove them from the list of books currently checked out to the patron. 
	 * The bookNumbers are taken from the list printed by the serve command. 
	 * Returns a list of the books just checked in.
	 * @param bookNumbers
	 * @return
	 */
	ArrayList<Book> checkIn(int... bookNumbers){
		ArrayList<Book> booksCheckIn = new ArrayList<Book>();
		if (currentPatron.getName() == null) {
			println("No patron is being served now...");
			return booksCheckIn;
		}
		if (currentPatron.getBooks().size() == 0) {
			println("No book has been checked out!");
			return booksCheckIn;
		}
		for (int i = 0; i < bookNumbers.length; i++) {
			if (bookNumbers[i] > patronBooks.size() || bookNumbers[i] < 1) {
				println("Illegal BookNumber " + bookNumbers[i] + "!");	
				continue;
			}
			Book book = patronBooks.get(bookNumbers[i]-1);
			if (book != null) {
				book.checkIn();
				collection.add(book);
				patronBooks.set(bookNumbers[i]-1, null);
				currentPatron.giveBack(book);
				booksCheckIn.add(book);
				println("Checkin book " + bookNumbers[i] + " success!");
			}			
		}		
		return booksCheckIn;
	}

	/**
	 * Looks up books whose title or author (or both) contains this string. 
	 * The search is case-insensitive. 
	 * Only books which are currently available (not checked out) will be returned. 
	 * Require that the search string be at least 4 characters long. 
	 * If multiple copies of a book are found, only one copy should be printed. 
	 * Returns a list of the books just found.
	 * @param part
	 * @return
	 */
	ArrayList<Book> search(String part) {
		foundBooks.clear();

		if (part.length() < 4) {
			println("Search string should be at least 4 characters.");
			return foundBooks;
		}

		boolean flag;
		for (Book book : collection) {
			if (book.getTitle().toLowerCase().contains(part.toLowerCase()) || book.getAuthor().toLowerCase().contains(part.toLowerCase())) {
				flag = true;
				for (Book element : foundBooks) {
					if (element.toString().equals(book.toString())) {
						flag = false;
						break;
					}
				}
				if (flag){
					foundBooks.add(book);					
				}
			}
		}

		String s = "";
		if (foundBooks.size() == 0) {
			s = "No such book!";
		}
		else{
			s = "Search results: \n";
			for (int i = 0; i < foundBooks.size(); i++){
				s += Integer.toString(i+1) + ": " + foundBooks.get(i).toString();				
			}
			print(s);			
		}

		return foundBooks;
	}


	/**
	 * Either checks out the book to the Patron currently being served , 
	 * or tells why the operation is not permitted. 
	 * The bookNumbers are one or more of the books returned by the most recent call to the search method.
	 * Returns a list of the books just checked out.
	 * @param bookNumbers
	 * @return
	 */
	ArrayList<Book> checkOut(int... bookNumbers) {
		ArrayList<Book> booksCheckOut = new ArrayList<Book>();
		if (currentPatron.getName() == null) {
			println("No patron being served now...");
			return booksCheckOut;
		}
		if (currentPatron.getBooks().size() >= 3) {
			println("Already have 3 books checked out.");
			return booksCheckOut;
		}
		if (bookNumbers.length > 3) {
			println("Cannot check out more than 3 books");
			return booksCheckOut;
		}		
		for (int i = 0; i < bookNumbers.length; i++) {
			if (bookNumbers[i] > foundBooks.size() || bookNumbers[i] < 1) {
				println("Illegal BookNumber " + bookNumbers[i] + "!");	
				continue;
			}
			Book book = foundBooks.get(bookNumbers[i]-1);
			if (book != null) {
				book.checkOut(calendar.getDate()+7);
				collection.remove(book);
				foundBooks.set(bookNumbers[i]-1, null);
				currentPatron.take(book);
				booksCheckOut.add(book);
				println("Checkout book " + bookNumbers[i] + " success!");
			}
			else{
				println("This book has been checked out.");
			}

		}		
		return booksCheckOut;
	}



	/**
	 * Shut down operations and go home for the night. 
	 * None of the other operations (except	quit) can be used when the library is closed.
	 */
	void close() {
		this.operating = false;
		println("Good night. Now library is close.");
	}

	/**
	 * End the program.
	 */
	void quit() {
		this.running = false;
		println("Program is shut down!");
	}


	void start() {
		Scanner scanner = new Scanner(System.in);
		while (running) {
			println("Enter a command:");

			String command = scanner.next();
			String args;

			if (this.operating) {
				switch (command) {						
				case "issueCard":
					args = scanner.nextLine();
					args = args.trim();
					issueCard(args);
					break;

				case "serve":
					args = scanner.nextLine();
					args = args.trim();
					serve(args);
					break;

				case "checkIn": {
					args = scanner.nextLine(); 
					String[] numbers = args.split(",");
					int size = numbers.length; 
					int[] bookNumbers = new int[size];
					for (int i=0; i<size; i++){
						bookNumbers[i] = Integer.parseInt(numbers[i].trim());
					}
					checkIn(bookNumbers);
					break;
				}

				case "search":
					args = scanner.nextLine();
					args = args.trim();
					search(args);
					break;

				case "checkOut": {
					args = scanner.nextLine(); 
					String[] numbers = args.split(",");
					int size = numbers.length; 
					int[] bookNumbers = new int[size];
					for (int i=0; i<size; i++){
						bookNumbers[i] = Integer.parseInt(numbers[i].trim());
					}
					checkOut(bookNumbers);
					break;
				}

				case "close":
					close();
					break;

				case "quit":
					quit();
					break;

				default:
					args = scanner.nextLine(); 
					println("Illegal command!");
					break;
				}
			}

			else {
				switch (command) {				
				case "open":
					open();
					break;
				case "quit":
					quit();
					break;
				default:
					args = scanner.nextLine(); 
					println("Illegal command!!!");
					break;					
				}				
			}
		}
		scanner.close();
	}

	/**
	 * This creates one Library object and calls its start method.
	 * @param args
	 */
	public static void main(String[] args){
		Library library = new Library();		
		library.start();		
	}

}

