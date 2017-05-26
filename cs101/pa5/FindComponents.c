//Kevin Lee
//1480757
//FindComponents.c 
//pa5

#include<stdio.h>
#include<string.h>
#include<stdlib.h>

#include "Graph.h"

#define MAX_LEN 160

int main(int argc, char * argv[]){

    char line[MAX_LEN];
    FILE *infile, *outfile;
    if(argc != 3 || argv == NULL) {
    printf("only infile and outfile\n");
    exit(1);
  }

  infile = fopen(argv[1], "r");
  outfile = fopen(argv[2], "w");

  if( infile==NULL ){
      printf("No input to read\n");
      exit(1);
   }
   if( outfile==NULL ){
      printf("no output to write\n");
      exit(1);
   }

   fgets(line, MAX_LEN, infile);
   int temp = 0;
   sscanf(line, "%d", &temp);

   Graph new_Graph = newGraph(temp);
   List new_List = newList();

   while( fgets(line, MAX_LEN, infile) != NULL)  {
      int x = 0;
      int y = 0;
      sscanf(line, "%d %d", &x, &y);
      if(x == 0 && y == 0) {
        continue;
      }
      else{
      addArc(new_Graph, x, y);
    }
   }

   for(int j = 1; j <= temp; ++j){
      append(new_List, j);
   }


    fprintf(outfile,"Adjacency list representation of G:\n");
   printGraph(outfile, new_Graph);
    /*fprintf(outfile,"TEST");*/
    fprintf(outfile, "\n");
   DFS(new_Graph,new_List);
   Graph transposed_Graph = transpose(new_Graph);
   int count = 0;
   /*fprintf(outfile, "\nG contains %d strongly connected components:", count);*/
   /*fprintf(outfile,"TEST4");*/
   DFS(transposed_Graph, new_List);
   /*fprintf(outfile,"TEST2");*/
   moveFront(new_List);
   /*fprintf(outfile,"TEST3");*/
   while(index(new_List)>=0){
    if(getParent(transposed_Graph, get(new_List)) == NIL)
      count++;
      moveNext(new_List);
   }
   fprintf(outfile, "\nG contains %d strongly connected components:", count);  

   List connectedComponents[count];
   for(int i = 0; i < count; ++i) {
      connectedComponents[i] = newList();
   }

   moveFront(new_List);
   int i = count;
   while(index(new_List)>=0){
    if(getParent(transposed_Graph, get(new_List)) == NIL){
      --i;
    }

    if(i == count)
      continue;
      append(connectedComponents[i], get(new_List));
      moveNext(new_List);
   }

  for(int i = 0; i < count; ++i) {
    fprintf(outfile, "\nComponent %d: ", (i + 1));
    printList(outfile, connectedComponents[i]);
    freeList(&(connectedComponents[i]));
  }

   freeGraph(&new_Graph);
   freeGraph(&transposed_Graph);
   freeList(&new_List);

  fclose(infile);
  fclose(outfile);
  return(0);
}
