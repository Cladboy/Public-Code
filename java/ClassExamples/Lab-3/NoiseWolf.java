/**
*Class that states a Wolf's noise
*
* @author Michael O'Keeffe (116315991)
*/
public final class NoiseWolf implements MakeNoise {
    private static final String NOISE = "Howl.";
	/**
	*Prints animals noise
	*/
    @Override
    public void makeNoise() {
        System.out.println(NOISE);
    }
}
