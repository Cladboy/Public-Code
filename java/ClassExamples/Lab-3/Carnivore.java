/**
*Class that implements Eat to state animals diet
*
* @author Michael O'Keeffe (116315991)
*/
public final class Carnivore implements Eat {
    private static final String EAT_BEHAVIOUR = "I am a carnivorous Beast. ";
	/**
	*Prints animals eating behaviour
	*/
    @Override
    public void eat() {
        System.out.println(EAT_BEHAVIOUR);
    }
}
