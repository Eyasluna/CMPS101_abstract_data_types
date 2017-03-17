/**List.java
 * An integer List ADT
 * CMPS101 PA1:List.java
 * Integer List ADT is a bi-directional queue that has a cursor for List
 *
 */
class List
{
    private class Node
    {
        // Fields
        int data;
        Node next;
        Node prev;

        // Constructor
        Node(int data)
        {
            this.data = data;
            next = null;
            prev = null;
        }

        // toString():  overrides Object's toString() method
        public String toString()
        {
            return String.valueOf(data);
        }

        // equals(): overrides Object's equals() method
        public boolean equals(Object x)
        {
            boolean eq = false;
            Node that;
            if(x instanceof Node){
                that = (Node) x;
                eq = (this.data==that.data);
            }
            return eq;
        }
    }
    // Fields
    private Node front;
    private Node back;
    private Node cursor;
    private int length;
    private int cursoras;

    // Constructor
    List() //Creates a new empty List.
    {
        front = back = cursor=null;
        length = 0;
        cursoras=-1;
    }

    // Access Functions --------------------------------------------------------

    //length()
    //Returns the number of elements in this List.
    int length()
    {
        return length;
    }

    //index()
    //If cursor is defined, returns the index of the cursor element, otherwise returns -1
    int index()
    {
        if(cursor==null)
        {
            return cursoras=-1;
        }
        return cursoras;
    }

    //front()
    //Returns front element
    //Pre:length()>0
    int front()
    {
        if(length()<0)
            throw new RuntimeException("List Error:front() pre-condition violated, length()<0");
        return front.data;
    }

    //back()
    //Returns back element
    //Pre:length()>0
    boolean isEmpty(){
        return length ==0;
    }

    int back()
    {
        if(this.length()==0)
            throw new RuntimeException("List Error:back() pre-condition violated, length()<0");
        return back.data;
    }

    //get()
    //Returns cursor element. Pre:length()>0, index()>=0;
    int get()
    {
        if(length()<0)
            throw new RuntimeException("List Error:get() pre-condition violated, length()<0");
        if(index()==-1)
            throw new RuntimeException("List Error:get() pre-condition violated, index==-1");
        return cursor.data;
    }

    //equals(List L)
    //Returns true if this List and L are the same integer
    //sequence. The cusor is ignored in both lists
    boolean equals(List L)
    {
        boolean baba=false;
        boolean fiest=true;
        Node X=L.front;
        Node Y=this.front;
        if(this.length()==L.length())
        {
            for(int i=0; i<this.length(); i++)
            {
                if(X.data==Y.data)
                    baba=true;
                else
                    return false;
                X=X.next;
                Y=Y.next;
            }
            return baba;
        }
        else
            return baba;
    }

    // Manipulation Procedures -------------------------------------------------
    //clear()
    // Resets this List to its original empty state
    void clear()
    {
        this.front=this.back=this.cursor=null;
        this.cursoras=-1;
        this.length=0;
        length = 0;
        cursor = null;
        front = null;
        back = null;
        cursoras = -1;
    }
    //moveFront()
    //If List is non-empty, places the cursor under the front element, otherwise does nothing.
    void moveFront()
    {
        cursor=front;
        cursoras=0;
    }
    //moveBack()
    //If List is non-empty, places the cursor under the back element, otherwise does nothing.
    void moveBack()
    {
        cursor=back;
        cursoras=length-1;
    }

    //movePrev()
    // If cursor is defined and not at front, moves cursor one step toward
    // front of this List, if cursor is defined and at front, cursor becomes
    // undefined, if cursor is undefined does nothing.
    void movePrev()
    {
        if(cursor.prev==null&& cursor==front)
        {
            cursor=null;
        }
        else
        {
            cursor=cursor.prev;
            cursoras--;
        }
    }

    //moveNext()
    // If cursor is defined and not at back, moves cursor one step toward
    // back of this List, if cursor is defined and at back, cursor becomes
    // undefined, if cursor is undefined does nothing.
    void moveNext()
    {
        if(cursor.next==null && cursor == back)
        {
            cursor=null;
        }
        else
        {
            cursor=cursor.next;
            cursoras++;
        }
    }

    //prepend(int data)
    // Insert new element into this List. If List is non-empty,
    // insertion takes place before front element.
    void prepend(int data)
    {
        if(this.length()==0)
        {
            front=back=cursor=new Node(data);
            length++;
            cursoras=0;
        }
        else
        {
            Node N = new Node(data);
            N.next=front;
            front.prev=N;
            this.front=N;
            length++;
            cursoras++;
        }
    }
    //append(int data)
    // Insert new element into this List. If List is non-empty,
    // insertion takes place after back element.
    void append(int data) {
        if(this.isEmpty())
        {
            front=back=cursor=new Node(data);
            length++;
            cursoras=0;
        }
        else
        {
            Node K = new Node(data);
            back.next=K;
            K.prev=back;
            this.back=K;
            length++;
        }
    }
    //insertBefore(int data)
    // Insert new element before cursor.
    // Pre: length()>0, index()>=0
    void insertBefore(int data)
    {
        if(length()<=0)
        {
            throw new RuntimeException("List Error:insertBefore(int data) pre-condition violated, length()<=0");
        }
        else if(index()<0)
        {
            throw new RuntimeException("List Error:insertBefore(int data) pre-condition violated, index()<0");
        }
        else if(cursor.prev==null)
        {
            front=new Node(data);
            cursor.prev=front;
            front.next=cursor;
            cursoras++;
        }
        else
        {
            Node N = new Node(data);
            N.next=cursor;
            movePrev();
            N.prev=cursor;
            cursor.next=N;
            moveNext();
            moveNext();
            cursor.prev=N;
        }
        length++;
    }

    //insertAfter(int data)
    // Inserts new element after cursor.
    // Pre: length()>0, index()>=0
    void insertAfter(int data)
    {
        if(length()<=0)
        {
            throw new RuntimeException("List Error:insertBefore(int data) pre-condition violated, length()<=0");
        }
        else if(index()<0)
        {
            throw new RuntimeException("List Error:insertBefore(int data) pre-condition violated, index()<0");
        }
        else if(cursor.next==null)
        {
            back=new Node(data);
            back.prev=cursor;
            cursor.next=back;
        }
        else
        {
            Node first = new Node(data);
            first.prev=cursor;
            moveNext();
            first.next=cursor;
            cursor.prev=first;
            movePrev();
            movePrev();
            cursor.next=first;
            cursoras++;
        }
        length++;
    }

    //deleteFront()
    //Deletes the front element
    //Pre:length()>0
    void deleteFront()
    {
        if(this.length() == 0) {
            throw new RuntimeException("List Error: deleteFront() called on empty List");
        }
        if(cursor == front) {
            cursor = null;
            cursoras = -1;
        } else {
            cursoras--;
        }
        front = front.next;
        front.prev = null;
        length--;
    }

    //deleteBack()
    //Deletes the back element
    //Pre:length()>0
    void deleteBack()
    {
        if(this.length()==0)
            throw new RuntimeException("List Error:deleteFront() pre-condition violated, length<1");
        else
        {
            back=back.prev;
            if(cursoras==length-1)
            {
                cursor=null;
                cursoras=-1;
            }
            length--;
        }
    }

    //delete()
    // Deletes cursor element, making cursor undefined.
    // Pre: length()>0, index()>=0
    void delete()
    {
        if(length<1)
            throw new RuntimeException("List Error:delete() pre-condition violated, length<1");
        else if(index()<1)
            throw new RuntimeException("List Error:delete() pre-condition violated, index()<1");
        else
        {
            if(cursoras==0)
            {
                deleteFront();
            }
            else if(cursoras==length()-1)
            {
                deleteBack();
            }
            else
            {
                Node here=cursor.next;
                Node no=cursor.prev;
                no.next=here;
                here.prev=no;
                cursor=null;
                cursoras=-1;
                length--;
            }
        }
    }
    //Other methods

    //toString()
    // Overrides Object's toString method. Returns a String
    // representation of this List consisting of a space
    // separated sequence of integers, with front on left.
    public String toString()
    {
        String result=new String();
        Node N=front;
        for(int i=0; i<length; i++)
        {
            result=result+N.data+" ";
            N=N.next;
        }
        return result;
    }

    //copy()
    // Returns a new List representing the same integer sequence as this
    // List. The cursor in the new list is undefined, regardless
    List copy()
    {
        List here=new List();
        Node M=front;
        for(int i=0; i<this.length(); i++)
        {
            here.append(M.data);
            M=M.next;
        }
        here.cursor=null;
        here.cursoras=-1;
        return here;
    }
    public List concat(List L) {
        List concat = copy();
        Node current = L.front;
        while(current != null) {
            concat.append(current.data);
            current = current.next;
        }
        concat.length = length + L.length;
        return concat;
    }
}
