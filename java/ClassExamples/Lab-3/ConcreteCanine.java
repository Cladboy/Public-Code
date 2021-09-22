/**
*Concrete Class that implements the Canine Class
*
* @author Michael O'Keeffe (116315991)
*/
public final class ConcreteCanine implements Canine {
    private final Animal animal;
	/**
	*States animals diet(Carnivore) and Roam type(PackAnimal)
	*and takes in what noise it makes from an animal class
	*/
    public ConcreteCanine(final MakeNoise makeNoise) {
        animal = new ConcreteAnimal(new Carnivore(),
		makeNoise, new PackAnimal());
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
