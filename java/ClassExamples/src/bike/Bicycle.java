package bike;

public class Bicycle {
	private int speed;
	private String maker;
	private String type;
	
	public Bicycle() {
		
	}
	public Bicycle(int s, String m, String t) {
		this.speed = s;
		this.maker = m;
		this.type = t;
	}
	//Getters
	public int getSpeed() {
		return this.speed;
	}
	
	public String getMaker() {
		return this.maker;
	}
	
	public String getType() {
		return this.type;
	}
	
	//Setters
	public void setSpeed(int s) {
		this.speed = s;
	}
	
	public void setMaker(String m) {
		this.maker = m;
	}
	
	public void setType(String t) {
		this.type = t;
	}
	
	public void print() {
		System.out.print(
						"\nSpeed: " + this.speed +
						"\nMaker: " + this.maker +
						"\nType: " + this.type
						
						);
	}
}
