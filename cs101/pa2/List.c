//Kevin Lee
//1480757
//pa1
//cmps101
//List.java

#include<stdio.h>
#include<stdlib.h>
#include "List.h"

typedef struct NodeObj{
        int data;
        struct NodeObj* next;
        struct NodeObj* previous;
    } NodeObj;

typedef struct NodeObj* Node;

typedef struct ListObj{
    Node front;
    Node back;
    Node cursor;
    int indexCursor;
    int length;
    } ListObj;
typedef ListObj* List;

 Node newNode(int data){
   Node N = malloc(sizeof(NodeObj));
   N->data = data;
   N->next = NULL;
   N->previous = NULL;
   return(N);
}

List newList(void){
   List L;
   L = malloc(sizeof(ListObj));
   L->front = NULL;
   L->front= NULL;
   L->cursor = NULL;
   L->indexCursor = -1;
   L->length = 0;
   return(L);
}

void freeList(List* pL){
    if( *pL!=NULL && pL!=NULL ){
        clear(*pL);
        free(*pL);
        *pL = NULL;
    }
}

void freeNode(Node* pN){
   if( pN!=NULL && *pN!=NULL ){
      free(*pN);
      *pN = NULL;
   }
}


int length(List L){
        return (L->length);
    }

int index(List L){
        if (L->indexCursor >= -1){
            return L->indexCursor;
        }
        else{ return -1;
        }
}

int front(List L){
        if (L->length>0)
            return L->front->data;
        else
            printf("List Error: cannot call front() on empty List\n");
 	     exit(1);
    }

int back(List L){
        if (length(L)>0)
            return L->back->data;
        else
            printf("List Error: cannot call front() on empty List\n");
             exit(1);
    }

int get(List L){
        if (length(L)>=0 && L->indexCursor>=0)
            return L->cursor->data;
        else
            printf("List Error: cannot call front() on empty List\n");
             exit(1);
    }

int equals(List A, List B){
        int  equal = ( A->length == B->length );
        Node C = A->front;
        Node D = B->front;
        if (A->length == B->length){
             while( equal && C!=NULL){
                equal =(C->data == D->data);
                C= C->next;
                D= D->next;
            }
            return equal;
        }
	return equal;
  }

void clear(List L){
        if( L==NULL ){
        printf("List Error: calling clear() on NULL List reference");
        exit(1);
    }
    while( L->front!=NULL ){
        deleteFront(L);
    }
    L->front = NULL;
    L->back = NULL;
    L->cursor = NULL;
    L->length = 0;
    L->indexCursor = -1;
}
void moveFront(List L){
        if (L->length>0){
            L->cursor = L->front;
            L->indexCursor = 0;
        }else{ return;
        }
    }

void moveBack(List L){
        if(L->length > 0){
            L->cursor = L->back;
            L->indexCursor = L->length-1;
        }else{ return;
        }
    }

void movePrev(List L){
        if (L->cursor !=NULL && L->indexCursor!= 0){
            L->cursor = L->cursor->previous;
            L->indexCursor--;
        }
        else if (L->indexCursor== 0){
            L->indexCursor = -1;
            L->cursor= NULL;

        }else{
            return;
        }
    }

 void moveNext(List L){
        if (L->cursor!= NULL && L->cursor!=L->back){
        L->cursor = L->cursor->next;
            L->indexCursor++;
        }else if (L->cursor!= NULL && L->cursor == L->back){
            L->cursor =NULL;
            L->indexCursor=-1;
        }
        else{
            return;
        }
    }

void prepend(List L, int data){
    if( L==NULL ){
        printf("List Error: calling prepend() on NULL List reference");
        exit(1);
    }
    Node element = newNode(data);
    if( L->length==0 ){
        L->front = element;
        L->back = element;
    }else{
        L->front->previous = element;
        element->next = L->front;
        L->front = element;
        L->indexCursor++;
    }
   L->length++;
}

void append(List L, int data){
        Node element = newNode(data);
    if( L->length==0 ){
        L->front = element;
        L->back = element;
	L->length++;
    }else{
        L->back->next = element;
        element->previous = L->back;
        L->back = element;
	L->length++;
    }
}

void insertBefore(List L, int data){
        Node element = newNode(data);
        Node previous = L->cursor->previous;
        if( L->length <= 0 || L->indexCursor< 0){
	   printf("List Error: insertBefore() called on empty List");
           exit(1);
        }
        if( L->indexCursor == 0){
            prepend(L,data);
        }
        else{
            element->previous = L->cursor->previous;
            element->next = L->cursor;
            previous->next = element;
            L->cursor->previous= element;
            L->length++;
            L->indexCursor++;
        }
    }

void insertAfter(List L, int data){
    Node element = newNode(data);
        Node next = L->cursor->next;
        if( L->length <= 0 || L->indexCursor < 0){
            printf("List Error: insertafter() called on empty List");
            exit(1);
        }
        if( L->indexCursor == L->length - 1){
            append(L,data);
        }

        else{
            element->previous = L->cursor;
            element->next = L->cursor->next;
            next->previous = element;
            L->cursor->next= element;
            L->length++;
            L->indexCursor++;
        }
    }

void deleteFront(List L){
        if(L->length <= 0){
            printf("List Error: cannot call front() on empty List\n");
             exit(1);
        }
        else{
	    Node N = L->front;
            L->front = L->front->next;
            L->length--;
	    freeNode(&N);
	    
 }
    }

void deleteBack(List L){
        if(L->length <= 0){
            printf("List Error: cannot call front() on empty List\n");
             exit(1);
        }

        else{
            L->cursor = L->back;
            L->back = L->back->previous;
            L->length--;
        }
    }
           

void delete(List L){
        if(L->length <= 0 || L->indexCursor< 0){
            printf("List Error: cannot call front() on empty List\n");
            exit(1);
      }
      else{
          L->cursor->previous->next = L->cursor->next;
          L->cursor->next = L->cursor->previous;
          L->cursor = NULL;
          L->indexCursor = -1;
          L->length--;
                        }
   }

void printList(FILE* out, List L){
        Node print = L->front;
        while( print != NULL){
            fprintf(out,"%d ", print->data);
            print = print->next;
        }
   }

List copyList(List L){
 List C = newList();
 for(Node T = L->front; T != NULL; T=T->next){
    append(C,T->data);
 }
 return C;
}


