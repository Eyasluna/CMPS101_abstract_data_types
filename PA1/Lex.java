/**
 * CMPS 101 Winter 2017 PA1:Lex.java
 * A main program uses List ADT to sort words in lexicographic order.
 * Lex takes a text file as input and sort them by comparing each element with other element and finding which is smaller in
 alphabetical order and store that into List and output a text file at the end when everything is done.
 *
 */
import java.io.*;
import java.util.Scanner;

public class Lex {
    public static void main(String[] args) throws IOException{
        Scanner in = null;
        PrintWriter out = null;
        int linenum=0;
        in = new Scanner(new File(args[0]));

        if (args.length < 2 ){
            System.out.println("Usage: FileLO infile outfile");
            System.exit(1);
        }
        // to count the num of lines in all the input files
        while( in.hasNextLine()){
            linenum++;
            in.nextLine();
        }

        String newinput[]=null;
        String newline = null;

        while (in.hasNextLine()){
            linenum++;
            newline= in.nextLine();
            newinput[linenum-1]=newline;
        }
        //insertion sort for the string for words into list of each files
        List A=new List();
        A.append(0);
        //use the array length for all the loop and initalized to 1
        //start curor at index zero to the list
        boolean action=false;
        for(int a=1; a<linenum; a++)
        {
            //compare the first array number to the array element with the index
            if(newinput[a].compareTo(newinput[A.get()])>0==false)
            {
                A.prepend(a);
            }
            else
            {
                while(newinput[a].compareTo(newinput[A.get()])>0==true && A.index()+1!=A.length())
                {
                    A.moveNext();
                    if(newinput[a].compareTo(newinput[A.get()])>0==false)
                    {
                        A.insertBefore(a);
                        action=true;
                        break;
                    }
                }
                if(action!=true) {
                    A.append(a);
                }
            }
            A.moveFront();
            action=false;
        }
        //the test files for outputs
        out = new PrintWriter(new FileWriter(args[1]));
        //print the outfile
        //close outfile down here
        A.moveFront();
        for(int M=0; M<A.length(); M++)
        {
            out.println(newinput[A.get()]);
            A.moveNext();
        }
        //close the scanner input and printwriter for program
        in.close();
        out.close();


    }
}
