// ****************************************************************
// SquareTest.java
//
// Uses the Square class to read in square data and tell if
// each square is magic.
//
// ****************************************************************
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
public class SquareTest {
	
	 public static void main(String[] args) throws IOException
	 {
		 int type = 0; // Change this to false for the dynamic implementation
		 
		 //Pass parameters by console
		 if (args.length > 0){
			 type = Integer.parseInt(args[0]);
		 }
		 
		 Scanner scan = new Scanner(new File("src/magicData"));
		 int count = 1; //count which square we're on
		 int size = scan.nextInt(); //size of next square
		 Square square = null;
		 //Expecting -1 at bottom of input file
		 while (size != -1)
		 {
			 //create a new Square of the given size
			
			 if(type==0){
				System.out.println("\n******************** My2DArraySquare ********************\n");
				square = new My2DArraySquare(size);	
				//System.out.println("\n******************** MyArrayLinkedListsSquare ********************\n");
				//square = new MyArrayLinkedListsSquare(size);
			 }
			else{
				System.out.println("\n******************** MyArrayLinkedListsSquare ********************\n");
				square = new MyArrayLinkedListsSquare(size);
			}
			//square = new My2DArraySquare(size);	
			 //call its read method to read the values of the square
			 square.readSquare(scan);
			 
			 System.out.println("\n******** Square " + count + " ********\n");
			 
              //TO-DO:
			 //print the square
			 square.printSquare();
			 //print the sums of its rows
			 System.out.println("\n**** Sum of Rows ****");
			 for(int i = 0; i < size; i++){
				 System.out.println("Sum of row "+(i+1)+": "+square.sumRow(i));
			 }
			 //print the sums of its columns
			 System.out.println("\n**** Sum of Columns ****");
			 for(int i = 0; i < size; i++){
				System.out.println("Sum of col "+(i+1)+": "+square.sumCol(i));
			}
			 //print the sum of the main diagonal
			 System.out.println("\nSum of Diagonal (\\): "+square.sumMainDiag());

			 //print the sum of the other diagonal
			 System.out.println("Sum of Other Diagonal (/): "+square.sumOtherDiag());

			 //determine and print whether it is a magic square

			 if(square.magic() == true){
				System.out.println("This is a magic square.");
			 }
			 else{
				 System.out.println("This is not a magic square.");
			 }
			 

			 //get size of next square
			 size = scan.nextInt();
			 count++;
		 }
	 }
} 