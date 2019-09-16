// ****************************************************************
// Square.java
//
// Define a Square interface with methods to create and read in
// info for a square matrix and to compute the sum of a row,
// a col, either diagonal, and whether it is magic.
//
// ****************************************************************

import java.util.Scanner;

public interface Square {

    //--------------------------------------
    //create new square of given size   --> Java does not support constructors in interfaces
    // --------------------------------------

    //--------------------------------------
    //return the sum of the values in the given row
    //--------------------------------------
    public int sumRow(int row) ;


    //--------------------------------------
    //return the sum of the values in the given column
    //--------------------------------------
    public int sumCol(int col) ;
    //--------------------------------------

    //return the sum of the values in the main diagonal
    //--------------------------------------
    public int sumMainDiag() ;

    //--------------------------------------
    //return the sum of the values in the other ("reverse") diagonal
    //--------------------------------------
    public int sumOtherDiag() ;

    //--------------------------------------
    //return true if the square is magic (all rows, cols, and diags have
    //same sum), false otherwise
    //--------------------------------------
    public boolean magic() ;

    //--------------------------------------
    //read info into the square from the standard input.
    //--------------------------------------
    public void readSquare(Scanner scan) ;
    //--------------------------------------
    //print the contents of the square, neatly formatted
    //--------------------------------------
    public void printSquare() ;
} 