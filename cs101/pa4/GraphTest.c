//Kevin Lee
//1480757
//pa4
//GraphTest.c
#include<stdio.h>
#include<stdlib.h>
#include"Graph.h"

int main(int argc, char* argv[]){
   int i, s, max, min, d, n=16;
   List  C = newList(); // central vertices 
   List  P = newList(); // peripheral vertices 
   List  E = newList(); // eccentricities 
   Graph G = NULL;

   G = newGraph(n);
   for(i=1; i<n; i++){
      if( i%2!=0 ) addEdge(G, i, i+1);
      if( i<=3  ) addEdge(G, i, i+2);
   }
   addEdge(G, 1, 3);
   addEdge(G, 2, 1);
   addEdge(G, 6, 10);


   printGraph(stdout, G);

   for(s=1; s<=n; s++){
      BFS(G, s);
      max = getDist(G, 1);
      for(i=2; i<=n; i++){
         d = getDist(G, i);
         max = ( max<d ? d : max );
      }
      append(E, max);
   }


   append(C, 1);
   append(P, 1);
   min = max = front(E);
   moveFront(E);
   moveNext(E);
   for(i=2; i<=n; i++){
      d = get(E);
      if( d==min ){
         append(C, i);
      }else if( d<min ){
         min = d;
         clear(C);
         append(C, i);
      }
      if( d==max ){
         append(P, i);
      }else if( d>max ){
         max = d;
         clear(P);
         append(P, i);
      }
      moveNext(E);
   }

   printf("\n");
   printf("Radius = %d\n", min);
   printf("Central vert%s: ", length(C)==1?"ex":"ices");
   printList(stdout, C);
   printf("\n");
   printf("Diameter = %d\n", max);
   printf("Peripheral vert%s: ", length(P)==1?"ex":"ices");
   printList(stdout, P);
   printf("\n");

   freeList(&C);
   freeList(&P);
   freeList(&E);
   freeGraph(&G);
    
   BFS(G, 13);
   List N = newList();
   getPath(N, G, 15);
   printf("Path from 13 to 15: ");
   printList(stdout, N);
   printf("\n");
   
   BFS(G, 11);
   List Q = newList();
   getPath(Q, G, 8);
   printf("Path from 1 to 8: ");
   printList(stdout, Q);
   printf("\n");
  
   freeList(&N);
   FreeList(&N);
   int x = getOrder(G);
   int y = getSize(G);
   int z = getSource(G);
   printf("%d %d %d",x,y,z);
      

   return(0);
}
