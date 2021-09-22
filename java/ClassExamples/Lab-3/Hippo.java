/**
*Class for the animal Hippo
*
* @author Michael O'Keeffe (116315991)
*/
public final class Hippo implements Animal {
    private final Animal animal;
	/**
	*States animals diet(Herbivore) and Roam type(PackAnimal)
	*and what noise it makes
	*/
    public Hippo() {
        animal = new ConcreteAnimal(new Herbivore(), 
		new NoiseHippo(), new PackAnimal());
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
