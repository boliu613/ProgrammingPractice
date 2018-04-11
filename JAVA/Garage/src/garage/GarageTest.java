package garage;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class GarageTest {
	private Garage garage;
	private Car car1, car2;
	@Before
	public void setUp() throws Exception {
		garage = new Garage();
		car1 = new Car("pink", "cadillac");
		car2 = new Car("corvette");
	}

	@Test
	public void testPark() {
		Car[] spots = garage.getGarage();		
		for (Car spot : spots) {
			assertEquals(null, spot);
		}
		
		int spot1 = garage.park(car1);
		
		assertEquals(1, spot1);
		assertEquals(car1, spots[spot1-1]);
		
		int spot2 = garage.park(car2);
		
		assertEquals(2, spot2);
		assertEquals(car2, spots[spot2-1]);
	}

	@Test
	public void testRetrieve() {
		Car[] spots = garage.getGarage();
		for (Car spot : spots) {
			assertEquals(null, spot);
		}
		
		int spot1 = garage.park(car1);
		assertEquals(1, spot1);
		garage.retrieve(spot1);
		assertEquals(null, spots[spot1-1]);
		
		int spot2 = garage.park(car2);
		assertEquals(1, spot2);
		garage.retrieve(spot2);
		assertEquals(null, spots[spot2-1]);
	}

}
