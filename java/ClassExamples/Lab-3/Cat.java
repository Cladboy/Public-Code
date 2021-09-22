/**
*Class for the animal Cat
*
* @author Michael O'Keeffe (116315991)
*/

public final class Cat implements Feline {
    private final Feline animal;
	/**
	*States animals Concreteclass and what noise it makes
	*/
    public Cat() {
        animal = new ConcreteFeline(new NoiseCat());
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
