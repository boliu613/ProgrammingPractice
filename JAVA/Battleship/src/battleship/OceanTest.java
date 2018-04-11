package battleship;

import static org.junit.Assert.*;

import java.util.Random;

import org.junit.Before;
import org.junit.Test;

public class OceanTest {
	Ocean ocean;
	Battleship battleship;
	Cruiser[] cruisers = new Cruiser[2];
	Destroyer[] destroyers = new Destroyer[3];
	Submarine[] submarines = new Submarine[4];

	@Before
	public void setUp() throws Exception {
		ocean = new Ocean();
		battleship = new Battleship();

		for (int i = 0; i < cruisers.length; i++) {
			cruisers[i] = new Cruiser();
		}

		for (int i = 0; i < destroyers.length; i++) {
			destroyers[i] = new Destroyer();
		}

		for (int i = 0; i < submarines.length; i++) {
			submarines[i] = new Submarine();
		}
	}

	@Test
	public void testOcean() {
		Ocean ocean = new Ocean();
		assertTrue(ocean instanceof Ocean);
	}

	@Test
	public void testPlaceAllShipsRandomly() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				assertFalse(ocean.isOccupied(i, j));
			}
		}

		ocean.placeAllShipsRandomly();
		Ship[][] ships = ocean.getShipArray();
		int count = 0;
		int expected = 1*4;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (ships[i][j].getShipType().equals("battleship")) {
					count++;
				}
			}
		}		
		assertEquals(expected, count);

		count = 0;
		expected = 2*3;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (ships[i][j].getShipType().equals("cruiser")) {
					count++;
				}
			}
		}		
		assertEquals(expected, count);

		count = 0;
		expected = 3*2;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (ships[i][j].getShipType().equals("destroyer")) {
					count++;
				}
			}
		}		
		assertEquals(expected, count);

		count = 0;
		expected = 4*1;
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (ships[i][j].getShipType().equals("submarine")) {
					count++;
				}
			}
		}		
		assertEquals(expected, count);
	}

	@Test
	public void testIsOccupied() {
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				assertFalse(ocean.isOccupied(i, j));
			}
		}

		ocean.placeAllShipsRandomly();
		findShips();
		
		//battleship
		for (int i = 0; i < battleship.getLength(); i++) {
			if (battleship.isHorizontal()) {
				assertTrue(ocean.isOccupied(battleship.getBowRow(), battleship.getBowColumn()+i));
			}
			else {
				assertTrue(ocean.isOccupied(battleship.getBowRow()+i, battleship.getBowColumn()));
			}
		}	
		
		//cruisers
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < cruisers[i].getLength(); j++) {
				if (cruisers[i].isHorizontal()) {
					assertTrue(ocean.isOccupied(cruisers[i].getBowRow(), cruisers[i].getBowColumn()+j));
				}
				else {
					assertTrue(ocean.isOccupied(cruisers[i].getBowRow()+j, cruisers[i].getBowColumn()));
				}
			}
		}

		//destroyers
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < destroyers[i].getLength(); j++) {
				if (destroyers[i].isHorizontal()) {
					assertTrue(ocean.isOccupied(destroyers[i].getBowRow(), destroyers[i].getBowColumn()+j));
				}
				else {
					assertTrue(ocean.isOccupied(destroyers[i].getBowRow()+j, destroyers[i].getBowColumn()));
				}
			}
		}
		
		//submarines
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < submarines[i].getLength(); j++) {
				if (submarines[i].isHorizontal()) {
					assertTrue(ocean.isOccupied(submarines[i].getBowRow(), submarines[i].getBowColumn()+j));
				}
				else {
					assertTrue(ocean.isOccupied(submarines[i].getBowRow()+j, submarines[i].getBowColumn()));
				}
			}
		}
	}

	private void findShips(){
		Ship[][] ships = ocean.getShipArray();
		int cruisersIndex = 0;
		int destroyersIndex = 0;
		int submarinesIndex = 0;

		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (ships[i][j].getShipType().equals("battleship")) {
					battleship = (Battleship) ships[i][j];
				}

				if (ships[i][j].getShipType().equals("cruiser")) {
					int k = 0;
					for (k = 0; k < 2; k++) {
						if (cruisers[k] == ships[i][j]) {
							break;
						}
					}
					if (k == 2) {
						cruisers[cruisersIndex] = (Cruiser) ships[i][j];
						cruisersIndex++;
					}
				}

				if (ships[i][j].getShipType().equals("destroyer")) {
					int k = 0;
					for (k = 0; k < 3; k++) {
						if (destroyers[k] == ships[i][j]) {
							break;
						}
					}
					if (k == 3) {
						destroyers[destroyersIndex] = (Destroyer) ships[i][j];
						destroyersIndex++;
					}					
				}

				if (ships[i][j].getShipType().equals("submarine")) {
					int k = 0;
					for (k = 0; k < 4; k++) {
						if (submarines[k] == ships[i][j]) {
							break;
						}
					}
					if (k == 4) {
						submarines[submarinesIndex] = (Submarine) ships[i][j];
						submarinesIndex++;
					}
				}
			}
		}
	}

	@Test
	public void testShootAt() {
		
		int totalFired = 0;
		int totalHit = 0;
		int totalSunk = 0;

		assertEquals(totalFired, ocean.getShotsFired());
		assertEquals(totalHit, ocean.getHitCount());
		assertEquals(totalSunk, ocean.getShipsSunk());

		//hit every place
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				assertFalse(ocean.shootAt(i, j));
				totalFired += 1;
			}
		}		
		
		assertEquals(totalFired, ocean.getShotsFired());
		assertEquals(totalHit, ocean.getHitCount());
		assertEquals(totalSunk, ocean.getShipsSunk());

		ocean.placeAllShipsRandomly();
		findShips();

		//hit battleship
		for (int i = 0; i < battleship.getLength(); i++) {
			if (battleship.isHorizontal()) {
				ocean.shootAt(battleship.getBowRow(), battleship.getBowColumn()+i);
				totalFired += 1;
				totalHit += 1;
			}
			else {
				ocean.shootAt(battleship.getBowRow()+i, battleship.getBowColumn());
				totalFired += 1;
				totalHit += 1;
			}
			assertEquals(totalFired, ocean.getShotsFired());
			assertEquals(totalHit, ocean.getHitCount());
		}
		totalSunk += 1;
		assertEquals(totalSunk, ocean.getShipsSunk());

		//hit first cruise but keep it afloat
		for (int i = 0; i < cruisers[0].getLength()-1; i++) {
			if (cruisers[0].isHorizontal()) {
				ocean.shootAt(cruisers[0].getBowRow(), cruisers[0].getBowColumn()+i);
				totalFired += 1;
				totalHit += 1;
			}
			else {
				ocean.shootAt(cruisers[0].getBowRow()+i, cruisers[0].getBowColumn());
				totalFired += 1;
				totalHit += 1;
			}
			assertEquals(totalFired, ocean.getShotsFired());
			assertEquals(totalHit, ocean.getHitCount());
		}
		assertEquals(totalSunk, ocean.getShipsSunk());

		//hit first cruise but keep it afloat again
		for (int i = 0; i < cruisers[0].getLength()-1; i++) {
			if (cruisers[0].isHorizontal()) {
				ocean.shootAt(cruisers[0].getBowRow(), cruisers[0].getBowColumn()+i);
				totalFired += 1;
				totalHit += 1;
			}
			else {
				ocean.shootAt(cruisers[0].getBowRow()+i, cruisers[0].getBowColumn());
				totalFired += 1;
				totalHit += 1;
			}
			assertEquals(totalFired, ocean.getShotsFired());
			assertEquals(totalHit, ocean.getHitCount());
		}
		assertEquals(totalSunk, ocean.getShipsSunk());

		//hit every place again
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				if (ocean.shootAt(i, j)) {
					totalFired += 1;
					totalHit += 1;
				}
				else{
					totalFired += 1;
				}
			}
		}
		totalSunk += 9;
		assertEquals(totalFired, ocean.getShotsFired());
		assertEquals(totalHit, ocean.getHitCount());
		assertEquals(totalSunk, ocean.getShipsSunk());
		
		//hit every place again
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ocean.shootAt(i, j);
				totalFired += 1;
			}
		}
		assertEquals(totalFired, ocean.getShotsFired());
		assertEquals(totalHit, ocean.getHitCount());
		assertEquals(totalSunk, ocean.getShipsSunk());
	}

	@Test
	public void testIsGameOver() {
		assertFalse(ocean.isGameOver());
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ocean.shootAt(i, j);
			}
		}
		assertFalse(ocean.isGameOver());

		ocean.placeAllShipsRandomly();
		
		//shoot someplace
		for (int i = 0; i < 10; i++) {
			Random rand = new Random();	
			int row = rand.nextInt(10);
			int column = rand.nextInt(10);
			ocean.shootAt(row, column);
		}
		assertFalse(ocean.isGameOver());
		
		//shoot every place
		for (int i = 0; i < 10; i++) {
			for (int j = 0; j < 10; j++) {
				ocean.shootAt(i, j);
			}
		}
		assertTrue(ocean.isGameOver());
	}

}
