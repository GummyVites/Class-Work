//Kevin Lee
//1480757
//pa4
//FindPath.c

#include<stdio.h>
#include<string.h>
#include<stdlib.h>

#include "Graph.h"
#include "List.h"

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
   while( fgets(line, MAX_LEN, infile) != NULL)  {
      int x = 0;
      int y = 0;
      sscanf(line, "%d %d", &x, &y);
      if(x == 0 && y == 0) { 
        break; 
      }
      addEdge(new_Graph, x, y);
   }
   printGraph(outfile, new_Graph);
   fprintf(outfile, "\n");
   while( fgets(line, MAX_LEN, infile) != NULL)  {
      int x = 0;
      int y = 0;
      sscanf(line, "%d %d", &x, &y);
      if(x == 0 && y == 0) {
         break;
      }
      
      BFS(new_Graph, x);
      List new_List = newList();
      getPath(new_List, new_Graph, y);

      if(getDist(new_Graph, y) == INF){
         fprintf(outfile, "The distance from %d to %d is infinity \n",x, y);
         fprintf(outfile, "No %d-%d path exists \n\n",x, y);
      }

      else { 
        fprintf(outfile, "The distance from %d to %d is : %d \n", x, y, getDist(new_Graph, y));
        fprintf(outfile, "A shortest %d-%d path is : ", x, y);
        printList(outfile, new_List);
        fprintf(outfile, "\n\n");
      }

      freeList(&new_List);
  }

  freeGraph(&new_Graph);
  fclose(infile);
  fclose(outfile);
}
