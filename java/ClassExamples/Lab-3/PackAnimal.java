/**
*Class that states an animals Roam status
*
* @author Michael O'Keeffe (116315991)
*/
public final class PackAnimal implements Roam {
    private static final String ROAM_BEHAVIOUR = "I'm roaming in a pack.";
	/**
	*Prints animals Roaming Beahaviour
	*/
    @Override
    public void roam() {
        System.out.println(ROAM_BEHAVIOUR);
    }
}
