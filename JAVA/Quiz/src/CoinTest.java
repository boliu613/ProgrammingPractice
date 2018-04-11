import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


public class CoinTest {
	
	private Coin coin1, coin2, coin3, coin4;
	
	@Before
	public void setUp() throws Exception {
		coin1 = new Coin("Quarter");
		coin2 = new Coin("Dime");
		coin3 = new Coin("Nickel");
		coin4 = new Coin("Penny");
	}

	@Test
	public void testCoin() {
		Coin coin = new Coin("Quarter");
        assertTrue(coin instanceof Coin);
	}

	@Test
	public void testToString() {
		assertEquals("Quarter", coin1.toString());
		assertEquals("Dime", coin2.toString());
		assertEquals("Nickel", coin3.toString());
		assertEquals("Penny", coin4.toString());
	}

}
