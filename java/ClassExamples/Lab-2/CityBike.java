/**
* A city bike
*
* @author Michael O'Keeffe (116315991)
*/
public class CityBike extends BikeWithLights{
	private Carrier carrier;

	/**
	* Allocates a Carrier and HighFrame to the bike
	*/
	public CityBike(){
		super(new HighFrame());
		carrier = new Carrier();
	}
	/**
	* Prints Carrier component and then calls printComponents superclass
	*/
	@Override
	public void printComponents(){
		System.out.print(carrier + ", ");
		super.printComponents();
	}
}
