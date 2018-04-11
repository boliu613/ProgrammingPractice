package battleship;

public class Battleship extends Ship {
	/**
	 * constructor
	 */
	public Battleship() {
		this.length = 4;
		for (int i = 0; i < 4; i++)
			hit[i] = false;
	}
	
	/**
	 * Returns the length of this ship.
	 */
	@Override
	public int getLength() {
		return this.length;
	}
	
	/**
	 * overriding getShipType method of Ship class
	 */
	@Override
	public String getShipType() {
		String type = "battleship";
		return type;
	}

}
