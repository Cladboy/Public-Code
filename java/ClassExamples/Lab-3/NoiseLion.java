/**
*Class that states a Lion's noise
*
* @author Michael O'Keeffe (116315991)
*/
public final class NoiseLion implements MakeNoise {
    private static final String NOISE = "Roar.";
	/**
	*Prints animals noise
	*/
    @Override
    public void makeNoise() {
        System.out.println(NOISE);
    }
}
