package in.javamumbaigroup.backingbeans;

public class Car {

	private int year;
	private String manufacturer;
	private String color;

	private String model;

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getManufacturer() {
		return manufacturer;
	}

	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Car(int year, String manufacturer, String color, String model) {
		super();
		this.year = year;
		this.manufacturer = manufacturer;
		this.color = color;
		this.model = model;
	}
	
	
}
