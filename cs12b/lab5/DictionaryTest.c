//Kevin Lee
//1480757
//CMPS 12M
//DictionaryTest.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include"Dictionary.h"

#define MAX_LEN 180

int main(int argc, char* argv[]){
   Dictionary A = newDictionary();
   char* k;
   char* v;
   char* word1[] = {"1","2","3","4","5","6"};
   char* word2[] = {"acne","raf","supreme","palace","raised","wolves"};
   int i;

   for(i=0; i<6; i++){
      insert(A, word1[i], word2[i]);
   }

   printDictionary(stdout, A);

   for(i=0; i<6; i++){
      k = word1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }

   printf("%d, %d\n", isEmpty(A), size(A));	

   delete(A, "1");
   delete(A, "4");	
   delete(A, "6");
   insert(A, "7", "bape" );
   printDictionary(stdout, A);

   for(i=0; i<6; i++){
      k = word1[i];
      v = lookup(A, k);
      printf("key=\"%s\" %s\"%s\"\n", k, (v==NULL?"not found ":"value="), v);
   }

   printf("%s\n", (isEmpty(A)?"true":"false"));
   printf("%d\n", size(A));
   makeEmpty(A);
   printf("%s\n", (isEmpty(A)?"true":"false"));

   freeDictionary(&A);

   return(EXIT_SUCCESS);
}
