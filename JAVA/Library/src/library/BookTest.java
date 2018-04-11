package library;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BookTest {
	
	private Book book;
	private Book book2;

	@Before
	public void setUp() throws Exception {
		book = new Book("1984", "George Orwell");
		book2 = new Book("U.S.A. (trilogy)", "John Dos Passos");
	}

	@Test
	public void testBook() {
		Book booktest = new Book("1984", "George Orwell");
		assertTrue(booktest instanceof Book);
		
		Book booktest2 = new Book("U.S.A. (trilogy)", "John Dos Passos");
		assertTrue(booktest2 instanceof Book);
	}

	@Test
	public void testGetTitle() {
		assertEquals("1984", book.getTitle());
		assertEquals("U.S.A. (trilogy)", book2.getTitle());
	}

	@Test
	public void testGetAuthor() {
		assertEquals("George Orwell", book.getAuthor());
		assertEquals("John Dos Passos", book2.getAuthor());
	}

	@Test
	public void testGetDueDate() {
		assertEquals(-1, book.getDueDate());
		assertEquals(-1, book2.getDueDate());
	}

	@Test
	public void testCheckOut() {
		book.checkOut(7);
		assertEquals(7, book.dueDate);
		
		book2.checkOut(0);
		assertEquals(0, book2.dueDate);
	}

	@Test
	public void testCheckIn() {
		book.checkIn();
		assertEquals(-1, book.dueDate);
		book.checkOut(6);
		book.checkIn();
		assertEquals(-1, book.dueDate);
		
		book2.checkIn();
		assertEquals(-1, book2.dueDate);
		book2.checkOut(9);
		book2.checkIn();
		assertEquals(-1, book2.dueDate);
	}

	@Test
	public void testToString() {
		assertEquals("1984, by George Orwell\n", book.toString());
		assertEquals("U.S.A. (trilogy), by John Dos Passos\n", book2.toString());
	}

}