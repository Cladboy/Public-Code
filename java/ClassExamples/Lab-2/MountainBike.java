/**
*Mountain bike class
*
* @author Michael O'Keeffe (116315991)
*/
public class MountainBike extends Bike{
	/**
	*Allocates Low Frame to Bike
	*/	
	public MountainBike(){
		super(new LowFrame());
	}
}