//-----------------------------------------------------------------------------
//  Lex.c
//  the program uses List ADT to sort words in lexicographic order.
//  class:CMPS 101
//  professor:Tantalo
//--------------------

#include<stdio.h>
#include<stdlib.h>
#include<string.h>
#include "List.h"

#define MAX_LEN 180

int main(int argc, char * argv[]) { //to check the correct form of arguments
    int count=0;
    FILE *newin, *newout;
    char newline[MAX_LEN];

    // check command line for correct number of arguments
    if( argc != 3 )
    {
        printf("here is the usage: %s <input file> <output file>\n", argv[0]);
        exit(1);
    }

    // open files for reading and writing, and int any variables
    newin = fopen(argv[1], "r");
    newout = fopen(argv[2], "w");
    if( newin==NULL )
    {
        printf("i am unable to open file %s for reading\n", argv[1]);
        exit(1);
    }
    if( newout==NULL )
    {
        printf("i am unable to open file %s for writing\n", argv[2]);
        exit(1);
    }

    // count the number of line to creat string with that size
    while( fgets(newline, MAX_LEN, newin) != NULL)
    {
        count++;
    }
    fclose(newin);
    //now create string array
    char* newuserinput[count];
    count=0;
    //openfile reading file again
    newin = fopen(argv[1], "r");
    while( fgets(newline, MAX_LEN, newin) != NULL)
    {
        newline[strcspn(newline,"\n")]='\0';
        newuserinput[count]=malloc(sizeof(char)*strlen(newline));
        strcpy(newuserinput[count],newline);
        count++;
    }
    //create a List and put 0 in as it's first element
    List A=newList();
    append(A,0);
    int inserted=0;
    for(int b=0; b<length(A);b++) {
        fprintf(newout,"%s\n",newuserinput[get(A)]);
        moveNext(A);
    }

    //for doing sorting argrithum starts here
    for(int a=1;a<count;a++)
    {
        if(strcmp(newuserinput[a],newuserinput[get(A)])<=0) {
            prepend(A,a);
        }
        else
        {
            while(strcmp(newuserinput[a],newuserinput[get(A)])>0 && index(A) +1!=length(A)) {
                moveNext(A);
                if(strcmp(newuserinput[a],newuserinput[get(A)])<=0)
                {
                    insertBefore(A,a);
                    inserted=1;
                }
            }
            if(inserted!=1)
                append(A,a);
        }
        moveFront(A);
        inserted=0;
    }
    //sorting process ends here
    //start printing the sorted array order to the output file

    moveFront(A);

    //clean the memory
    //free the loop for print out
    for(int c=0; c<count; c++) {
        free(newuserinput[c]);
    }

    freeList(&A);
    fclose(newin);
    fclose(newout);
    return(0);
}
