package garage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CarTest {
	private Car car1, car2;
	
	@Before
	public void setUp() throws Exception {
		car1 = new Car("pink", "cadillac");
		car2 = new Car("corvette");
	}

	@Test
	public void testCarStringString() {
		Car car = new Car("pink", "cadillac");
        assertTrue(car instanceof Car);
	}

	@Test
	public void testCarString() {
		Car car = new Car("corvette");
        assertTrue(car instanceof Car);
	}

	@Test
	public void testEqualsObject() {
		Car car3 = new Car("pink", "cadillac");
		Car car4 = new Car("corvette");
		
		assertTrue(car3.equals(car1));
		assertTrue(car4.equals(car2));
	}

	@Test
	public void testGetColor() {
		assertTrue(car1.getColor().equals("pink"));
		assertTrue(car2.getColor().equals("white"));
	}

	@Test
	public void testGetManufacturer() {
		assertTrue(car1.getManufacturer().equals("cadillac"));
		assertTrue(car2.getManufacturer().equals("corvette"));
	}

	@Test
	public void testToString() {
		assertTrue(car1.toString().equals("cadillac: pink"));
		assertTrue(car2.toString().equals("corvette: white"));
	}

}
