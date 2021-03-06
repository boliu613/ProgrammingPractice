package battleship;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ShipTest {
	
	Ship battleship;
	Ship cruiser;
	Ship destroyer;
	Ship submarine;
	Ship emptysea;
	Ocean ocean;

	@Before
	public void setUp() throws Exception {
		battleship = new Battleship();
		cruiser = new Cruiser();
		destroyer = new Destroyer();
		submarine = new Submarine();
		emptysea = new EmptySea();
		ocean = new Ocean();
	}

	@Test
	public void testGetLength() {
		assertEquals(4, battleship.getLength());
		assertEquals(3, cruiser.getLength());
		assertEquals(2, destroyer.getLength());
		assertEquals(1, submarine.getLength());
		assertEquals(1, emptysea.getLength());
	}

	@Test
	public void testGetAndSetBowRow() {
		battleship.setBowRow(0); 
		assertEquals(0, battleship.getBowRow());
		cruiser.setBowRow(1);
		assertEquals(1, cruiser.getBowRow());
		destroyer.setBowRow(2);
		assertEquals(2, destroyer.getBowRow());
		submarine.setBowRow(3);
		assertEquals(3, submarine.getBowRow());
		emptysea.setBowRow(9);
		assertEquals(9, emptysea.getBowRow());
	}

	@Test
	public void testGetAndSetBowColumn() {
		battleship.setBowColumn(0);
		assertEquals(0, battleship.getBowColumn());
		cruiser.setBowColumn(1);
		assertEquals(1, cruiser.getBowColumn());
		destroyer.setBowColumn(2);
		assertEquals(2, destroyer.getBowColumn());
		submarine.setBowColumn(3);
		assertEquals(3, submarine.getBowColumn());
		emptysea.setBowColumn(9);
		assertEquals(9, emptysea.getBowColumn());
	}

	@Test
	public void testIsAndSetHorizontal() {
		battleship.setHorizontal(true);
		assertTrue(battleship.isHorizontal());
		battleship.setHorizontal(false);
		assertFalse(battleship.isHorizontal());
		
		cruiser.setHorizontal(true);
		assertTrue(cruiser.isHorizontal());
		cruiser.setHorizontal(false);
		assertFalse(cruiser.isHorizontal());
		
		destroyer.setHorizontal(true);
		assertTrue(destroyer.isHorizontal());
		destroyer.setHorizontal(false);
		assertFalse(destroyer.isHorizontal());
		
		submarine.setHorizontal(true);
		assertTrue(submarine.isHorizontal());
		submarine.setHorizontal(false);
		assertFalse(submarine.isHorizontal());
		
		emptysea.setHorizontal(true);
		assertTrue(emptysea.isHorizontal());
		emptysea.setHorizontal(false);
		assertFalse(emptysea.isHorizontal());
	}


	@Test
	public void testGetShipType() {
		assertEquals("battleship", battleship.getShipType());
		assertEquals("cruiser", cruiser.getShipType());
		assertEquals("destroyer", destroyer.getShipType());
		assertEquals("submarine", submarine.getShipType());
		assertEquals("empty", emptysea.getShipType());
	}

	@Test
	public void testOkToPlaceShipAt() {
		assertFalse(battleship.okToPlaceShipAt(0, 10, true, ocean));
		
		assertFalse(battleship.okToPlaceShipAt(0, 7, true, ocean));
		assertFalse(battleship.okToPlaceShipAt(7, 0, false, ocean));
		
		assertTrue(battleship.okToPlaceShipAt(1, 1, true, ocean));
		battleship.placeShipAt(1, 1, true, ocean);
		
		assertFalse(cruiser.okToPlaceShipAt(1, 2, true, ocean));
		assertFalse(cruiser.okToPlaceShipAt(1, 2, false, ocean));
		
		assertFalse(destroyer.okToPlaceShipAt(0, 1, true, ocean));
		assertFalse(destroyer.okToPlaceShipAt(2, 1, false, ocean));
		assertTrue(destroyer.okToPlaceShipAt(3, 1, true, ocean));
		
		assertFalse(submarine.okToPlaceShipAt(0, 0, true, ocean));
		assertFalse(submarine.okToPlaceShipAt(2, 0, false, ocean));
		assertFalse(submarine.okToPlaceShipAt(0, 5, true, ocean));
		assertFalse(submarine.okToPlaceShipAt(2, 5, false, ocean));
	}

	@Test
	public void testPlaceShipAt() {
		Ship ships[][] = ocean.getShipArray();
		
		battleship.placeShipAt(1, 1, true, ocean);
		
		assertTrue(ships[1][1] instanceof Battleship);
		assertTrue(ships[1][2] instanceof Battleship);
		assertTrue(ships[1][3] instanceof Battleship);
		assertTrue(ships[1][4] instanceof Battleship);
		assertFalse(ships[1][5] instanceof Battleship);
		
		cruiser.placeShipAt(3, 1, true, ocean);
		
		assertTrue(ships[3][1] instanceof Cruiser);
		assertTrue(ships[3][2] instanceof Cruiser);
		assertTrue(ships[3][3] instanceof Cruiser);
		assertFalse(ships[3][4] instanceof Cruiser);
		
		destroyer.placeShipAt(5, 1, false, ocean);
		
		assertTrue(ships[5][1] instanceof Destroyer);
		assertTrue(ships[6][1] instanceof Destroyer);
		assertFalse(ships[7][1] instanceof Destroyer);
		
		submarine.placeShipAt(9, 1, true, ocean);
		
		assertTrue(ships[9][1] instanceof Submarine);
		assertFalse(ships[9][2] instanceof Submarine);
		
		emptysea.placeShipAt(9, 9, false, ocean);
		assertTrue(ships[9][9] instanceof EmptySea);
		
	}

	@Test
	public void testShootAt() {
		battleship.placeShipAt(1, 1, true, ocean);
		
		assertFalse(battleship.shootAt(2, 1));
		
		assertTrue(battleship.shootAt(1, 1));
		assertTrue(battleship.shootAt(1, 1));
		assertTrue(battleship.shootAt(1, 2));
		assertTrue(battleship.shootAt(1, 3));
		assertTrue(battleship.shootAt(1, 4));
		
		assertFalse(battleship.shootAt(1, 1));
		assertFalse(battleship.shootAt(1, 2));
		assertFalse(battleship.shootAt(1, 3));
		assertFalse(battleship.shootAt(1, 4));
		
		emptysea.setBowRow(9);
		emptysea.setBowColumn(9);
		assertFalse(emptysea.shootAt(9, 9));
	}

	@Test
	public void testIsSunk() {
		battleship.setBowRow(1);
		battleship.setBowColumn(1);
		battleship.setHorizontal(true);
		
		assertFalse(battleship.isSunk());
		
		battleship.shootAt(1, 1);
		assertFalse(battleship.isSunk());
		battleship.shootAt(1, 2);
		assertFalse(battleship.isSunk());
		battleship.shootAt(1, 3);
		assertFalse(battleship.isSunk());
		battleship.shootAt(1, 4);
		assertTrue(battleship.isSunk());
		
		emptysea.hit[0] = true;
		emptysea.hit[1] = true;
		emptysea.hit[2] = true;
		emptysea.hit[3] = true;
		assertFalse(emptysea.isSunk());
		
	}

	@Test
	public void testToString() {
		battleship.setBowRow(1);
		battleship.setBowColumn(1);
		battleship.setHorizontal(true);
		
		assertEquals("S", battleship.toString());
		battleship.shootAt(1, 1);
		assertEquals("S", battleship.toString());
		battleship.shootAt(1, 2);
		assertEquals("S", battleship.toString());
		battleship.shootAt(1, 3);
		assertEquals("S", battleship.toString());
		battleship.shootAt(1, 4);
		assertEquals("x", battleship.toString());
		
		assertEquals("-", emptysea.toString());
	}

}
