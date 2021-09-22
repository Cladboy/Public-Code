/**
*Class for the animal Tiger
*
* @author Michael O'Keeffe (116315991)
*/
public final class Tiger implements Feline {
    private final Feline animal;
	/**
	*States animals Concreteclass and what noise it makes
	*/
    public Tiger() {
        animal = new ConcreteFeline(new NoiseTiger());
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
