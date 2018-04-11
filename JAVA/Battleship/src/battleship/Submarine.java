package battleship;

public class Submarine extends Ship {
	/**
	 * constructor
	 */
	public Submarine() {
		this.length = 1;
		for (int i = 0; i < 1; i++)
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
		String type = "submarine";
		return type;
	}

}
