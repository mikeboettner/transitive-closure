/**
 * @author      Michael Boettner
 *              E01474622
 *              COSC 314
 *              Programming Assignment #2
 * @version     1.0
 * Driver Class containing main method to compute the transitive closure of
 * a relation R on a set by using Warshall's algorithm.
 */
package cosc_314_programming_assignment_2;

import java.util.Scanner;

public class Driver {
    
    /**
    * Keyboard input
    */
    static Scanner keyboard = new Scanner(System.in);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        /**
        * Number of elements in the set (user specified)
        */
        int setSize;
        
        /**
        * 2D array to store elements of relation R on the set (user specified)
        */
        boolean[][] myMatrix;
        
        /**
        * Stores each row of the relation as it is entered by the user
        */
        String rowString;
        
        System.out.println("**************************************");
        System.out.println("*   Compute the transitive closure   *");
        System.out.println("*   of a relation R on a set using   *");
        System.out.println("*        Warshall's algorithm        *");
        System.out.println("*                                    *");
        System.out.println("*          Michael Boettner          *");
        System.out.println("**************************************");
        
        System.out.print("\nEnter set size (positive integer): ");
        
        setSize = keyboard.nextInt();
        
        myMatrix = new boolean[setSize][setSize];
        
        System.out.println("\n" + "Enter elements of the relation one row at "
                + "a time (no spaces)");
        
        //loop prompts user to enter each row, one at a time
        for(int i = 0; i < setSize; i++)
        {
            System.out.print("Row " + (i+1) + ": ");
            
            rowString = keyboard.next();
            
            //convert each string character to boolean and store
            for(int j = 0; j < setSize; j++)
            {
                myMatrix[i][j] = rowString.charAt(j) - 48 != 0;
            }
        }
        
        System.out.println("\nW0=");
        
        //print relation R (W0)
        for(int i = 0; i < setSize; i++)
        {
            for(int j = 0; j < setSize; j++)
            {
                if(myMatrix[i][j])
                    System.out.print(" " + 1);
                else
                    System.out.print(" " + 0);
            }
            System.out.println();
        }
        
        //Warshall's algorithm
        for(int k = 0; k < setSize; k++)
        {
            for(int i = 0; i < setSize; i++)
            {
                for(int j = 0; j < setSize; j++)
                {                    
                    /*if the element is already true or the comparisons of
                        column element to row element produce a new path, then
                        set the element to true*/
                    myMatrix[i][j] = ((myMatrix[i][j]) ||
                            ((myMatrix[k][j])&& (myMatrix[i][k])));                                       
                }
            }

            System.out.println("\nW"+(k+1)+"=");
            
            //print the updated matrix
            for(int i = 0; i < setSize; i++)
            {
                for(int j = 0; j < setSize; j++)
                {
                    if(myMatrix[i][j])
                        System.out.print(" " + 1);
                    else
                        System.out.print(" " + 0);
                }
                System.out.println();
            }   
        }
        
        System.out.println("\nW"+setSize+" is the matrix of the transitive "
                + "closure.");  
    }
}

/*
run:
**************************************
*   Compute the transitive closure   *
*   of a relation R on a set using   *
*        Warshall's algorithm        *
*                                    *
*          Michael Boettner          *
**************************************

Enter set size (positive integer): 5

Enter elements of the relation one row at a time (no spaces)
Row 1: 01011
Row 2: 10100
Row 3: 00110
Row 4: 10001
Row 5: 10101

W0=
 0 1 0 1 1
 1 0 1 0 0
 0 0 1 1 0
 1 0 0 0 1
 1 0 1 0 1

W1=
 0 1 0 1 1
 1 1 1 1 1
 0 0 1 1 0
 1 1 0 1 1
 1 1 1 1 1

W2=
 1 1 1 1 1
 1 1 1 1 1
 0 0 1 1 0
 1 1 1 1 1
 1 1 1 1 1

W3=
 1 1 1 1 1
 1 1 1 1 1
 0 0 1 1 0
 1 1 1 1 1
 1 1 1 1 1

W4=
 1 1 1 1 1
 1 1 1 1 1
 1 1 1 1 1
 1 1 1 1 1
 1 1 1 1 1

W5=
 1 1 1 1 1
 1 1 1 1 1
 1 1 1 1 1
 1 1 1 1 1
 1 1 1 1 1

W5 is the matrix of the transitive closure.
BUILD SUCCESSFUL (total time: 20 seconds)
*/