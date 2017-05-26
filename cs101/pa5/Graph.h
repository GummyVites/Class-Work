//Kevin lee
//1480757
//pa5
//Graph.h

#ifndef __GRAPH_H__
#define __GRAPH_H__

#include <stdio.h>
#include "List.h"

#define UNDEF -3
#define NIL 0

typedef struct GraphObj* Graph;
Graph newGraph(int n);
void freeGraph(Graph* pG);
int getOrder(Graph G);
int getSize(Graph G);
int getParent(Graph G, int u);
int getDiscover(Graph G, int u);
int getFinish(Graph G, int u);
void addArc(Graph G, int u, int v);
void addEdge(Graph G, int u, int v);
void Visit(Graph G, List S, int u, int *time);
void DFS(Graph G, List S);
Graph transpose(Graph G);
Graph copyGraph(Graph G);
void printGraph(FILE *out, Graph G);

#endif
