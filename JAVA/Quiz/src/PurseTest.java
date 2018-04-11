import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class PurseTest {
	
	private Purse purse;
	private Coin coin1, coin2, coin3, coin4;

	@Before
	public void setUp() throws Exception {
		purse = new Purse();
		coin1 = new Coin("Quarter");
		coin2 = new Coin("Dime");
		coin3 = new Coin("Nickel");
		coin4 = new Coin("Penny");
	}

	@Test
	public void testPurse() {
		Purse purse = new Purse();
		assertTrue(purse instanceof Purse);
	}

	@Test
	public void testAddCoin() {
		purse.addCoin(coin1);
		String expected = "Purse[Quarter]";
		assertEquals(expected, purse.toString());
		purse.addCoin(coin2);
		purse.addCoin(coin3);
		purse.addCoin(coin4);
		expected = "Purse[Quarter, Dime, Nickel, Penny]";		
		assertEquals(expected, purse.toString());
	}

	@Test
	public void testToString() {
		purse.addCoin(coin1);
		String expected = "Purse[Quarter]";
		assertEquals(expected, purse.toString());
		
		purse.addCoin(coin2);
		purse.addCoin(coin3);
		purse.addCoin(coin4);
		expected = "Purse[Quarter, Dime, Nickel, Penny]";		
		assertEquals(expected, purse.toString());
	}

}
