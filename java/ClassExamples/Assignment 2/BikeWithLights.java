/**
* Bike with lights
*
* @author Michael O'Keeffe (116315991)
*/
public abstract class BikeWithLights extends Bike{
	private FrontLight frontLight;
	private RearLight rearLight;
	/**
	* Allocates the bike a FrontLight and RearLight component.
	*
	* @param frame the frame of this bike
	*/
	public BikeWithLights(Frame frame){
		super(frame);
		frontLight = new FrontLight();
		rearLight = new RearLight();
	}
	/**
	* Prints frontLight and rearLight and then calls printComponents superclass
	*/
	public void printComponents(){
		System.out.print(frontLight + ", ");
		System.out.print(rearLight + ", ");
		super.printComponents();
	}
}