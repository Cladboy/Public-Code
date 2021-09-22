/**
* Bike Frame
*
* @author Michael O'Keeffe (116315991)
*/
public abstract class Frame extends Components{
	private static final String FrameDesc = "Frametype: ";
	/**
	* Allocates a frame component with the types description
	*
	* @param type Describes frame type
	*/
	public Frame(final String type){
		super(FrameDesc + type);
	}
}