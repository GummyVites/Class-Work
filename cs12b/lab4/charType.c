//kevin Lee
//1480757
//CMPS 12B
//Lab4
//Sorts letters numbers punctuation and white spaces. 
#include<stdio.h>
#include<stdlib.h>
#include<ctype.h>
#include<assert.h>
#include<string.h>

#define MAX_STRING_LENGTH 100

void extract_chars(char* s, char* a, char* d, char* p, char* w);

int main(int argc, char* argv[]){
    FILE* in;        
    FILE* out;       
    char* line;      
    char* alpha;   
    char* digit;
    char* punc;
    char* white;

    if( argc != 3 ){
        printf("Usage: %s input-file output-file\n", argv[0]);
        exit(EXIT_FAILURE);
    }
    
    if( (in=fopen(argv[1], "r"))==NULL ){
        printf("Unable to read from file %s\n", argv[1]);
        exit(EXIT_FAILURE);
    }
    if( (out=fopen(argv[2], "w"))==NULL ){
        printf("Unable to write to file %s\n", argv[2]);
        exit(EXIT_FAILURE);
    }

    line = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    alpha = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    digit = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    punc = calloc(MAX_STRING_LENGTH+1, sizeof(char));
    white = calloc(MAX_STRING_LENGTH+1, sizeof(char));

    assert( line!=NULL && alpha!=NULL && digit!=NULL && punc!=NULL && white!=NULL );

    int i = 1;
    while( fgets(line, MAX_STRING_LENGTH, in) != NULL ) {
        extract_chars(line,alpha,digit,punc,white);
        fprintf(out, "line %d contains: \n", i);
        if ((int) strlen(alpha) == 1) {
            fprintf(out, "%d alphabetic character: %s\n", (int)strlen(alpha), alpha);
        } else {
            fprintf(out, "%d alphabetic characters: %s\n", (int)strlen(alpha), alpha);
        }
        if ((int)strlen(digit) == 1) {
            fprintf(out, "%d numeric character: %s\n", (int)strlen(digit), digit);
        } else {
            fprintf(out, "%d numeric characters: %s\n", (int)strlen(digit), digit);
        }
        if ((int)strlen(punc) == 1) {
            fprintf(out, "%d punctuation character:  %s\n", (int)strlen(punc), punc);
        } else {
            fprintf(out, "%d punctuation characters: %s\n", (int)strlen(punc), punc);
        }
        if (strlen(white) ==  1) {
            fprintf(out, "%d whitespace characters: %s\n", (int)strlen(white), white);
        } else {
            fprintf(out, "%d whitespace characters: %s\n", (int)strlen(white), white);
        }
        i++;
    }

    free(line);
    free(alpha);
    free(digit);
    free(punc);
    free(white);

    fclose(in);
    fclose(out);
    return EXIT_SUCCESS;

}

void extract_chars(char* s, char* a, char* d, char* p, char* w){
	int i=0;
	int j=0;
	int q=0;
	int t=0;
	int r=0;

	while(s[i] != '\0' && i<MAX_STRING_LENGTH){
	if(isalpha(s[i])){
			a[j] =s[i];
			j++;
		}
	else if( isdigit(s[i])){
			d[q] = s[i];
			q++;
		}
	else if(ispunct(s[i])){
			p[t]=s[i];
			t++;
		}
	else{
		w[r]=s[i];
		r++;
		}
		i++;
		a[j]='\0';
        	d[q]='\0';
        	p[t]='\0';
        	w[r]='\0';
	}	
}

