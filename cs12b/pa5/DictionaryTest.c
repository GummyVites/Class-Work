//Kevin Lee
//1480757
//PA5
//DictionaryTest.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary a = newDictionary();
   Dictionary b = newDictionary(); 
   
 
   insert(a, "rick", "sick");
   insert(a, "pally", "vlone");
   insert(a, "assc", "ronin"); 
   insert(a, "patch", "sour");
   insert(b, "stapler", "magic");
   insert(b, "one", "once");
   insert(b, "two", "twice");
  

   printf("%s\n",lookup(a,"rick"));
   printf("%s\n",lookup(a,"patch"));   
   printf("%s\n",lookup(b,"one"));

   printDictionary(stdout, a);
   printDictionary(stdout, b);

   makeEmpty(b);

   printf("%s\n", (isEmpty(a)?"true":"false"));   
   printf("%s\n", (isEmpty(b)?"true":"false"));


   delete(a,"ascc");
   delete(a,"rick");


   printf("%d\n", size(a));
   printf("%d\n", size(b));


   makeEmpty(a);
   
   printDictionary(stdout,a);
  
   freeDictionary(&a);
   freeDictionary(&b);
   return(EXIT_SUCCESS);
}

