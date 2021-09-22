/**
*Class that states a Tiger's noise
*
* @author Michael O'Keeffe (116315991)
*/
public final class NoiseTiger implements MakeNoise {
    private static final String NOISE = "Rawr.";
	/**
	*Prints animals noise
	*/
    @Override
    public void makeNoise() {
        System.out.println(NOISE);
    }
}
