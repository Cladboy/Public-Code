/**
 * 
 * @author michael
 *
 */
public class car {
	
	private String make;
	private String model;
	private int age;
	private double price;
	
	//Constructor
	public car(String ma, String m, int a, double p) {
		// TODO Auto-generated constructor stub
		this.make = ma;
		this.model = m;
		this.age = a;
		this.price = p;
	}
	
	//Getters
	public String getMake() {
		return this.make;
	}
	public String getModel() {
		return this.model;
	}
	
	public int getAge() {
		return this.age;
	}
	
	public double getPrice() {
		return this.price;
	}
	
	//Setters
	public void setMake(String ma) {
		this.make = ma;
	}
	public void setModel(String m) {
		this.model = m;
	}
	
	public void setAge(int a) {
		this.age = a;
	}
	
	public void setPrice(double p) {
		this.price = p;
	}
	
	public void returnCarStatus() {
		String carState = "a Banger";
		if (this.age < 3){
			carState = "Shiny new.";
		}
		else if(this.age < 6){
			carState= "Slightly worn.";
		}
		else if(this.age < 8) {
			carState = "Past its best.";
		}
		else if(this.age < 10) {
			carState = "Showing signs of age.";
		}
		System.out.println(this.make +" "+ this.model + " is " + this.age + " years old and is " + carState);
	}

	
}
