package garage;

public class Car {
	
	private String color;
	private String manufacturer;

	public Car(String color, String manufacturer) {
		this.color = color;
		this.manufacturer = manufacturer;
	}

	public Car(String manufacturer) {
		this.color = "white";
		this.manufacturer = manufacturer;
	}
	
	@Override
	public boolean equals(Object obj) {
		Car car = (Car) obj;
		return this.color.equals(car.getColor()) && this.manufacturer.equals(car.getManufacturer());
	}
	
	
	public String getColor(){
		return this.color;
	}
	
	public String getManufacturer(){
		return this.manufacturer;
	}
	
	
	@Override
	public String toString() {
		return this.manufacturer + ": " + this.color;
	}
}
