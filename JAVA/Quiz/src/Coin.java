
public class Coin {
	//instance variables
	private String name;
	private int value;
	/**
	 * this method also sets the value appropriately.
	 * @param name
	 */
	public Coin(String name) {
		if (name.equals("Quarter")) {
			this.name = name;
			this.value = 25;
		}
		else if (name.equals("Dime")) {
			this.name = name;
			this.value = 10;
		}
		else if (name.equals("Nickel")) {
			this.name = name;
			this.value = 5;
		}
		else if (name.equals("Penny")) {
			this.name = name;
			this.value = 1;
		}
		else
		{
			throw new IllegalArgumentException("Illegal Argument.");
		}		
	}

	/**
	 * returns just the name of the coin
	 */	
	@Override
	public String toString() {
		return this.name;
	}

}


