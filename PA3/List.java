package com.company;

/**CMPS101 WInter 2017
 *An object List ADT
 * Yibo Fu
 */
class List
{
    private class Node {// Fields
        Object data;
        Node next;
        Node prev;
        Node(Object data) {
            this.data = data;
            next = null;
            prev = null;
        }// toString():  overrides Object's toString() method
        public String toString() {
            return String.valueOf(data);
        }
    }
    private Node newfront;
    private Node newback;
    private Node cursor;
    private int newlength;
    private int newpos;

    // here is the Constructor
    List() //Creates a new empty List.
    {
        newfront = newback = cursor=null;
        newlength = 0;
        newpos=-1;
    }

    //length()
    //Returns the number of elements in this List.
    int length() {
        return newlength;
    }

    //index()
    //If cursor is defined, returns the index of the cursor element, otherwise returns -1
    int index() {
        if(cursor==null) {
            return newpos=-1;
        }
        return newpos;
    }

    //front()
    //to Returns front element
    //Pre:length()>0
    Object newfront() {
        if(length()<0)
            throw new RuntimeException("List Error:front() pre-condition violated, length()<0");
        return newfront.data;
    }

    //back()
    //Returns back element
    //Pre:length()>0
    Object newback() {
        if(length()<0)
            throw new RuntimeException("List Error:back() pre-condition violated, length()<0");
        return newback.data;
    }

    //get()
    //Returns cursor element. Pre:length()>0, index()>=0;
    Object get() {
        if(length()<0)
            throw new RuntimeException("List Error:get() pre-condition violated, length()<0");
        if(index()==-1)
            throw new RuntimeException("List Error:get() pre-condition violated, index==-1");
        return cursor.data;
    }

    //equals(List L)
    //Returns true if this List and L are the same integer
    //sequence. The cusor is ignored in both lists
    boolean equals(List L) {
        boolean equal=false;
        Node A=L.newfront;
        Node B=this.newfront;
        if(this.length()==L.length()) {
            if(A==null&&B==null)
                return true;
            for(int i=0; i<this.length(); i++) {
                if(A.data==B.data)
                    equal=true;
                else
                    return false;
                A=A.next;
                B=B.next;
            }
            return equal;
        }
        else
            return equal;
    }

    // Manipulation Procedures -------------------------------------------------
    //clear()
    // Resets this List to its original empty state
    void clear() {
        this.newfront=this.newback=this.cursor=null;
        this.newpos=-1;
        this.newlength=0;
    }
    //moveFront()
    //If List is non-empty, places the cursor under the front element, otherwise does nothing.
    void moveFront() {
        if(newfront!=null) {
            cursor=newfront;
            newpos=0;
        }
        else {
            cursor=null;
            newpos=-1;
        }
    }
    //moveBack()
    //If List is non-empty, places the cursor under the back element, otherwise does nothing.
    void moveBack() {
        if(newback!=null) {
            cursor=newback;
            newpos=newlength-1;
        }
        else {
            cursor=null;
            newpos=-1;
        }
    }

    // If cursor is defined and not at front, moves cursor one step toward
    // make the front of this List, if cursor is defined and at front, cursor becomes
    // not such undefined, if cursor is undefined does nothing.
    void movePrev() {
        if(cursor.prev==null&& cursor==newfront) {
            cursor=null;
            newpos=-1;
        }
        else {
            cursor=cursor.prev;
            newpos--;
        }
    }

    //moveNext()
    // back of this List, if cursor is defined and at back, cursor becomes
    // undefined, if cursor is undefined does nothing.
    void moveNext() {
        if(cursor.next==null && cursor == newback) {
            cursor=null;
            newpos=-1;
        }
        else {
            cursor=cursor.next;
            newpos++;
        }
    }

    // to insert new element into this List. If List is non-empty,
    // to make new doc here and insertion takes place before front element.
    void prepend(Object data) {
        if(this.length()==0) {
            newfront=newback=cursor=new Node(data);
            newlength++;
            newpos=0;
        }
        else {
            Node N = new Node(data);
            N.next=newfront;
            newfront.prev=N;
            this.newfront=N;
            newlength++;
            newpos++;
        }
    }
    //append(Object data)
    // dele to insert new element into this List. If List is non-empty,
    void append(Object data) {
        if(this.length()==0) {
            newfront=newback=cursor=new Node(data);
            newlength++;
            newpos=0;
        }
        else {
            Node N = new Node(data);
            newback.next=N;
            N.prev=newback;
            this.newback=N;
            newlength++;
        }
    }
    //here is the insertBefore(Object data)
    // Insert new element before cursor.
    // here is the Pre: length()>0, index()>=0
    void insertBefore(Object data) {
        if(length()<=0) {
            throw new RuntimeException("List Error:insertBefore(Object data) pre-condition violated, length()<=0");
        }
        else if(index()<0) {
            throw new RuntimeException("List Error:insertBefore(Object data) pre-condition violated, index()<0");
        }
        else if(cursor.prev==null) {
            newfront=new Node(data);
            cursor.prev=newfront;
            newfront.next=cursor;
            newpos++;
        }
        else {
            Node N = new Node(data);
            N.next=cursor;
            movePrev();
            N.prev=cursor;
            cursor.next=N;
            moveNext();
            moveNext();
            cursor.prev=N;
        }
        newlength++;
    }

    //to insertAfter(Object data)
    // Pre: length()>0, index()>=0
    void insertAfter(Object data) {
        if(length()<=0) {
            throw new RuntimeException("List Error:insertBefore(Object data) pre-condition violated, length()<=0");
        }
        else if(index()<0) {
            throw new RuntimeException("List Error:insertBefore(Object data) pre-condition violated, index()<0");
        }
        else if(cursor.next==null) {
            newback=new Node(data);
            newback.prev=cursor;
            cursor.next=newback;
        }
        else {
            Node N = new Node(data);
            N.prev=cursor;
            moveNext();
            N.next=cursor;
            cursor.prev=N;
            movePrev();
            movePrev();
            cursor.next=N;
            newpos++;
        }
        newlength++;
    }

    //to make a Deletes the front element
    //Pre:length()>0
    void deleteFront() {
        if(newlength<1)
            throw new RuntimeException("List Error:deleteFront() pre-condition violated, length<1");
        else {
            newfront=newfront.next;
            if(newpos==0) {
                cursor=null;
                newpos=-1;
            }
            else
                newpos--;
            newlength--;
        }
    }
    //deleteBack()
    //Pre:length()>0
    void deleteBack() {
        if(newlength<1)
            throw new RuntimeException("List Error:deleteFront() pre-condition violated, length<1");
        else {
            newback=newback.prev;
            if(newpos==newlength-1) {
                cursor=null;
                newpos=-1;
            }
            newlength--;
        }
    }

    //delete()
    // Deletes cursor element, making cursor undefined.
    // Pre: length()>0, index()>=0
    void delete() {
        if(newlength<1)
            throw new RuntimeException("List Error:delete() pre-condition violated, length<1");
        else if(index()<1)
            throw new RuntimeException("List Error:delete() pre-condition violated, index()<1");
        else {
            if(newpos==0) {
                deleteFront();
            }
            else if(newpos==length()-1) {
                deleteBack();
            }
            else {
                Node A=cursor.next;
                Node B=cursor.prev;
                B.next=A;
                A.prev=B;
                cursor=null;
                newpos=-1;
                newlength--;
            }
        }
    }
    //Other methods

    //toString()
    // Overrides Object's toString method. Returns a String
    // representation of this List consisting of a space
    // separated sequence of integers, with front on left.
    public String toString() {
        String result=new String();
        Node N=newfront;
        for(int i=0; i<newlength; i++) {
            result=result+N.data+" ";
            N=N.next;
        }
        return result;
    }

    //copy()
    // Returns a new List representing the same integer sequence as this
    // List. The cursor in the new list is undefined, regardless
    List copy() {
        List temp=new List();
        Node M=newfront;
        for(int i=0; i<this.length(); i++) {
            temp.append(M.data);
            M=M.next;
        }
        temp.cursor=null;
        temp.newpos=-1;
        return temp;
    }
}

