//Roots.java
//Kevin Lee
//1480757
//pa4
//finding roots

import java.util.*;

class Roots {
	public static int degree;
	public static double[] diffArray;
	public static int coeff;
	public static double[] poly;
	
	public static void main( String[] args ){
		double a;
		double b;
		double resolution = 0.01;
		double tolerance = 0.0000001;
		double threshold = 0.001;
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter the degree: ");
		degree = sc.nextInt();
		coeff = degree + 1;
		System.out.print("Enter " + coeff + " coefficients: ");
		double[] C = new double[coeff];
		for(int i = 0; i < coeff; i++) {
		C[i] = sc.nextDouble();
		}

		System.out.print("Enter the left and right endpoints: ");
		a = sc.nextDouble();
		b = sc.nextDouble();
		double[] diff = new double[coeff];
		diff = diff (C);
		int counter = 0;
		for (double i = a; i < b; i+= resolution){
		if(poly(C,i)* poly(C,i+resolution) < 0) 
	
		{
		double root = findRoot(C,i,i+resolution,tolerance);
		counter++;
		System.out.printf("Root found at: %.5f\n", root);
		continue;
		}

		else if (i+resolution>=b && counter ==0)
		{
		System.out.println("No roots were found in the specified range.");
		}
		}
	}
	
static double poly(double[] C, double x)
	{
	double sum = 0;
	for (int i = 0; i < C.length; i++)
	{
	sum = C[i]*(Math.pow(x, i) )+ sum;
	}
	return sum;
	}
	
	static double[] diff(double[] C){ 
	int e = C.length - 1;
	double diffArray[] = new double[e];
	for (int i = 0; i < C.length-1; i++)
	{
	diffArray[i] = (i+1)*C[i+1];
	}
	return(diffArray);
	}

	static double findRoot(double[] C, double a, double b, double tolerance){
	double mid = a;
	while ( (b - a) > tolerance ) {
	mid = (a + b) / 2.0;
	if (poly(C, a)*poly(C,mid) < 0) {
	b = mid;
	}
	else
	a = mid;
	}
	return mid;
 }
}
