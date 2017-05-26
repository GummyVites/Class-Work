// GCD.java
// Kevin Lee
// 1480757
// pa3
// Finds the greatest common denominator of two numbers.

 import java.util.Scanner;

 class GCD{
	 public static void main( String[] args ){
		 int a,b,temp;
		 String GCD;
		 Scanner sc = new Scanner(System.in);
		 System.out.print("Enter a positive integer: ");

		 while(true){
		 while( !sc.hasNextInt() ){
		 sc.next();
		 System.out.print("Please enter a positive integer: "); 
		 }	
		 a=sc.nextInt();
		 if( a>0 ) break;
		 System.out.print("Please enter a positive integer: ");
		 }

		 System.out.print("Enter another positive integer: ");

		 while(true){
		 while( !sc.hasNextInt() ){
		 sc.next();
		 System.out.print("Please enter a positive integer: ");
		 }
		 b=sc.nextInt();
		 if(b>0 ) break;
		 System.out.print("Please enter a positive integer: ");
		 }

		 GCD = "The GCD of " + a + " and " + b + " is "; 
		 temp = a % b; 
		

		 if(a > b){
		 if(temp == 0) {
		 System.out.println(GCD + b);
		 } else {
		 while(temp > 0){
		 temp = a % b;
		 a = b;
		 b = temp;
		 if(temp == 0) {
		 System.out.println(GCD + a); }
		 }
   		 }
		 }else {
		 temp = b % a;
		 if(temp == 0) {
		 System.out.println(GCD + a);
		 } else {
		 while(temp > 0) {
		 temp = b % a;
		 b = a;
		 a = temp;
		 if(temp == 0){
		 System.out.println(GCD + b); }
		 }
              }
          }
      }
 }
