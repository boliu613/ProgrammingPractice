/**
 * Tests for Library assignment.
 */
package library;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 * @author David Matuszek
 */
public class PatronTest {
    private Patron dave;
    private Patron paula;
    private Book book1, book2, book3, book4;

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {
        dave = new Patron("Dave", null);
        paula = new Patron("Paula", null);
        book1 = new Book("Disappearing Nightly", "Laura Resnick");
        book2 = new Book("Contact", "Carl Sagan");
        book3 = new Book("Equal Rites", "Terry Pratchett");
        book4 = new Book("Weird Sisters", "Terry Pratchett");
    }

    /**
     * Test method for {@link library.Patron#Patron(java.lang.String, library.Library)}.
     */
    @Test
    public void testPatron() {
        Patron paula = new Patron("Paula", null);
        assertTrue(paula instanceof Patron);
    }

    /**
     * Test method for {@link library.Patron#getName()}.
     */
    @Test
    public void testGetName() {
        assertEquals("Dave", dave.getName());
        assertEquals("Paula", paula.getName());
    }

    /**
     * Test method for {@link library.Patron#take(library.Book)}.
     */
    @Test
    public void testTake() {
        paula.take(book1);
        assertTrue(paula.getBooks().contains(book1));
        assertFalse(dave.getBooks().contains(book1));
        
        paula.take(book2);
        assertTrue(paula.getBooks().contains(book2));
        
        paula.take(book3);
        assertTrue(paula.getBooks().contains(book3));
        
        paula.take(book4);
        assertFalse(paula.getBooks().contains(book4));
    }

    /**
     * Test method for {@link library.Patron#giveBack(library.Book)}.
     */
    @Test
    public void testGiveBack() {
        paula.take(book1);
        assertTrue(paula.getBooks().contains(book1));
        paula.giveBack(book1);
        assertFalse(paula.getBooks().contains(book1));
        
        paula.take(book2);
        assertTrue(paula.getBooks().contains(book2));
        paula.giveBack(book2);
        assertFalse(paula.getBooks().contains(book2));
    }

    /**
     * Test method for {@link library.Patron#getBooks()}.
     */
    @Test
    public void testGetBooks() {
        dave.take(book1);
        assertTrue(dave.getBooks().contains(book1));
        assertEquals(1, dave.getBooks().size());
        
        dave.take(book2);
        assertTrue(dave.getBooks().contains(book2));
        assertEquals(2, dave.getBooks().size());

        dave.take(book3);
        assertTrue(dave.getBooks().contains(book3));
        assertEquals(3, dave.getBooks().size());
        
        dave.take(book4);
        assertFalse(dave.getBooks().contains(book4));
        assertEquals(3, dave.getBooks().size());
    }

    /**
     * Test method for {@link library.Patron#toString()}.
     */
    @Test
    public void testToString() {
        assertEquals("Dave", dave.toString());
        assertEquals("Paula", paula.toString());
    }

}
