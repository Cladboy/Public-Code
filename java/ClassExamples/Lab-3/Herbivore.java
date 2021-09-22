/**
*Class that implements Eat to state animals diet
*
* @author Michael O'Keeffe (116315991)
*/
public final class Herbivore implements Eat {
    private static final String 
	EAT_BEHAVIOUR = "I'm eating plants and stuff.";
	/**
	*Prints animals eating behaviour
	*/
    @Override
    public void eat() {
        System.out.println(EAT_BEHAVIOUR);
    }
}
