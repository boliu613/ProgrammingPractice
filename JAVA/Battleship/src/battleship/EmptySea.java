package battleship;

public class EmptySea extends Ship {
	/**
	 * constructor
	 */
	public EmptySea() {
		this.length = 1;
		for (int i = 0; i < 1; i++)
			hit[i] = false;
	}
	
	/**
	 * always return false
	 */
	@Override
	public boolean shootAt(int row, int column) {
		this.hit[0] = true;
		return false;
	}
	
	/**
	 * always return false
	 */
	@Override
	public boolean isSunk() {
		return false;
	}
	
	/**
	 * returns single-character String
	 */
	
	@Override
	public String toString() {
		String s = "-";			
		return s;		
	}
	
	@Override
	public String getShipType() {
		String type = "empty";
		return type;
	}
	
}
