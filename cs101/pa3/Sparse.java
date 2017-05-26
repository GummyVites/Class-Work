//Kevin Lee
//1480757
//pa3
//cmps101
//Sparse.java

import java.io.*;
import java.util.Scanner;

class Sparse {
  
  public static void main (String args[]) throws IOException {
    Scanner in = null;
    PrintWriter out = null;
    String line = null;
   String[] token = null;
    int lineNumber = 0;
    
    if(args.length < 2) {
      System.out.printf("Usage: Sparse in out%n");
      System.exit(1);
    }
    
    in = new Scanner(new File(args[0]));
    out = new PrintWriter(new FileWriter(args[1]));
    Matrix A = new Matrix(1);
    Matrix B = new Matrix(1);
    int n = 0;
    int a = 0;
    int b = 0;
    int row;
    int col;
    double val;



    while( in.hasNextLine() ) {
      ++lineNumber;
      line = in.nextLine()+" ";
      token = line.split("\\s+");
  

      if ( lineNumber <= 1 ) {
        n = (int)Integer.parseInt(token[0]);
        a = (int)Integer.parseInt(token[1]);
        b = (int)Integer.parseInt(token[2]);
        A = new Matrix(n);
        B = new Matrix(n);
      }

      else if ( lineNumber > 2 ) {
        if ( lineNumber <= a+2 ) {
          row = (int)Integer.parseInt(token[0]);
          col = (int)Integer.parseInt(token[1]);
          val = (double)Double.parseDouble(token[2]);
          A.changeEntry(row,col,val);
        }

        else if ( lineNumber >= a+4 ) {
          row = (int)Integer.parseInt(token[0]);
          col = (int)Integer.parseInt(token[1]);
          val = (double)Double.parseDouble(token[2]);
          B.changeEntry(row,col,val);
        }
      }
    }  

    

 

    out.println("A has " + A.getNNZ() + " non-zero entries:");
      out.println(A);
      
      out.println("B has " + B.getNNZ() + " non-zero entries:");
      out.println(B);

      out.println("(1.5)*A =");
      out.println(A.scalarMult(1.5));
	
      out.println("A+B =");
      out.println(A.add(B));

      out.println("A+A =");
      out.println(A.add(A));

      out.println("B-A =");
      out.println(B.sub(A));
      
      out.println("A-A =");
      out.println(A.sub(A));
      
      out.println("Transpose(A) =");
      out.println(A.transpose());
      
      out.println("A*B =");
      out.println(A.mult(B));
      
      out.println("B*B =");
      out.println(B.mult(B));
      
      in.close();
      out.close();
   }
}
