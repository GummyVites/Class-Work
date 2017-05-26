//Kevin Lee
//1480757
//CMPS 12B
//pa5
//Creates Dictionary
//Dictionary.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

const int tableSize=101;

/* NodeObj */
typedef struct NodeObj{
        char* key;
        char* value;
        struct NodeObj* next;
} NodeObj;

/* Node */
typedef NodeObj* Node;

/* newNode()
 * constructor of the Node type */
Node newNode(char* k, char* v) {
        Node N = malloc(sizeof(NodeObj));
        assert(N!=NULL);
        N->key = k;
        N->value = v;
        N->next = NULL;
        return(N);
}

/* freeNode()
 * destructor for the Node type */
void freeNode(Node* pN){
    if( pN!=NULL && *pN!=NULL ){
        free(*pN);
        *pN = NULL;
   }
}

/* Dictionary
 * Exported reference type */
typedef struct DictionaryObj{
        Node* head;
        int numItems;
} DictionaryObj;

/* newDictionary() 
 * constructor for the Dictionary type */
Dictionary newDictionary(void){
        Dictionary D = malloc(sizeof(DictionaryObj));
        assert(D != NULL);
        D->head = calloc(tableSize, sizeof(DictionaryObj));
        D->numItems = 0;
        return D;
}

/* freeDictionary() 
 * destructor for the Dictionary type */
void freeDictionary(Dictionary* pD){
   if (pD != NULL && *pD != NULL) {
        if( !isEmpty(*pD) )
          makeEmpty(*pD);
          free((*pD)->head);
          free(*pD);
          *pD = NULL;
       }
  }

/* rotate_left()
 * rotate the bits in an unsigned int */
unsigned int rotate_left(unsigned int value, int shift) {
   int sizeInBits = 8*sizeof(unsigned int);
   shift = shift & (sizeInBits - 1);  // remainder on division by sizeInBits
   if ( shift == 0 )
      return value;
   return (value << shift) | (value >> (sizeInBits - shift));
}

/* pre_hash()
  turn a string into an unsigned int */
unsigned int pre_hash(char* input) {  /* input points to first char in string */
   unsigned int result = 0xBAE86554;  /* = 10111010111010000110010101010100 */
   while (*input) {                   /* while *input is not '\0' (not end of string) */
      result ^= *input++;                /* result = result ^ *input (current char alters result) */
                                         /* input++  (go to next char) */
      result = rotate_left(result, 5);   /* rotate result by fixed amount */
   }
   return result;  /* result is now a random looking bit pattern depending on input string */
}

/* hash() 
 * turns a string into an int in the range 0 to tableSize-1 */
int hash(char* key){
   return pre_hash(key)%tableSize;
}

/* isEmpty() 
 * returns 1 (true) if S is empty, 0 (false) otherwise 
 * pre: none */
int isEmpty(Dictionary D){
   return(D->numItems==0);
}

/* size() 
 * returns the number of (key, value) pairs in D 
 * pre: none */
int size(Dictionary D){
        return D->numItems;
}

/* lookup() 
 * returns the value v such that (k, v) is in D, or returns NULL if no 
 * such value v exists. 
 * pre: none */
char* lookup(Dictionary D, char* k){
   int index = hash(k);
        Node N = D->head[index];
        while(N != NULL){
        if(strcmp(N->key,k) != 0){
               N = N->next;
        }else{
                return N->value;
      }
    }
                return NULL;
}

/* insert() 
 * inserts new (key,value) pair into D 
 * pre: lookup(D, k)==NULL */
void insert(Dictionary D, char* k, char* v){
  if(lookup(D,k)!=NULL){
      fprintf(stderr, "Dictionary Error: calling insert() on a existent key\n");

      exit(EXIT_FAILURE);
   }
   int index = hash(k);
 if(D->head[index] == NULL){
      Node N = newNode(k, v);
      D->head[index] = N;
      D->numItems++;
   }
   else{
      Node N = D->head[index];
      while(N->next != NULL) N = N->next;
      N->next = newNode(k,v);
      D->numItems++;
   }
}

/* delete() 
 * deletes pair with the key k 
 * pre: lookup(D, k)!=NULL */
void delete(Dictionary D, char* k){
         if (lookup(D,k) == NULL){
           fprintf(stderr, "Key not found\n");
           exit(EXIT_FAILURE);
         }
        int index = hash(k);
        Node N = D->head[index];
        if (strcmp(N->key,k) == 0){
          D->head[index] = N->next;
          D->numItems--;
          freeNode(&N);
        }else{
             while (N->next->key != k){
            N = N->next;
        }
          Node temp = N->next;
          N->next = N->next->next;
          D->numItems--;
          freeNode(&temp);
        }
}

/* makeEmpty() 
 * resets D to the empty state 
 * pre: none */
void makeEmpty(Dictionary D){
   for(int i=0;i<tableSize;i++){
        Node N = D->head[i];
        Node temp;
        while(N!=NULL){
                temp = N;
                N = N->next;
                freeNode(&temp);
                D->numItems--;
            }
        D->numItems = 0;
        }
}

/* printDictionary() 
 * pre: none 
 * prints a text representation of D to the file pointed to by out */
void printDictionary(FILE* out, Dictionary D){
    for(int i=0;i<tableSize;i++){
         for(Node N=D->head[i];N!=NULL;N=N->next){
            fprintf(out,"%s %s\n",N->key,N->value);
         }
      }
   }
