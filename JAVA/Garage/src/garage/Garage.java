package garage;

public class Garage {
	
	private Car[] spots;
	public Garage() {
		this.spots = new Car[500];
		for (int i = 0; i < spots.length; i++) {
			spots[i] = null;
		}
	}
	
	/**
	 * park the car in the first available spot. 
	 * This method returns the spot number where the car gets parked. 
	 * @param car
	 * @return
	 */
	public int park(Car car){
		for (int i = 0; i < this.spots.length; i++) {
			if (this.spots[i] == null) {
				this.spots[i] = car;
				return i+1;
			}
		}
		
		System.out.println("Sorry. Garage is full.");
		return 0;
	}
	
	/**
	 * gets the car parked at a particular spot. 
	 * @param spot
	 */
	public void retrieve(int spot){
		if (this.spots[spot-1] == null) {
			System.out.println("No car in that spot.");
		}
		else {
			this.spots[spot-1] = null;
			System.out.println("Retrieve success.");
		}
	}
	
	public Car[] getGarage(){
		return this.spots;
	}
	
	public static void main(String[] args) {
		Car car1 = new Car("pink", "cadillac");
		Car car2 = new Car("red", "corvette");
		Car car3 = new Car("black", "mercedes");
		Garage garage = new Garage();
		int spot1 = garage.park(car1);
		int spot2 = garage.park(car2);
		garage.retrieve(spot1);
		int spot3 = garage.park(car3);
		System.out.println(spot3);
		
		garage.retrieve(3);
	}
}
