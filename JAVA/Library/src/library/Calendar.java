package library;

public class Calendar {
	/**
	 * private instance date to keep track
	 * of how many days have passed 
	 */
	private int date;
	
	/**
	 * constructs class Calendar, set initial date to 0
	 * this can only be called once
	 * time is the same for every one
	 */
	public Calendar() {
		this.date = 0;
	}
	
	/**
	 * returns the current date
	 * @return
	 */
	public int getDate() {
		return this.date;
	}
	
	/**
	 * increment the date, move ahead to next day
	 */
	public void advance() {
		this.date += 1;
	}

}