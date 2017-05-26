//Kevin Lee
//1480757
//pa4
//Graph.c

#include <stdio.h>
#include <stdlib.h>

#include "Graph.h"
#include "List.h"

#define WHITE 0
#define GRAY 1
#define BLACK 2

typedef struct GraphObj{
  	List * adjList;
  	int order;
  	int size;
  	int source;
  	int * parent;
  	int * color;
  	int * distance;

} GraphObj;


Graph newGraph(int n){
	Graph new_Graph =  malloc(sizeof(struct GraphObj));
	new_Graph->adjList = calloc(n + 1, sizeof(List));
	new_Graph->order = n;
	new_Graph->size = 0;
	new_Graph->source = NIL;
	new_Graph->parent = calloc(n + 1, sizeof(int));
   	new_Graph->color = calloc(n + 1, sizeof(int));
   	new_Graph->distance = calloc(n + 1, sizeof(int));
   	for(int i = 0; i < (n + 1); ++i) {
        new_Graph->adjList[i] = newList();
        new_Graph->color[i] = WHITE;
        new_Graph->parent[i] = NIL;
        new_Graph->distance[i] = INF;
   }
   return new_Graph;
}


void freeGraph(Graph* pG){
    for(int i = 0; i < (*pG)->order+1; i++) {
      freeList(&(*pG)->adjList[i]);
    }
    free((*pG)->parent);
    free((*pG)->color);
    free((*pG)->distance);
    free((*pG)->adjList);
    free(*pG);
    *pG = NULL;
  }

int getOrder(Graph G){
    if(G == NULL) {
      fprintf(stderr, "Graph is NULL at getOrder");
    }
	return G->order;
}

int getSize(Graph G){
    if(G == NULL) {
      fprintf(stderr, "Graph is NULL at getSize");
    }
	return G->size;
}

int getSource(Graph G){
    if(G == NULL) {
      fprintf(stderr, "Graph is NULL at getSource");
      exit(1);
    }
    return G->source;
}

int getParent(Graph G, int u){
   if(G == NULL) {
      fprintf(stderr, "Graph null at getParent");
      exit(1);
   }
   return G->parent[u];
}
int getDist(Graph G, int u){
   if(getSource(G) == NIL) {
      return INF;
   }
   return G->distance[u];
}
void getPath(List L, Graph G, int u){
   if(getSource(G) == NIL) {
      printf("getSource == NIL\n"); 
   }

   if(u == G->source) {
      prepend(L, G->source);
   } 
   
   else if(G->parent[u] == NIL) {
      append(L, NIL);
   } 
   
   else {
      prepend(L, u);
      getPath(L, G, G->parent[u]);
   }
}

void makeNull(Graph G){
   for(int i = 0; i < (G->order + 1); ++i){
     clear(G->adjList[i]);
   }
}

void addEdge(Graph G, int u, int v){
	addArc(G, u, v);
	addArc(G, v, u);
	G->size--;
}

void addArc(Graph G, int u, int v){
   moveFront(G->adjList[u]);
   while(index(G->adjList[u]) > -1 && v > get(G->adjList[u])) {
      moveNext(G->adjList[u]);
   }
   if(index(G->adjList[u]) == -1){
      append(G->adjList[u], v);
   }
   else{ 
      insertBefore(G->adjList[u], v);
   G->size++;
   }
}

void BFS(Graph G, int s) {
   for(int i = 0; i < (G->order+1); ++i) {
      G->parent[i] = NIL;
      G->distance[i] = INF;
      G->color[i] = WHITE;
   }
   G->parent[s] = NIL;
   G->source = s;
   G->distance[s] = 0;
   G->color[s] = GRAY;

   List new_List = newList();
   prepend(new_List, s);

   while(length(new_List) > 0) {
      int temp = back(new_List);
      deleteBack(new_List);
      List adj = G->adjList[temp];
      moveFront(adj);
      while(index(adj) > -1) {
         int vertex = get(adj);
         if(G->color[vertex] == WHITE) {
            G->color[vertex] = GRAY;
            G->parent[vertex] = temp;
            G->distance[vertex] = G->distance[temp] + 1;
            prepend(new_List, vertex);
         }
         moveNext(adj);
      }
	G->color[temp] = BLACK;
   }
   freeList(&new_List); 
}

void printGraph(FILE *out, Graph G) {
   for(int i = 1; i <= G->order; ++i) {
      fprintf(out, "%d: ", i);
      printList(out, G->adjList[i]);
      fprintf(out, "\n");
   }
}
