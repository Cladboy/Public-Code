/**
*Class that states a Hippo's noise
*
* @author Michael O'Keeffe (116315991)
*/
public final class NoiseHippo implements MakeNoise {
    private static final String NOISE = "Wheeze.";
	/**
	*Prints animals noise
	*/
    @Override
    public void makeNoise() {
        System.out.println(NOISE);
    }
}
