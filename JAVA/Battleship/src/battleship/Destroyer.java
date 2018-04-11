package battleship;

public class Destroyer extends Ship {
	/**
	 * constructor
	 */
	public Destroyer() {
		this.length = 2;
		for (int i = 0; i < 2; i++)
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
		String type = "destroyer";
		return type;
	}

}
