/**
* Bike Component
*
* @author Michael O'Keeffe (116315991)
*/
public abstract class Components{
	private final String desc;
	/**
	* Allocates a component with a variable description
	*
	* @param desc Component description
	*/
	
	public Components(final String desc){
		this.desc = desc;
	}
	/**
	* Takes in component description as a String
	*
	* @return String Component's description
	*/
	public String printDesc(){
		return desc;
	}
	
	
	
	
	
	
	
	
	
	
	
	
}