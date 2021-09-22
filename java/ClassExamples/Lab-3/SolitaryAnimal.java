/**
*Class that states an animals Roam status
*
* @author Michael O'Keeffe (116315991)
*/
public final class SolitaryAnimal implements Roam {
    private static final String ROAM_BEHAVIOUR = "I'm roaming alone.";
	/**
	*Prints animals Roaming Behaviour
	*/
    @Override
    public void roam() {
        System.out.println(ROAM_BEHAVIOUR);
    }
}
