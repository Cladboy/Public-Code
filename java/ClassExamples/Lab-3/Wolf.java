/**
*Class for the animal Wolf
*
* @author Michael O'Keeffe (116315991)
*/
public final class Wolf implements Canine {
    private final Canine animal;
	/**
	*States animals Concrete class and what noise it makes
	*/
    public Wolf() {
        animal = new ConcreteCanine(new NoiseWolf());
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
