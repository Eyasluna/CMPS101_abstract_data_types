//-----------------------------------------------------------------------------
// List.c
// the Implementation file for List ADT
//  Yibo Fu
//  class:CMPS 101
//  professor:Tantalo
//  the integer List ADT is a bi-directional queue that has a cursor for List
//-----------------------------------------------------------------------------


#include<stdio.h>
#include<stdlib.h>
#include "List.h"

// structs --------------------------------------------------------------------

// private NodeObj type
typedef struct NodeObj {
    int data;
    struct NodeObj* next;
    struct NodeObj* previ;
} NodeObj;

// private Node type
typedef NodeObj* Node;

// private ListObj type
typedef struct ListObj
{
    Node front;
    Node back;
    Node cursor;
    int newlength;
    int newindex;
} ListObj;


// Constructors-Destructors ---------------------------------------------------

// newNode()
// Returns reference to new Node object. Initializes next and data fields.
// Private.
Node newNode(int data)
{
    Node N = malloc(sizeof(NodeObj));
    N->data = data;
    N->next = NULL;
    N->previ = NULL;
    return(N);
}

// freeNode()
// Frees heap memory pointed to by *pN, sets *pN to NULL.
// Private.
void freeNode(Node* pN)
{
    if( pN!=NULL && *pN!=NULL )
    {
        free(*pN);
        *pN = NULL;
    }
}
// Constructor
// Createss a new empty List
List newList(void){
    List L;
    L = malloc(sizeof(ListObj));
    L->newlength = 0;
    L->front = L->back = NULL;
    L->newindex = -1;
    L->cursor=NULL;
    return(L);
}
//Destructor
void freeList(List* pL)
{
    if(*pL==NULL ||pL==NULL)
    {
        return;
    }
    clear(*pL);
    free(*pL);
    *pL=NULL;
}


// Access functions -----------------------------------------------------------
//length()
//Returns the number of elements in List
int length(List L) {
    if (L == NULL) {
        printf("List Error: length() called on NULL List reference\n");
        exit (1);
    }
    int herelength = 0;
    if (L->front == NULL){
        return herelength;
    }
    Node a = L-> front;
    while (a != NULL){
        herelength++;
        a = a->next;
    }
    return L->newlength;
}
//If cursor is defined return the index
int index(List L)
{
    if(L->cursor==NULL){
        fprintf(stderr, "List error: here is no list exist\n");
        //exit(1);
        return -1;
    }
    else
        return L->newindex;
}
//Returns front element
//Pre:elgnth()>0
int front(List L)
{
    if(L->newlength<1) {
        printf("List Error: calling front() on List length<1");
        exit(1);
    }
    return L->front->data;
}

//Returns back elemenet
//Pre:length()>0
int back(List L)
{
    if(L->newlength<1) {
        printf("List Error: calling back() on List length<1");
        exit(1);
    }
    return L->back->data;
}
//Returns cursor element
//Pre:length()>0, index()>=0
int get(List L)
{
    if(length(L)<=0) {
        printf("List Error: calling get() on List length<1");
        exit(1);
    }
    else if(index(L)==-1||L->cursor==NULL) {
        printf("List Error:calling get() on List cursor index <0");
        exit(1);
    }
    return L->cursor->data;
}
//equals()
//Return 1 if both L have same element and length
// gnored the function where cursor comes.
//the sequence stops here.
int equals(List A, List B)
{

    int newequal; newequal=0;
    if(length(A)==length(B))
    {
        Node a = NULL;
        Node b = NULL;
        for(a=A->front, b=B->front; a!=NULL ; a=a->next, b=b->next)
        {
            if(a->data==b->data)
            {
                newequal=1;
                a = a->next;
                b = b ->next;
            }
            else
            {
                return 0;
            }
        }
        return newequal;
    }
    else
        return 0;
}
// Manipulation procedures ----------------------------------------------------
//clear the list for next function
//Resets this List to its original empty state

void clear(List L)
{
    L->newindex=-1;
    L->newlength=0;
    L->front=L->back=L->cursor=NULL;
    while (length(L));
    deleteBack(L);
}
//moveFront()
//If List is non-emepty, places the cursor at front element, otherwise, do nothing
void moveFront(List L)
{
    L->cursor=L->front;
    L->newindex=0;
}
//moveBack()
//place curosr at Back element if List is not empty
void moveBack(List L)
{
    L->cursor=L->back;
    L->newindex=length(L)-1;
}
//movePrev()
// If cursor is defined and not at front, moves cursor one step toward
// front of this List, if cursor is defined and at front, cursor becomes
// undefined, if cursor is undefined does nothing.
void movePrev(List L)
{
    if(L->cursor->previ==NULL && L->cursor==L->front)
    {
        L->cursor=NULL;
        L->newindex=-1;
    }
    else
    {
        L->cursor=L->cursor->previ;
        L->newindex--;
    }
}
//moveNext()
// If cursor is defined and not at back, moves cursor one step toward
// back of this List, if cursor is defined and at back, cursor becomes
// undefined, if cursor id equal to 1 then move the list to front
void moveNext(List L)
{
    if(L->cursor->next==NULL && L->cursor==L->back) {
        L->cursor=NULL;
        L->newindex=-1;
    }
    else {
        L->cursor=L->cursor->next;
        L->newindex++;
    }
}
//prepend()
// Insert new number into this List. If List is non-empty,
// insertion takes place before front number.
void prepend(List L, int data)
{
    if(L->newlength==0) {
        L->front=L->back=L->cursor=newNode(data);
        L->newlength++;
        L->newindex=0;
    }
    else {
        Node G=newNode(data);
        G->next=L->front;
        L->front->previ=G;
        L->front=G;
        L->newlength++;
        L->newindex++;
    }
}
//append(int data)
// Insert new element into this List. If List is non-empty,
// insertion takes place after back element.
void append(List L, int data)
{
    if(L->newlength==0) {
        L->front=L->back=L->cursor=newNode(data);
        L->newlength++;
        L->newindex++;
    }
    else {
        Node G = newNode(data);
        L->back->next=G;
        G->previ=L->back;
        L->back=G;
        L->newlength++;
    }
}
//insertBefore(int data)
// Insert new element before cursor.
// Pre: length()>0, index()>=0
void insertBefore(List L, int data)
{
    if(L->newlength<=0) {
        printf("List Error:insertBefore() pre-condition violated, length<=0");
        exit(1);
    }
    else if(L->newindex<0) {
        printf("List Error:insertBefore() pre-condition violated, index<0");
        exit(1);
    }
    else if(L->cursor->previ==NULL) {
        L->front= newNode(data);
        L->cursor->previ=L->front;
        L->front->next=L->cursor;
        L->newindex++;
    }
    else {
        Node N=newNode(data);
        N->next=L->cursor;
        movePrev(L);
        N->previ=L->cursor;
        L->cursor->next=N;
        moveNext(L);
        moveNext(L);
        L->cursor->previ=N;
    }
    L->newlength++;
}
//insertAfter(int data)
// Inserts the new element after cursor for this function
// Pre: length()>0, index()>=0 for list
void insertAfter(List L, int data) {
    if(L->newlength<=0) {
        printf("List Error:insertBefore(int data) pre-condition violated, length()<=0");
        exit(1);
    }
    else if(L->cursor->next==NULL) {
        L->back=newNode(data);
        L->back->previ=L->cursor;
        L->cursor->next=L->back;
    }
    else {
        Node N=newNode(data);
        N->previ=L->cursor;
        moveNext(L);
        N->next=L->cursor;
        L->cursor->previ=N;
        movePrev(L);
        movePrev(L);
        L->cursor->next=N;
        L->newindex++;
    }
    L->newlength++;
}
//deleteFront()
//Deletes the front list for function
//Pre:length()>0
void deleteFront(List L) {
    if(L->newlength<1) {
        printf("List Error:deleteFront() pre-condition violated, length<1");
        exit(1);
    }if ( L->newlength <=0){
        printf("List Error: deleteFront() called on NULL List reference\n");
        exit(1);
    }
    else {
        L->front=L->front->next;
        freeNode(&mkstemp);
        if(L->newindex==0)
        {
            L->cursor=NULL;
            L->newindex=-1;
        }
        else
            L->newindex--;
        L->newlength--;
    }
}
//deleteBack()
//Deletes the back element
//Pre:length()>0
void deleteBack(List L) {
    /*if(L->newlength<1) {
        printf("List Error:deleteFront() pre-condition violated, length<1");
        exit(1);
    }
    else {
        L->back=L->back->previ;
        if(L->newindex==L->newlength-1) {
            L->cursor=NULL;
            L->newindex=-1;
        }
        L->newlength--;
    }*/
    Node deleteBack = L->back;
    if(length(L)== 0){
        return;
    }
    if(length(L)>1){
        L->back = L->back->previ;
        L->back->next = NULL;
        freeNode(&deleteBack);
    }
    else if(length(L)==1){
        L->back = L->front = NULL;
        freeNode(&deleteBack);
    }if(L->back == L->cursor){
        L->cursor = NULL;
    }
}
//delete()
// Deletes cursor element, making cursor undefined.
// Pre: length()>0, index()>=0
void delete(List L) {
    if(length(L)<1) {
        printf("List Error:delete() pre-condition violated, length<1");
        exit(1);
    }
    else if(index(L)<1) {
        printf("List Error:delete() pre-condition violated, index()<1");
        exit(1);
    }
    else {
        if(index(L)==0) {
            deleteFront(L);
        }
        else if(index(L)==length(L)-1) {
            deleteBack(L);
        }
        else {
            Node A=L->cursor->next;
            Node B=L->cursor->previ;
            B->next=A;
            A->previ=B;
            L->cursor=NULL;
            L->newindex=-1;
            L->newlength--;
        }
    }
}
//Other methods------------
//printList()
//print out element in L to out File
void printList(FILE* out, List L) {
    Node n=NULL;
    for(n=L->front; n!=NULL; n=n->next) {
        fprintf(out,"%d ", n->data);
    }
}
//copyList()
//copy the function for index and state the cursor at list
//return the new list for this function
List copyList(List L) {
    List newtemp=newList();
    Node a=NULL;
    for(a=L->front; a!=NULL; a=a->next) {
        append(newtemp, a->data);
    }
    newtemp->cursor=NULL;
    newtemp->newindex=-1;
    return newtemp;
}
