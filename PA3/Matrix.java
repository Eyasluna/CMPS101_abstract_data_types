package com.company;

/**CMPS 101 PA3
 * Matrix.java
 * A test class for list ADT
 * Yibo Fu
 */
class Matrix {
    private class Entry{        //Matrix private class that List use
        int newcolumn;
        double newval;
        Entry(int col,double val) {
            this.newcolumn=col;
            this.newval=val;
        }
        public String toString() {

            return "("+this.newcolumn+", "+this.newval+") ";
        }
        public boolean equals(Entry x) {
            boolean eq=false;
            if(x.newcolumn==this.newcolumn)
                if(x.newval==this.newval)
                    eq=true;
            return eq;
        }
    }
    private int newsize;
    private int ffk;
    List[] row;

    //Matrix construction
    Matrix(int n){// Makes a new n x n zero Matrix. pre: n>=1
        if(n<1)
            throw new RuntimeException("Matrix Error: Matrix(int n) pre-condition violated, n<1");
        newsize=n;
        ffk=0;
        row=new List[n+1];
        for(int i=1;i<n+1;i++) {
            row[i]=new List();
        }
    }
    int getnewSize() {
        return newsize;// Returns n, the number of rows and columns of this Matrix
    }

    int getNNZ() // Returns the number of non-zero entries in this Matrix
    {
        return ffk;
    }
    public boolean equals(Object x){
    // overrides Object's equals() method
    // Manipulation procedures
        if(this==x) {
            return true;
        }
        Matrix L=(Matrix) x;
        for(int i=1;i<L.newsize;i++) {
            List first=L.row[i];
            List second=this.row[i];
            if(first.equals(second)==false)
                return false;
        }
        return true;
    }

    void makenewZero() // sets this Matrix to the zero state
    {
        for(int i=1; i<=this.newsize; i++)
        {
            row[i]=new List();
        }
        ffk=0;
    }
    Matrix copy()// returns a new Matrix having the same entries as this Matrix
    {
        Matrix newmatrix=new Matrix(this.newsize);
        newmatrix.ffk=this.ffk;
        for(int i=1; i<=this.newsize; i++)
        {
            this.row[i].moveFront();
            while(this.row[i].index()!=-1)
            {
                newmatrix.row[i].append(this.row[i].get());
                this.row[i].moveNext();
            }
        }
        return newmatrix;
    }
    void changeEntry(int i, int j, double x)
    // changes ith row, jth column of this Matrix to x
    // pre: 1<=i<=getSize(), 1<=j<=getSize()
    {
        if(1>i || i>this.newsize)
            throw new RuntimeException("Matrix Error: changeEntry(int i, int j, double x) pre-condition violated, 1>i or i>size");
        if(1>j || j>this.newsize)
            throw new RuntimeException("Matrix Error: changeEntry(int i,int j, double x) pre-condition violated, 1>i or i>size");
        List finder=row[i];     //go to the corresponding row
        finder.moveFront();
        Entry insertstuff=new Entry(j,x);
        while(finder.index()!=-1)
        {
            Entry colchecker=(Entry) finder.get();
            if(colchecker.newcolumn==j)        //if column exist
            {
                if(x==0)
                {
                    finder.delete();    //if ij found and wanted to delete
                    ffk--;
                }
                else
                {
                    colchecker.newval=j; //if ij found and wanted to put new value
                }
                return;
            }
            else if(colchecker.newcolumn>j)
            {
                if(x!=0)
                {
                    finder.insertBefore(insertstuff);
                    ffk++;
                }
                return;

            }
            finder.moveNext();      //traverse List
        }
        if(x!=0)
        {
            finder.append(insertstuff); //if cursor falls off the list
            ffk++;
        }
    }
    Matrix scalarMult(double x)
    // returns a new Matrix that is the scalar product of this Matrix with x
    {
        Matrix nuevomatrix=new Matrix(this.newsize);
        for(int i=1;i<=this.newsize;++i)
        {
            List listtrav=this.row[i];
            listtrav.moveFront();
            while(listtrav.index()!=-1)
            {
                Entry nodetrav=(Entry) listtrav.get();
                double multi=nodetrav.newval*x;
                nuevomatrix.changeEntry(i,nodetrav.newcolumn,multi);
                listtrav.moveNext();
            }
        }
        nuevomatrix.ffk=this.ffk;
        return nuevomatrix;
    }
    Matrix add(Matrix M)
    // returns a new Matrix that is the sum of this Matrix with M
    // pre: getSize()==M.getSize()
    {
        if(this.getnewSize()!=M.getnewSize())
        {
            throw new RuntimeException("Matrix Error:add(Matrix M)pre-condition violated, two matrix is not the same size");
        }
        if(this==M)
        {
            return M.scalarMult(2);
        }
        Matrix nuematrix=new Matrix(this.newsize);
        //int newcol;
        //double newdou;
        for(int i=1;i<=this.newsize;i++)
        {
            List listtrav1=this.row[i];
            List listtrav2=M.row[i];
            listtrav1.moveFront();
            listtrav2.moveFront();
            while(listtrav1.index()!=-1 || listtrav2.index()!=-1)
            {
                while(listtrav1.index()!=-1 && listtrav2.index()!=-1)
                {
                    Entry nodetrav1=(Entry) listtrav1.get();
                    Entry nodetrav2=(Entry) listtrav2.get();
                    if(nodetrav1.newcolumn<nodetrav2.newcolumn)
                    {
                        nuematrix.changeEntry(i, nodetrav1.newcolumn, nodetrav1.newval);
                        listtrav1.moveNext();
                        nuematrix.ffk++;

                    }
                    else if(nodetrav2.newcolumn<nodetrav1.newcolumn)
                    {
                        nuematrix.changeEntry(i, nodetrav2.newcolumn, nodetrav2.newval);
                        listtrav2.moveNext();
                        nuematrix.ffk++;

                    }
                    else if(nodetrav2.newcolumn==nodetrav1.newcolumn)
                    {
                        nuematrix.changeEntry(i,nodetrav2.newcolumn,nodetrav1.newval+nodetrav2.newval);
                        listtrav1.moveNext();
                        listtrav2.moveNext();
                        nuematrix.ffk++;
                    }
                }
                if(listtrav1.index()==-1)
                {
                    while(listtrav2.index()!=-1)
                    {
                        nuematrix.row[i].append((Entry)listtrav2.get());
                        listtrav2.moveNext();
                        nuematrix.ffk++;
                    }
                }
                else if(listtrav2.index()==-1)
                {
                    while(listtrav1.index()!=-1)
                    {
                        nuematrix.row[i].append((Entry)listtrav1.get());
                        listtrav1.moveNext();
                        nuematrix.ffk++;
                    }
                }
            }
        }
        return nuematrix;
    }
    Matrix sub(Matrix M)
    // returns a new Matrix that is the difference of this Matrix with M
    // pre: getSize()==M.getSize()
    {
        if(this.getnewSize()!=M.getnewSize())
            throw new RuntimeException("Matrix Error:sub(Matrix M) pre-condition violated, this.getSize()!=M.getSize()");
        if(this==M)
            return new Matrix(this.getnewSize());
        return this.add(M.scalarMult(-1));
    }
    Matrix transpose()
    // returns a new Matrix that is the transpose of this Matrix
    {
        Matrix muematrix=new Matrix(this.getnewSize());
        for(int i=1;i<=this.getnewSize();i++)
        {
            List rowtrav=this.row[i];
            rowtrav.moveFront();
            while(rowtrav.index()!=-1)
            {
                muematrix.changeEntry(((Entry)rowtrav.get()).newcolumn, i, ((Entry)rowtrav.get()).newval);
                rowtrav.moveNext();
            }
        }
        return muematrix;
    }
    double findhelper(int i,int j)
    {
        if(i>this.getnewSize()||i==0)
        {
            return 0.0;
        }
        double num=0.0;
        this.row[i].moveFront();
        while(this.row[i].index()!=-1)
        {
            if(((Entry)this.row[i].get()).newcolumn==j)
                return ((Entry)this.row[i].get()).newval;
            this.row[i].moveNext();
        }
        return num;
    }

    Matrix mult(Matrix M)
    // returns a new Matrix that is the product of this Matrix with M
    // pre: getSize()==M.getSize()
    {
        if(this.getnewSize()!=M.getnewSize())
            throw new RuntimeException("Matrix Error:mult(Matrix M) pre-condition violated, this.getSize()!=M.getSize()");
        if(this==M)
            M=this.copy();
        double cumulative=0;
        Matrix nuematrix=new Matrix(this.getnewSize());
        for(int i=1;i<=this.getnewSize();i++)
        {
            for(int j=1;j<=this.getnewSize();j++)
            {
                cumulative=0;
                for(int k=1;k<=this.getnewSize();k++)
                {
                    cumulative+=this.findhelper(i, k)*M.findhelper(k, j);
                }
                nuematrix.changeEntry(i, j, cumulative);
            }
        }
        return nuematrix;
    }
    // Other functions
    public String toString() // overrides Object's toString() method
    {
        String out = "";
        for(int i = 1; i <= getnewSize(); i++) {
            if(row[i].length() > 0)
                out += (i + ": " + row[i] + "\r\n");
        }
        return out;
    }
}
