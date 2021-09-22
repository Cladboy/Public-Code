/**
*Bike class
*
* @author Michael O'Keeffe (116315991)
*/
public class Bike{
	private final Brakes brakes;
	private final Handlebar handlebar;
	private final Saddle saddle;
	private final Wheels wheels;
	private final Frame frame;
	
	/**
	* Allocates bike Brakes, Handlebar, Saddle and Wheels components
	*
	* @param frame The frame of this bike
	*/
	public Bike(final Frame frame){
		brakes = new Brakes();
		handlebar = new Handlebar();
		saddle = new Saddle();
		wheels = new Wheels();
		this.frame = frame;
		
	}

	/**
	* Prints the components of the bike that have been defined in this class
	*/
	public void printComponents(){
		System.out.print(frame + ", ");
		System.out.print(brakes + ", ");
		System.out.print(handlebar + ", ");
		System.out.print(saddle + ", ");
		System.out.print(wheels + ".");
	}



    
}