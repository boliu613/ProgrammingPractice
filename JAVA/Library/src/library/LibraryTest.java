/**
 * 
 */
package library;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author David Matuszek
 */
public class LibraryTest {
    private Book contact;
    private Book contact2;
    private Book equalRites;
    private Book sisters;
    private Book witches;
    private Book nightly;
    private Book time;
    private Book rings;
    private ArrayList<Book> books;
    private Library library;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        contact = new Book("Contact", "Carl Sagan");
        equalRites = new Book("Equal Rites", "Terry Pratchett");
        sisters = new Book("Weird Sisters", "Terry Pratchett");
        witches = new Book("Witches Abroad", "Terry Pratchett");
        nightly = new Book("Disappearing Nightly", "Laura Resnick");
        contact2 = new Book("Contact", "Carl Sagan");
        time = new Book("Nick of Time", "Ted Bell");
        rings = new Book("Lord of the rings", "J. R. R. Tolkien");
        books = new ArrayList<Book>();
        books.add(contact);
        books.add(witches);
        books.add(sisters);
        books.add(equalRites);
        books.add(nightly);
        books.add(contact2);
        books.add(time);
        books.add(rings);
        library = new Library(books);        
        
    }

    /**
     * Test method for {@link library.Library#open()}.
     */
    @Test
    public void testOpen() {
        // The open() method doesn't make any change that we can
        // readily test, but we can at least make sure it returns
        // an ArrayList<Book>.
        assertEquals(new ArrayList<OverdueNotice>(), library.open());
    }
    
    private Patron openAndServeDave() {
        library.open();
        Patron dave = library.issueCard("Dave");
        library.serve("Dave");
        return dave;
    }

    /**
     * Test method for {@link library.Library#createOverdueNotices()}.
     */
    @Test
    public void testCreateOverdueNotices() {
        ArrayList<OverdueNotice> notices;
        openAndServeDave();
        ArrayList<Book> foundBooks = library.search("Equal Rites");
        assertEquals(equalRites, foundBooks.get(0));
        library.checkOut(1);
        
        int dueDate = 7;
        // Don't send an overdue notice during the next seven days
        for (int i = 0; i < dueDate; i++) {
            library.close();
            notices = library.open();
            assertTrue(notices.isEmpty());
            System.out.println(library.calendar.getDate());
        }
        System.out.println(library.calendar.getDate());
        library.close();
        for (int i=0;i<library.collection.size();i++){
        System.out.println(library.collection.get(i).dueDate);}
        // Send a notice on the 8th day
        notices = library.open();
        assertFalse(notices.isEmpty());
        library.close();
        // Don't send another notice after that
        notices = library.open();
        assertTrue(notices.isEmpty());
    }

    /**
     * Test method for {@link library.Library#issueCard(java.lang.String)}.
     */
    @Test
    public void testIssueCard() {
        library.open();
        Patron dave = library.issueCard("Dave");
        assertTrue(dave instanceof Patron);
        
        Patron tom = library.issueCard("Tom");
        assertTrue(tom instanceof Patron);
        assertEquals("Tom", tom.getName());
    }
  
    /**
     * Test method for {@link library.Library#serve(java.lang.String)}.
     */
    @Test
    public void testServe() {
        // We can test if the correct Patron is returned, but not if
        // it's being saved. The tests for checkIn and checkOut can
        // determine this.
        library.open();
        Patron dave = library.issueCard("Dave");
        Patron paula = library.issueCard("Paula");
        assertEquals(dave, library.serve("Dave"));
        assertEquals(paula, library.serve("Paula"));
    }

  
    /**
     * Test method for {@link library.Library#checkOut(int[])}.
     */
    @Test
    public void testCheckOutOneBook() {
        Patron dave = openAndServeDave();
        library.search("Time");
        ArrayList<Book> booksCheckedOut = library.checkOut(1);
        assertTrue(dave.getBooks().contains(time));
        assertEquals(dave.getBooks(), booksCheckedOut);
        // Book shouldn't still be in library
        ArrayList<Book> booksFound = library.search("Time");
        assertTrue(booksFound.isEmpty());
        
        library.search("witches");
        booksCheckedOut = library.checkOut(2);
        assertTrue(booksCheckedOut.isEmpty());
    }

    /**
     * Test method for {@link library.Library#checkOut(int[])}.
     */
    @Test
    public void testCheckOutOneOfMultipleCopies() {
        Patron dave = openAndServeDave();
        ArrayList<Book> booksFound = library.search("Carl Sagan");
        library.checkOut(1);
        // There should still be another copy in the library
        booksFound = library.search("Carl Sagan");
        assertEquals(1, booksFound.size());
        ArrayList<Book> davesBooks = dave.getBooks();
        assertTrue(davesBooks.contains(contact));
    }

  
    /**
     * Test method for {@link library.Library#checkOut(int[])}.
     */
    @Test
    public void testCheckOutBooksInRandomOrder() {
        Patron dave = openAndServeDave();
        library.search("Terry Pratchett");
        library.checkOut(1);
        library.checkOut(3);
        library.checkOut(2);
        ArrayList<Book> davesBooks = dave.getBooks();
        assertTrue(davesBooks.contains(witches));
        assertTrue(davesBooks.contains(sisters));
        assertTrue(davesBooks.contains(equalRites));
    }
    
    
    /**
     * Test method for {@link library.Library#checkOut(int[])}.
     */
    @Test
    public void testCheckOutMultipleBooksOneTime() {
        Patron dave = openAndServeDave();
        library.search("Terry Pratchett");
        library.checkOut(1,3,2);
        ArrayList<Book> davesBooks = dave.getBooks();
        assertTrue(davesBooks.contains(witches));
        assertTrue(davesBooks.contains(sisters));
        assertTrue(davesBooks.contains(equalRites));
    }
    /**
     * Test method for {@link library.Library#checkIn(int[])}.
     */
    @Test
    public void testCheckInOneBook() {
        Patron dave = openAndServeDave();
        ArrayList<Book> foundBooks = library.search("Disappearing Nightly");
        // Checking out a book moves it from the library to the patron
        library.checkOut(1);
        assertEquals(1, dave.getBooks().size());
        assertTrue(dave.getBooks().contains(nightly));
        assertTrue(library.search("Disappearing Nightly").isEmpty());
        // Checking in a book moves it back from the patron to the library
        library.serve("Dave");
        library.checkIn(1);
        assertFalse(library.search("Disappearing Nightly").isEmpty());
    }

    /**
     * Test method for {@link library.Library#checkOut(int[])}.
     */
    @Test
    public void testCheckInBooksInRandomOrder() {
        Patron dave = openAndServeDave();
        library.search("Terry Pratchett");
        library.checkOut(1,2,3);        
        ArrayList<Book> davesBooks = dave.getBooks();
        assertTrue(davesBooks.contains(witches));
        assertTrue(davesBooks.contains(sisters));
        assertTrue(davesBooks.contains(equalRites));
        library.serve("Dave");
        library.checkIn(2); 
        library.checkIn(1); 
        library.checkIn(3); 
        davesBooks = dave.getBooks();
        assertFalse(davesBooks.contains(witches));
        assertFalse(davesBooks.contains(sisters));
        assertFalse(davesBooks.contains(equalRites));
    }
    
    /**
     * Test method for {@link library.Library#checkIn(int[])}.
     */
    @Test
    public void testCheckInManyBooks() {
        openAndGiveBooksToDave();
        Patron dave = library.serve("Dave");
        ArrayList<Book> davesBooks = dave.getBooks();
        assertEquals(3, davesBooks.size());
        Book someBook = davesBooks.get(1); // Can't be sure which book this is
        assertTrue(library.search(someBook.getTitle()).isEmpty());
        assertTrue(davesBooks.contains(witches));
        assertTrue(davesBooks.contains(sisters));
        assertTrue(davesBooks.contains(equalRites));
        library.serve("Dave");
        library.checkIn(1,2,3);
        davesBooks = dave.getBooks();
        assertFalse(davesBooks.contains(witches));
        assertFalse(davesBooks.contains(sisters));
        assertFalse(davesBooks.contains(equalRites));
        
    }
    
    private void openAndGiveBooksToDave() {
        Patron dave = openAndServeDave();
        library.search("Terry");
        library.checkOut(1);
        library.checkOut(2);
        library.checkOut(3);
    }

    /**
     * Test method for {@link library.Library#search(java.lang.String)}.
     */
    @Test
    public void testSearchTitle() {
        library.open();
        ArrayList<Book> foundBooks = library.search("appear");
        assertEquals(1, foundBooks.size());
        assertEquals("Disappearing Nightly", foundBooks.get(0).getTitle());
        foundBooks = library.search("xyzzy");
        assertEquals(0, foundBooks.size());
        foundBooks = library.search("e");
        assertEquals(0, foundBooks.size());
    }

    /**
     * Test method for {@link library.Library#search(java.lang.String)}.
     */
    @Test
    public void testSearchAuthor() {
        library.open();
        ArrayList<Book> foundBooks = library.search("Resnick");
        assertEquals(1, foundBooks.size());
        assertEquals("Laura Resnick", foundBooks.get(0).getAuthor());
        foundBooks = library.search("erry");
        assertEquals(3, foundBooks.size());
        assertTrue(foundBooks.contains(equalRites));
        assertTrue(foundBooks.contains(sisters));
        assertTrue(foundBooks.contains(witches));
    }

    /**
     * Test method for {@link library.Library#search(java.lang.String)}.
     */
    @Test
    public void testSearchWithMixedCase() {
        library.open();
        ArrayList<Book> foundBooks = library.search("laura");
        assertEquals(1, foundBooks.size());
        foundBooks = library.search("NIGHTLY");
        assertEquals(1, foundBooks.size());
        foundBooks = library.search("Nick");
        assertEquals(2, foundBooks.size());
    }

    /**
     * Test method for {@link library.Library#search(java.lang.String)}.
     */
    @Test
    public void testSearchAndIgnoreDuplicates() {
        library.open();
        ArrayList<Book> foundBooks = library.search("Contact");
        assertEquals(1, foundBooks.size());
    }

    
}
