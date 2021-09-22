/**
*Main class to test outputs
*
* @author Michael O'Keeffe (116315991)
*/
public class Main {
	/**
	*Creates Wolf, Hippo and Tiger Animals and prints out their behaviours
	*/
    public static void main(String[] args) {
        Wolf wolf = new Wolf();
        Tiger tiger = new Tiger();
        Hippo hippo = new Hippo();
		
		System.out.println("\n-------- \nWolf:");
        wolf.eat();
        wolf.roam();
        wolf.makeNoise();

		System.out.println("\n-------- \nHippo:");
        hippo.eat();
        hippo.roam();
        hippo.makeNoise();

		System.out.println("\n-------- \nTiger:");
        tiger.eat();
        tiger.roam();
        tiger.makeNoise();
    }
}
