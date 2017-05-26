//Kevin Lee
//1480757
//pa5
//Graph.c

#include <stdio.h>
#include <stdlib.h>

#include "Graph.h"

#define WHITE 0
#define GRAY 1
#define BLACK 2

typedef struct GraphObj{
        List *adjList;
        int order;
        int size;
        int *parent;
        int *color;
        int *discoverTime;
        int *finishTime;
}GraphObj;


Graph newGraph(int n){
        Graph new_Graph =  malloc(sizeof(struct GraphObj));
        new_Graph->adjList = calloc(n + 1, sizeof(List));
        new_Graph->order = n;
        new_Graph->size = 0;
        new_Graph->parent = calloc(n + 1, sizeof(int));
        new_Graph->color = calloc(n + 1, sizeof(int));
        new_Graph->finishTime = calloc(n + 1, sizeof(int));
        new_Graph->discoverTime = calloc(n + 1, sizeof(int));

        for(int i = 0; i < (n + 1); ++i) {
        new_Graph->adjList[i] = newList();
        new_Graph->color[i] = WHITE;
        new_Graph->parent[i] = NIL;
        new_Graph->discoverTime[i]= UNDEF;
        new_Graph->finishTime[i]= UNDEF;
   }
   return new_Graph;
}

void freeGraph(Graph* pG){
    Graph temp = *pG;
    for(int i = 0; i < (temp->order+1); i++) {
      freeList(&(*pG)->adjList[i]);
    }
    free((*pG)->parent);
    free((*pG)->color);
    free((*pG)->adjList);
    free((*pG)->finishTime);
    free((*pG)->discoverTime);
    free(*pG);
    *pG = NULL;
  }

int getOrder(Graph G){
        return G->order;
}

int getSize(Graph G){
    if(G == NULL) {
      fprintf(stderr, "Graph is NULL at getSize");
    }
        return G->size;
}

int getParent(Graph G, int u){
   if(G == NULL) {
      fprintf(stderr, "Graph null at getParent");
      exit(1);
   }
   return G->parent[u];
}

int getDiscover(Graph G, int u) {
   return G->discoverTime[u];
}

int getFinish(Graph G, int u) {
   return G->finishTime[u];
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
   else
      insertBefore(G->adjList[u], v);
   G->size++;

}

void DFS(Graph G, List S){
    if (length(S)!=getOrder(G)){
        printf("Graph Error: called DFS() without matching sizes\n");
        exit(1);
    }

    int time = 0;
    moveFront(S);

    for(int i = 0; i < (G->order+1); ++i) {
        G->parent[i] = NIL;
        G->color[i] = WHITE;
    }

    while(index(S)>= 0){
        int y = get(S);
        if( G->color[y] == WHITE ){
        Visit(G, S, y, &time);
        }
        moveNext(S);
        }
    for(int size = length(S)/2; size > 0; --size) {
      deleteBack(S);
   }
}

void Visit(Graph G,List S,int x, int *t){
    *t = *t +1;
    moveFront(G->adjList[x]);
    G->discoverTime[x] = *t;
    G->color[x] = GRAY;
    while(index(G->adjList[x]) > -1){
        int vertex = get(G->adjList[x]);
        if(G->color[vertex] == WHITE){
            G->parent[vertex] = x;
            Visit(G, S, vertex, t);
        }
        moveNext(G->adjList[x]);
    }
    G->color[x] = BLACK;
    prepend(S, x);
    *t = *t+1;
    G->finishTime[x] = *t;
}

Graph transpose(Graph G){
    Graph transposed = newGraph(getOrder(G));
    for(int i = 1; i <= getOrder(G); ++i) {
      moveFront(G->adjList[i]);
      while(index(G->adjList[i]) >= 0) {
         addArc(transposed, get(G->adjList[i]), i);
         moveNext(G->adjList[i]);
      }
   }
   return transposed;
}

Graph copyGraph(Graph G){
    Graph copyGraph = newGraph(getOrder(G));
   for(int i = 1; i <= getOrder(G); ++i) {
      moveFront(G->adjList[i]);
      while(index(G->adjList[i]) >= 0) {
         addArc(copyGraph, i, get(G->adjList[i]));
         moveNext(G->adjList[i]);
      }
   }
   return copyGraph;
}

void printGraph(FILE *out, Graph G) {
   for(int i = 1; i <= getOrder(G); ++i) {
      fprintf(out, "%d: ", i);
      printList(out, G->adjList[i]);
      fprintf(out, "\n");
   }
}
