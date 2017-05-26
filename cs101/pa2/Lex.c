//Kevin Lee
//1480757
//pa2
//Cmps101
//Lex.java

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "List.h"
#define MAX_LEN 160

int main (int argc, const char * argv[]) {
    FILE *in, *out, *in2;
    char line[MAX_LEN];

        int lineNumber = 0;
        int i = 0;

        if( argc != 3 ){
        printf("Usage: %s infile outfile\n Bad Error", argv[0]);
        exit(1);
    }
       
        in = fopen(argv[1], "r");
        out = fopen(argv[2], "w");
        in2 = fopen(argv[1], "r");

        while( fgets(line, MAX_LEN, in) != NULL){
        lineNumber++;
        }
        fclose(in);
        
        
        char** list = malloc(sizeof(char**) * lineNumber);
        while( fgets(line,MAX_LEN, in2) != NULL){
            char *D = malloc(strlen(line) + 1);
            strcpy(D,line);
            list[i]= D;
            i++;
        }
        fclose(in2);

        List L = newList();
        append(L,0);
	//printf("%i", lineNumber);
        for(i=1; i<lineNumber; i++){
            char *temp = list[i];
            moveFront(L);
		if (strcmp(temp,list[back(L)])>0){
                        append(L,i);
                }
                else if (strcmp(temp,list[front(L)])<0){
                        prepend(L,i);
                }
                else if(index(L) != -1){
                       while(strcmp(temp,list[get(L)])>0){
			                   //printf("loop");
                        moveNext(L);
                }
        insertBefore(L,i);

        }
     }

        for(moveFront(L); index(L)>=0; moveNext(L)){
            fprintf(out, "%s", list[get(L)]);
        }
        fclose(out);

    for(int i = 0; i < lineNumber; i++){
    free(list[i]);
  }
  free(list);
  freeList(&L);
}

