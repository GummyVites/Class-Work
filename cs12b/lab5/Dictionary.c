//Kevin Lee
//1480757
//CMPS 12M
//lab5
//Creates Dictionary
//Dictionary.c

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include<assert.h>
#include"Dictionary.h"

// NodeObj
typedef struct NodeObj{
	char* key;
	char* value;
	struct NodeObj* next;
} NodeObj;

// Node
typedef NodeObj* Node;

// newNode()
// constructor of the Node type
Node newNode(char* k, char* v) {
	Node N = malloc(sizeof(NodeObj));
	assert(N!=NULL);
	N->key = k;
	N->value = v;
	N->next = NULL;
	return(N);
}

// freeNode()
// destructor for the Node type
void freeNode(Node* pN){
	if( pN!=NULL && *pN!=NULL ){
		free(*pN);
		*pN = NULL;
   }
}

// Dictionary
// Exported reference type
typedef struct DictionaryObj{
	Node head;
	int numItems;
} DictionaryObj;

// newDictionary()
// constructor for the Dictionary type
Dictionary newDictionary(void){
	Dictionary D = malloc(sizeof(DictionaryObj));
	assert(D!=NULL);
	D->head = NULL;
	D->numItems= 0;
	return D;
}

//freeDictionary() 
//destructor for the Dictionary type
void freeDictionary(Dictionary* pD){
	if( pD!=NULL && *pD!=NULL ){
	if( !isEmpty(*pD) ) makeEmpty(*pD);
	free(*pD);
	*pD = NULL;
     }
}

// isEmpty()
// returns 1 (true) if S is empty, 0 (false) otherwise
// pre: none
int isEmpty(Dictionary D){
if( D==NULL ){
      fprintf(stderr, 
              "Stack Error: calling isEmpty() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
   return(D->numItems==0);
}

// size()
// returns the number of (key, value) pairs in D
// pre: none
int size(Dictionary D){
if( D==NULL ){
      fprintf(stderr, 
         "Dictionary Error: calling size() on NULL Dictionary reference\n");
      exit(EXIT_FAILURE);
   }
return D->numItems;
}

// lookup()
// returns the value v such that (k, v) is in D, or returns NULL if no 
// such value v exists.
// pre: none
char* lookup(Dictionary D, char* k){
	if( D==NULL ){
	fprintf(stderr, "Stack Error: calling lookup() on NULL Dictionary reference\n");
	exit(EXIT_FAILURE);
}
Node N = D->head;
while(N != NULL){
	if(strcmp(N->key,k) != 0){
		N = N->next;
	}else{
		return N->value;
}
}
		return NULL;
}

// insert()
// inserts new (key,value) pair into D
// pre: lookup(D, k)==NULL
void insert(Dictionary D, char* k, char* v){
 if(lookup(D,k)!=NULL){
      fprintf(stderr, "Dictionary Error: calling insert() on a existent key\n");

      exit(EXIT_FAILURE);
   }

 if(D->head == NULL){
          Node N = newNode(k, v);
          D->head = N;
	  D->numItems++;
       }else{
	  Node N = D->head;
	  for (int i=1;i<D->numItems;i++){
	  N= N->next;
	}
         Node B = newNode(k,v);
	  N->next =B;
	  D->numItems ++;
              }
   }

// delete()
// deletes pair with the key k
// pre: lookup(D, k)!=NULL
void delete(Dictionary D, char* k){
if (lookup(D,k) == NULL){
	fprintf(stderr, "Key not found\n");
	exit(EXIT_FAILURE);
}
Node N= D->head;
if (strcmp(N->key,k) == 0){
	D->head = N->next;
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


// makeEmpty()
// resets D to the empty state
// pre: none
   void makeEmpty(Dictionary D){
	Node N = D->head;
	Node temp;
	while(N!=NULL){
   	  	temp = N;
  	   	N = N->next;
   	  	freeNode(&temp);
 	    	D->numItems--;
 	    }
	D->numItems = 0;	
	}
	
// printDictionary()
// pre: none
// prints a text representation of D to the file pointed to by out
void printDictionary(FILE* out, Dictionary D){
   Node N;
   if( D==NULL ){
      fprintf(stderr, 
              "Stack Error: calling printStack() on NULL Stack reference\n");
      exit(EXIT_FAILURE);
   }
   for(N=D->head; N!=NULL; N=N->next) fprintf(out, "%s %s \n" , N->key, N->value);
}
