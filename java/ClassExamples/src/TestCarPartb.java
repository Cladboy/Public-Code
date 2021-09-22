import java.util.ArrayList;
import java.util.Scanner;

public class TestCarPartb {

		

	
	public static void input() {
		
		Scanner reader = new Scanner(System.in);
		boolean run = true;
		ArrayList <car> carDB = new ArrayList <car>();
		
		while (run == true) {
			System.out.println("What would you like to do? Please enter the caracter in the brackets"
								+ "of the option you would like to choose.");
			System.out.println("New Car (n); Remove Car(r); List Cars(l); Exit(e); ");
			String option = reader.next();
			
			if (option.equals("n")) {
				System.out.println("Car make(String): ");
				String ma = reader.next();
				
				System.out.println("Car model(String): ");
				String m = reader.next();
				
				System.out.println("Car age(Integer): ");
				int a = reader.nextInt();
				
				System.out.println("Car price(Double): ");
				double p = reader.nextDouble();
				
				carDB.add(new car(ma, m, a, p));
				
				System.out.println("Car has been added to the database.\n");
			}
			
			else if (option.equals("r")) {
				System.out.println("What car item would you like to remove?(Integer: 0-n)");
				int index = reader.nextInt();
				
				carDB.remove(index);
			}
			
			else if (option.equals("l")) {
				for(int i = 0; i <= (carDB.size())-1; i++) {
					System.out.println("\n" + i + ".");
					(carDB.get(i)).returnCarStatus();
					System.out.print("\n");
				}
			}
			
			else if (option.equals("e")) {
				run = false;
			}
			
			else {
				System.out.println("Invalid input");
			}
		}
		reader.close();
	}
	
	

	public static void main(String[] args) {
		input();

	}

}
