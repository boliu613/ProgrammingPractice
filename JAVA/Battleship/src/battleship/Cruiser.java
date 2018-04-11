package battleship;

public class Cruiser extends Ship {
	/**
	 * constructor
	 */
	public Cruiser() {
		this.length = 3;
		for (int i = 0; i < 3; i++)
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
		String type = "cruiser";
		return type;
	}
	
}
