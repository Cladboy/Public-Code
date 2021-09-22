
public class MainTest {

	public MainTest() {
		// TODO Auto-generated constructor stub
		Player p0 = new Player("John Doe");
		Player p1 = new Player("Donnie Jones");
		Player p2 = new Player("Enni Briated");
		Player p3 = new Player("Dusty");
		
		Team t0 = new Team("Los Zargos", "Yellow", "Millstreet");
		
		t0.newPlayer(p0);
		t0.newPlayer(p1);
		t0.newPlayer(p2);
		t0.newPlayer(p3);
		
		System.out.println(t0.toString());
		
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new MainTest();
	}

}
