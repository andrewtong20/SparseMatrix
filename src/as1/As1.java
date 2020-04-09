/*
Andrew Tong
Mr. Tomczak
10/8/19
AS1


This program reads in a matrix from a file and determines if it is sparse or abundant. 
If the matrix is sparse, both the original matrix and reduced representation will be printed.
If the matrix is abundant, only the original matrix will be printed.
If the matrix has as many 0s as integers, both the original matrix and reduced representation.
 */
package as1;

import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class As1 {

    public static void main(String[] args) throws FileNotFoundException
    {
        System.out.println("Hi, welcome to Andrew's AS1! \nThis program reads in a matrix from a file and determines if it is sparse or abundant. \n" +
        "If the matrix is sparse, both the original matrix and reduced representation will be printed.\n" +
        "If the matrix is abundant, only the original matrix will be printed.\n" +
        "If the matrix has as many 0s as integers, both the original matrix and reduced representation.");
        
        Scanner consoleScanner=new Scanner(System.in);//scanner to read in user input from console
        System.out.println("\nThe file name should be in the form testn.txt where n is an integer value. \nPlease enter n, the text file integer with the array that you want to check. ");
        
        int integer=consoleScanner.nextInt();

        File inFile=new File("test"+Integer.toString(integer)+".txt");
        
        Scanner fileScanner=new Scanner(inFile);//reads in specified file
        
        
        String input=fileScanner.nextLine();
        
        //Original matrix
        int[] dimensions=dimensionFinder(input);
        final int rowLength=dimensions[0];
        final int columnLength=dimensions[1];
        int[][] matrix=new int[rowLength][columnLength];

        //reading in matrix
        for (int i=0; i<rowLength; i++)
        {
            for (int j=0; j<columnLength; j++)
            {
                matrix[i][j]=fileScanner.nextInt(); 
            }
        }

        //REDUCED MATRIX
        
        //find the number of rows in reduced matrix
        int reducedRow=0;
        for (int i=0; i<rowLength; i++)
        {
            for (int j=0; j<columnLength; j++)
            {
                if (matrix[i][j]!=0)
                {
                    reducedRow++;
                }
            }
        }
        final int reducedColumn=3;//is always set due to nature of sparse matrix
        
        //filling in reduced matrix
        int[][] reducedMatrix= new int[reducedRow][reducedColumn];
        int tempRow=0;//to be able to loop through for multiple rows of reduced matrix
        while (tempRow<reducedRow)
        {    
            for (int i=0; i<rowLength;i++)
            {
                for (int j=0; j<columnLength; j++)
                {
                    if (matrix[i][j]!=0)
                    {
                        reducedMatrix[tempRow][0]=i;
                        reducedMatrix[tempRow][1]=j;
                        reducedMatrix[tempRow][2]=matrix[i][j];
                        tempRow++;
                    }
                }
            }
        }
        
        //comparing efficiencies
        int matrixEfficiency=rowLength*columnLength;
        int reducedEfficiency=reducedRow*reducedColumn;
        
        if (matrixEfficiency==reducedEfficiency)
        {
            System.out.println("Original Matrix:");
            printMatrix(matrix);
            System.out.println("Reduced Representation: ");
            printMatrix(reducedMatrix);
            System.out.println("The original matrix and its reduced representation are equally efficient.");
        }
        else if (matrixEfficiency<reducedEfficiency)
        {
            System.out.println("Original Matrix:");
            printMatrix(matrix);
            System.out.println("The original matrix is abundant.");
        }
        else if (matrixEfficiency>reducedEfficiency)
        {
            System.out.println("Original Matrix:");
            printMatrix(matrix);
            System.out.println("Reduced Representation: ");
            printMatrix(reducedMatrix);
            System.out.println("The original matrix is sparse.");
        }
    } 
    
    public static void printMatrix(int[][] matrix)//I used this printMatrix method to be more efficient
    {
        int rowLength=matrix.length;
        int columnLength=matrix[0].length;
        
        for (int i=0;i<rowLength; i++)
        {
            for (int j=0; j<columnLength; j++)
            {
                System.out.printf("%8d", matrix[i][j]);
            }
            System.out.println();
        }
        
    }  
    public static int[] dimensionFinder(String input)//I think that it's easier to use a method to find the dimensions instead of worrying about which nextInts I should use
    {
        String[] dimensionFromFile=input.trim().split(" ");
        int row=Integer.parseInt(dimensionFromFile[0]);
        int column=Integer.parseInt(dimensionFromFile[1]);
        
        int[] dimensions= {row,column};
        return dimensions;
    }
}
