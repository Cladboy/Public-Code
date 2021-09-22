
public class Team {
	private String name;
	private String gersey;
	private String location;
	private Player players[];
	private int playerCount;
	
	public Team(String n, String g, String l) {
		this.name = n;
		this.gersey = g;
		this.location = l;
		this.players = new Player[20];
		this.playerCount = 0;
	}
	
	//Getters
	public String getName() {
		return this.name;
	}
	
	public String getGersey() {
		return this.gersey;
	}
	
	public String getLocation() {
		return this.location;
	}
	
	//Setters
	public void setName(String n) {
		this.name = n;
	}
	
	public void setGersey(String g) {
		this.gersey = g;
	}
	
	public void setLocation(String l) {
		this.location = l;
	}
	
	public String toString() {
		String theString = "";
		theString = this.name + " who have " + this.gersey
				+ " and lives in " + this.location + "\n"
				+ this.getPlayers();
		
		return theString;
		
		
	}
	
	//Array stuff
	public void newPlayer(Player p){
		this.players[this.playerCount] = p;
		this.playerCount = this.playerCount +1;
	}
	public String getPlayers() {
		String theString = "";
		for (int i = 0; i < playerCount; i++) {
			Player p = this.players[i];
			String tempString =
			"\nName: " + p.getName() +
			"\nCode: " + p.getCode() +
			"\n----------------------";
			theString = theString + tempString;
		}
		return theString;
	}

}
