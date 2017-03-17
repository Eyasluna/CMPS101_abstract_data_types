package com.company;

/**ListTest.java
 * the test class for List ADT
 * CMPS101 Winter 2017
 * Yibo Fu
 */
public class ListTest
{
    public static void main(String[] args)
    {
        List tester=new List();
        tester.append(4);
        tester.prepend(3);
        tester.moveBack();
        tester.insertBefore(5);
        tester.moveBack();
        tester.insertBefore(6);
        System.out.println(tester);
    }

}
