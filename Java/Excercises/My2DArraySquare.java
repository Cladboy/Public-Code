// ****************************************************************
//
// Implements the Square interface with methods to create and read in
// info for a square matrix with the data structure 2D array
// and to compute the sum of a row, a col, either diagonal, and whether it is magic.
//
// ****************************************************************



import java.util.Scanner;

public class My2DArraySquare implements Square {
    
    //-------------
    // Attributes 
    //-------------

    //TO-DO
    private int axis;
    private int [][] matrix;



    //--------------------------------------
    //create new square of given size
    //--------------------------------------
    //TO-DO
    public My2DArraySquare(int size){
        axis = size-1;
        matrix = new int[size][size];
    }

    //--------------------------------------
    //return the sum of the values in the given row
    //--------------------------------------
    //TO-DO
    public int sumRow(int row){
        int res = 0;
        for(int i = 0; i <= axis; i++){
            res += matrix[row][i];
        }
        return res;
    }

    //--------------------------------------
    //return the sum of the values in the given column
    //--------------------------------------
    //TO-DO
    public int sumCol(int col){
        int res = 0;
        for(int i = 0; i <= axis; i++){
            res += matrix[i][col];
        }
        return res;
    }


    //--------------------------------------
    //return the sum of the values in the main diagonal
    //--------------------------------------
    //TO-DO
    public int sumMainDiag(){
        int res = 0;
        int xAxis = 0;
        int yAxis = 0;

        while(yAxis<=axis){
            System.out.println(matrix[yAxis][xAxis]);
            res += matrix[yAxis][xAxis];
            xAxis++;
            yAxis++;
        }
        return res;
    }

    //--------------------------------------
    //return the sum of the values in the other ("reverse") diagonal
    //--------------------------------------
    //TO-DO
    public int sumOtherDiag(){
        int res = 0;
        int xAxis = axis;
        int yAxis = 0;

        while(yAxis<=axis){
            System.out.println(matrix[yAxis][xAxis]);
            res += matrix[yAxis][xAxis];
            xAxis--;
            yAxis++;
        }
        return res;
    }

    //--------------------------------------
    //return true if the square is magic (all rows, cols, and diags have
    //same sum), false otherwise
    //--------------------------------------
    //TO-DO
    public boolean magic(){
        int test = sumRow(0);

        for(int i = 0; i <= axis; i++){
            if (sumRow(i) != test){
                return false;
            }
        }
        for(int i = 0; i <= axis; i++){
            if (sumCol(i) != test){
                return false;
            }
        }
        if(sumMainDiag() != test){
            return false;
        }
        else if(sumOtherDiag() != test){
            return false;
        }

        return true;
    }

    //--------------------------------------
    //read info into the square from the standard input.
    //--------------------------------------
    public void readSquare(Scanner scan) {
        for (int row = 0; row < matrix.length; row++)
            for (int col = 0; col < matrix.length; col++)
                matrix[row][col] = scan.nextInt();
    }

    //--------------------------------------
    //print the contents of the square, neatly formatted
    //--------------------------------------
    //TO-DO
    public void printSquare(){
        for(int y = 0; y <= axis; y++){
            for(int x = 0; x <= axis; x++){
                System.out.print(String.format("|%-3d|", matrix[y][x]));
            }
            System.out.print("\n");
        }
    }
} 