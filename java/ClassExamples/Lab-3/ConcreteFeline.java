/**
*Concrete Class that implements the Feline Class
*
* @author Michael O'Keeffe (116315991)
*/
public final class ConcreteFeline implements Feline {
    private final Animal animal;
	/**
	*States animals diet(Carnivore) and Roam type(SolitaryAnimal)
	*and takes in what noise it makes from an animal class
	*/
    public ConcreteFeline(final MakeNoise makeNoise) {
        animal = new ConcreteAnimal(new Carnivore(),
		makeNoise, new SolitaryAnimal());
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
