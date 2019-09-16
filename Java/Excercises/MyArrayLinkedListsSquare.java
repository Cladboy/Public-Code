// ****************************************************************
//
// Implements the Square interface with methods to create and read in
// info for a square matrix with the data structure array of linked lists
// and to compute the sum of a row, a col, either diagonal, and whether it is magic.
//
// ****************************************************************

import java.util.LinkedList;
import java.util.Scanner;
import java.util.LinkedList;

public class MyArrayLinkedListsSquare implements Square{
   
    //-------------
    // Attributes 
    //-------------
    // TO-DO
    private LinkedList<Integer> [] matrix;
    

    //--------------------------------------
    //create new square of given size
    //--------------------------------------
     // TO-DO
     public MyArrayLinkedListsSquare(int size){
        matrix = new LinkedList [size];
        for (int i = 0; i < size; i++){
            matrix[i] = new LinkedList<Integer>();
        }
     }

    //--------------------------------------
    //return the sum of the values in the given row
    //--------------------------------------
    // TO-DO
    public int sumRow(int row){
        int res = 0;
        LinkedList<Integer> temp = matrix[row];
        for(int i = 0; i < matrix.length; i++){
            res += temp.get(i);
        }
        return res;
    }

    //--------------------------------------
    //return the sum of the values in the given column
    //--------------------------------------
    //TO-DO
    public int sumCol(int col){
        int res = 0;
        for(int i = 0; i < matrix.length; i++){
            res += matrix[i].get(col);
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

        while(yAxis<matrix.length){
            res += matrix[yAxis].get(xAxis);
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
        int xAxis = matrix.length;
        int yAxis = 0;

        while(xAxis>0){
            res += matrix[yAxis].get(xAxis-1);
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

        for(int i = 0; i < matrix.length; i++){
            if (sumRow(i) != test){
                return false;
            }
        }
        for(int i = 0; i < matrix.length; i++){
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
                matrix[row].add(new Integer(scan.nextInt()));
    }


    //--------------------------------------
    //print the contents of the square, neatly formatted
    //--------------------------------------
   // TO-DO
    public void printSquare(){
        for(int y = 0; y < matrix.length; y++){
            for(int x = 0; x < matrix.length; x++){
                System.out.print(String.format("|%-3d|", matrix[y].get(x)));
            }
        System.out.print("\n");
        }
    }
}