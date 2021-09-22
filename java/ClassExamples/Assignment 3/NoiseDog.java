/**
*Class that states a Dog's noise
*
* @author Michael O'Keeffe (116315991)
*/
public final class NoiseDog implements MakeNoise {
    private static final String NOISE = "Woof.";
	/**
	*Prints animals noise
	*/
    @Override
    public void makeNoise() {
        System.out.println(NOISE);
    }
}
