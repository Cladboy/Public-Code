package bike;
/**
 * @author michael
 *
 */
public class TestBicycle {
	public static void main(String[] args) {
		Bicycle bike1 = new Bicycle();//Creates a new bike object
		bike1.setSpeed(100);//Sets the bike object's speed
		bike1.setMaker("Raleigh");//Sets the bike object's Maker
		bike1.setType("Mountain Bike");//Sets the bike object's type
		bike1.print();
		
		Bicycle bike2 = new Bicycle(10, "Home made", "Bone Shaker");
		bike2.print();
	}
	
}