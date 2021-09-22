/**
* Bike Light
*
* @author Michael O'Keeffe (116315991)
*/
public abstract class Light extends Components{
	/**
	* Allocates a light component
	*
	* @param type Describes type of light
	*/	
	public Light(final String type){
		super (type + "bulb");
	}
}