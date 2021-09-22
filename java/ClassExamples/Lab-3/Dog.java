/**
*Class for the animal dog
*
* @author Michael O'Keeffe (116315991)
*/
public final class Dog implements Canine {
    private final Canine animal;
	/**
	*States animals Concreteclass and what noise it makes
	*/
    public Dog() {
        animal = new ConcreteCanine(new NoiseDog());
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
