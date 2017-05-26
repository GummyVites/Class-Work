/*-----------------------------------------------------------------------------
 *    Circle.c
 *       Computes the area and circumference of a circle of given radius.
 *          Compile this on the Unix Timeshare unix.ucsc.edu as follows:
 *             
 *                   gcc -lm -o Circle Circle.c
 *                      
 *                         The -lm flag is necessary for use of the math.h library.
 *                         -------------------------------------------------------------------------------*/
#include<stdio.h>
#include<math.h>

int main(){
   double radius, area, circumference;
   const double pi = 3.141592654;

   printf("Enter radius: ");
   scanf("%lf", &radius);
   area = pi*pow(radius,2);
   circumference = 2*pi*radius;
   printf("The area is: %f\n", area);
   printf("The circumference is: %f\n", circumference);

   return 0;
}

