/**
*Class that states a cat's noise
*
* @author Michael O'Keeffe (116315991)
*/
public final class NoiseCat implements MakeNoise {
    private static final String NOISE = "Meow.";
	/**
	*Prints animals noise
	*/
    @Override
    public void makeNoise() {
        System.out.println(NOISE);
    }
}
