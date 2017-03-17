package com.company;

/**CMPS101 Winter 2017
 * MatrixTest.java
 * the test class for List ADT
 * Yibo Fu
 */
public class MatrixTest{
    public static void main(String[] args){
        int i = 0, j = 0, n=5;
        Matrix A = new Matrix(n);
        Matrix B = new Matrix(n);


        A.changeEntry(1,4,7);
        A.changeEntry(1,5,2);

        A.changeEntry(2,2,18);
        A.changeEntry(2,4,7);
        A.changeEntry(2,3,64);

        A.changeEntry(3,1,99);
        A.changeEntry(3,4,1);

        A.changeEntry(4,1,1);
        A.changeEntry(4,2,-1);
        A.changeEntry(4,3,0);

        A.changeEntry(5,5,12);

        A.changeEntry(1,1,3);
        A.changeEntry(1,2,9);
        A.changeEntry(1,3,1);

        B=A.transpose();
        System.out.println("Matrix A: ");
        System.out.println(A);

        System.out.println("Matrix 3*A: ");
        System.out.println(A.scalarMult(3));

        System.out.println("Matrix B(Transpose(A)): ");
        System.out.println(B);

        System.out.println("Matrix A+B: ");
        System.out.println(A.add(B));

        System.out.println("Matrix A+A: ");
        System.out.println(A.add(A));

        System.out.println("Matrix B-A: ");
        System.out.println(B.sub(A));

        System.out.println("Matrix A-A: ");
        System.out.println(A.sub(A));

        System.out.println("Matrix A*B: ");
        System.out.println(A.mult(B));

        System.out.println("Matrix B*B: ");
        System.out.println(B.mult(B));
    }
}
