/**
*Concrete Class that implements the Animal Class
*
* @author Michael O'Keeffe (116315991)
*/
public final class ConcreteAnimal implements Animal {
    private final Eat eat;
    private final MakeNoise makeNoise;
    private final Roam roam;

    public ConcreteAnimal(final Eat eat, final MakeNoise makeNoise,
        final Roam roam) {
        this.eat = eat;
        this.makeNoise = makeNoise;
        this.roam = roam;
    }

    @Override
    public void eat() {
        eat.eat();
    }

    @Override
    public void makeNoise() {
        makeNoise.makeNoise();
    }

    @Override
    public void roam() {
        roam.roam();
    }
}
