//GCD.c
//Kevin Lee
//1480757
//lab8
//Finds the greatest common denominator of two numbers.
#include<stdio.h>
#include<stdlib.h>
#include<string.h>

int main(){
   int i, n;
   char str[100];
   int x[2];

        printf("Enter a positive integer: ");
        for(i=0; i<2; i++){
           n= scanf(" %s", str);
        while( 1 ){
         sscanf(str, "%d", &x[i]);
         if( x[i] > 0){
            printf("Enter another positive integer: ");
            break;
         }
         printf("Please enter a positive integer: ");
         n = scanf(" %s", str);
      }
   }
int a=x[0];
int b=x[1];
int temp = (x[0] % x[1]);
        if(a > b){
            if(temp == 0) {
                printf("The GCD of %d and %d is %d",x[0],x[1],b);
            } else {
                while(temp > 0){
                    temp = (a % b);
                    a = b;
                    b = temp;
                    if(temp == 0) {
                        printf("The GCD of  %d   and   %d  is %d",x[0],x[1],a);
                    }
                }
            }
        }else {
            temp = (b % a);
            if(temp == 0) {
                printf("The GCD of  %d   and   %d   is %d",x[0],x[1],a);
            } else {
                while(temp > 0) {
                    temp = (b % a);
                    b = a;
                    a = temp;
                    if(temp == 0){
                        printf("The GCD of %d and %d is %d",x[0],x[1],b);
                    }
                }
            }


 return 0;
}
}
