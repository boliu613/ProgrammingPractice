import java.util.ArrayList;

public class Purse {

	//instance variable
	private ArrayList<Coin> coins;

	/**
	 * create an empty collection of coins
	 */
	public Purse() {
		coins = new ArrayList<Coin>();
	}

	/**
	 * Add a coin to this purse.
	 * @param c
	 */
	public void addCoin(Coin c){
		this.coins.add(c);
	}

	@Override
	public String toString() {
		String s = "Purse";
		s += coins.toString();
		return s;
	}

}
