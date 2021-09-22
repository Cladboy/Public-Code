
public class Player {
	
	public String name;
	public String code;
	
	public Player() {
	}
	public Player(String n) {
		this.name = n;
		String tempCode = Character.toString((n.charAt(0)));
		for (int i=0; i <= (n.length())-1; i++) {
			if(n.charAt(i)==' ' || n.charAt(i)=='\'') {
				tempCode = tempCode + (Character.toString(n.charAt(i)));
			}
		}
		this.code = tempCode;
	}
	
	//Getters
	public String getName() {
		return this.name;
	}
	
	public String getCode() {
		return this.code;
	}
	
	//Setters
	public void setName(String n) {
		this.name = n;	
	}
	
	public void setCode(String c) {
		this.code = c;
	}
	
	public String toString() {
		String theString = "Name: ";
		theString = theString + this.name 
					+ "\n Code: " + this.code;
		return theString;
	}
	
	public void print() {
		System.out.println(toString());
	}
}
