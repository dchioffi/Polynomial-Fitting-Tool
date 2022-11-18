//Hit run to start

//Imported Classes
import java.util.Scanner;
import Jama.*;

//The Main Class is used to run and test the Polynomial Class
class Main 
{
  public static void main(String[] args) 
  {

        System.out.println("Enter the degree of the Polynomial: ");

        //Creates a Scanner
        Scanner input = new Scanner(System.in);

        //Sets the degrees as the Next Integer that is inputed
        int n = input.nextInt();

        System.out.println("Enter the points in the given order x1 Enter y1 Enter x2 Enter y2 ...");

        //Initializes a 2D Array of Degree + 1 Rows and 2 Columns
        double[][] p = new double[n+1][2];

        //This double for loop sets the values for the 2D Array
        for(int i=0; i<(n+1); i++)
        {
            for(int j=0; j<2; j++)
            {
                p[i][j] = input.nextDouble();
            }
        }

        //Creates a Polynomial Object with the given degree and points
        Polynomial test = new Polynomial(n,p);

        //Calls the solve function in the Polynomial Class which sovles and prints the polynomial equation
        test.solve();
      
  }
}