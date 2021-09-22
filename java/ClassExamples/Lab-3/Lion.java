/**
*Class for the animal Lion
*
* @author Michael O'Keeffe (116315991)
*/
public final class Lion implements Feline {
    private final Feline animal;
	/**
	*
	*
	*
	*/
    public Lion() {
        animal = new ConcreteFeline(new NoiseLion());
    }

    @Override
    public void eat() {
        animal.eat();
    }

    @Override
    public void makeNoise() {
        animal.makeNoise();
    }

    @Override
    public void roam() {
        animal.roam();
    }
}
