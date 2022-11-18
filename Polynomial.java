//Imports the Jama package which handles Matrix Operations
import Jama.*;

//Polynomial is a class which can recognize a polynomial function of a given degree given at least degree+1 (x,y) points
//Ex: x^2, a 2nd degree polynomial requires 3 (x,y) points
class Polynomial 
{
  //Global Variables

  //Degree of the Polynomial
  int degree;

  //2D Array which will hold both Coefficient and Y values
  double[][] points;

  //2D Array which will hold Coefficient values
  double[][] lArray;

  //2D Array which will hold Y Values
  double[][] rArray;

  //Constructor for the polynomial
  Polynomial(int d, double[][] p)
  {
    //Sets the degrees of the polynomial Ex: x^2 = degree of 2
    degree = d;

    //Initializes a 2D array with degree + 1 Rows and 2 Columns
    points = new double[degree+1][2];

    //This for loop copies the inputted 2D Array values to the initialized 2D Array
    for(int i = 0; i < degree+1; i++) //Loops through Rows
    {
      for(int x = 0; x < 2; x++) //Loops through Columns
      {
        //Sets values for the 2D Array
        points[i][x] = p[i][x];
      }
    }
  }

  //The solve function handles everything from taking in the original points to printing the polynomial function
  public void solve()
  {
    //Initializes a left and right array
    lArray = new double[degree+1][degree+1];
    rArray = new double[degree+1][1];

    //This loop seperates the X value coefficients into a Left Array, and the Y Value Coefficients into a Right array
    for(int q = 0; q < degree+1; q++)
    {
      rArray[q][0] = points[q][1];
      for(int s = degree; s > -1; s--)
      {
        lArray[q][degree-s] = (Math.pow(points[q][0],s));
      }
    }

      //Creates a Matrix representation based on the 2D arrays
      Matrix lMatrix = new Matrix(lArray);
      Matrix rMatrix = new Matrix(rArray);

      //Inverses the Left Matrix
      Matrix invMatrix = lMatrix.inverse();

      //Multipllies the Left Inverted Matrix with the Right Matrix to get the solved Matrix
      Matrix finalMatrix = invMatrix.times(rMatrix);

      //converting the matrix into a form which can be printed on the console
      Matrix finalTransposed = finalMatrix.transpose();
      double[][] valsTransposed = finalTransposed.getArray();

        System.out.print("Answer: ");

      //This loop handles printing out the function
      for(int j = 0; j < valsTransposed[0].length; j++) 
      {    
        //converting and rounding the double vaue to a String which can be manipulated easier Ex: adding "+" or "-"   
        String coefficient = String.valueOf(round(valsTransposed[0][j],10));

        //Adds a "+" in front of positive Coefficients (they normally will have nothing in front if positive) Ex: x^2x turns into x^2+x
        if(round(valsTransposed[0][j],10) > 0 && j != 0)
        {
          coefficient = "+" + coefficient;
        }

        //Instead of writing x^1, this if statement changes it to just x
        if((degree-j) == 1 && round(valsTransposed[0][j],10) != 0)
        {
          //Positive case for a coefficient of 1 Ex: 1x^1 turns into x
          if(round(valsTransposed[0][j],10) == 1)
          {
            System.out.print("+x");
          }
          //Negative Case for a coefficient of -1 Ex: -1x^1 turns into -x
          if(round(valsTransposed[0][j],10) == -1)
          {
            System.out.print("-x");
          }
          //Turns non |1| Coefficient values from ax^1 to ax
          if(round(valsTransposed[0][j],10) != -1 && round(valsTransposed[0][j],10) != 1)
          {
            System.out.print(coefficient+"x");
          }          
        }

        //Checks for the Constant case Ex: cx^0 turns into just c
        if((degree-j) == 0 && round(valsTransposed[0][j],10) != 0)
        {
          System.out.print(coefficient);        
        }

        //Handles all Coefficient of degree 2 and higher, and are not 0 Ex: ax^2, bx^3, cx^4 ...
        if((degree-j) > 1 && round(valsTransposed[0][j],10) != 0)
        {
          //Checks for the positive case of a Coefficient of 1 Ex: 1x^2 -> x^2
          if(round(valsTransposed[0][j],10) == 1)
          {
            //Checks if the first term is positive to get rid of the "+" Ex: +x^2 -> x^2
            if(j == 0)
            {
              System.out.print("x^"+(degree-j));
            }
            //Case for when the Coefficient is not the first term so it keeps the "+"
            if(j != 0)
            {
              System.out.print("+x^"+(degree-j));
            }
          }

          //Checks if the Coefficient is -1 Ex: -1x^2 -> -x^2
          if(round(valsTransposed[0][j],10) == -1)
          {
            System.out.print("-x^"+(degree-j));
          }

          //Handles all other higher degree Coefficients that are not a special case of 0 or |1| or some other special case
          if(round(valsTransposed[0][j],10) != -1 && round(valsTransposed[0][j],10) != 1)
          {
            System.out.print(coefficient+"x^"+(degree-j));
          }
        }
      }
  
  }

  //This function handles rounding double values
  //places is to how many decimals the rounding is accurate to
  public static double round(double value, int places) 
  {
    //The rounding accuracy is currently set to 10 decimal places
    double scale = Math.pow(10, places);
    return Math.round(value * scale) / scale;
  }
} 