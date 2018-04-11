package library;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class OverdueNoticeTest {
	
	private Patron patron;
    private Book book1, book2;
	private int todaysDate;
	private OverdueNotice overdueNotice;

	@Before
	public void setUp() throws Exception {
		patron = new Patron("Dave", null);
        book1 = new Book("Disappearing Nightly", "Laura Resnick");
        book2 = new Book("Contact", "Carl Sagan");
	}

	@Test
	public void testOverdueNotice() {
		overdueNotice = new OverdueNotice(patron, todaysDate);
        assertTrue(overdueNotice instanceof OverdueNotice);
	}

	@Test
	public void testToString() {
		todaysDate = 0;		
		
		patron.take(book1);
		book1.checkOut(todaysDate + 7);
		patron.take(book2);
		book2.checkOut(todaysDate + 7 );
		overdueNotice = new OverdueNotice(patron, todaysDate);
		String expected = "Patron: Dave\n"
						+ "Title: Disappearing Nightly  || Due Date: 7  || Status: Not Overdue\n"
						+ "Title: Contact  || Due Date: 7  || Status: Not Overdue\n";
		assertEquals(expected, overdueNotice.toString());
		
		todaysDate = 8;
		overdueNotice = new OverdueNotice(patron, todaysDate);
		expected = "Patron: Dave\n"
				+ "Title: Disappearing Nightly  || Due Date: 7  || Status: Overdue\n"
				+ "Title: Contact  || Due Date: 7  || Status: Overdue\n";
		assertEquals(expected, overdueNotice.toString());
	}

}
