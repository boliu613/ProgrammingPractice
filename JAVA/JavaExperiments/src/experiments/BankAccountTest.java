package experiments;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class BankAccountTest {
	
	BankAccount testAccount;

	@Before
	public void setUp() throws Exception {
		testAccount = new BankAccount("x", 10);
	}

	@Test
	public void testDeposit() {
		testAccount.deposit(100);
		assertEquals(110.0, testAccount.balance, 0.0);
	}

	@Test
	public void testWithdraw() {
		testAccount.withdraw(5);
		assertEquals(5.0, testAccount.balance, 0.0);
	}

	@Test
	public void testGetBalance() {
		assertEquals(10.0, testAccount.balance, 0.0);
	}

}
