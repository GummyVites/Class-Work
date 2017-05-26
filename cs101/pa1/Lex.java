//Kevin Lee
//1480757
//pa1
//Cmps101
//Lex.java

import java.io.*;
import java.util.Scanner;

class Lex{
    public static void main(String[] args) throws IOException{
        Scanner in = null;
        Scanner in2 = null;
        PrintWriter out = null;
        String line = null;
        String[] token = null;
        int n, lineNumber = 0;
        String[] ArrayOne;
        List list = new List();
        int i = 0;

        if (args.length != 2){
            System.err.println ("Lex infile outfile");
            System.exit(1);
        }

        in = new Scanner(new File(args[0]));
        in2 = new Scanner(new File(args[0]));
        out = new PrintWriter(new FileWriter(args[1]));

        while( in.hasNextLine() ){
            lineNumber++;
            in.nextLine();
        }
        in.close();
        ArrayOne = new String[lineNumber];
        while(in2.hasNextLine()){      
            ArrayOne[i]=  in2.nextLine();
            i++;
        }
	in2.close();

	list.prepend(0);
	//System.out.println(lineNumber);
        for(i=1; i<lineNumber; i++){ 
            String temp = ArrayOne[i];
	    list.moveFront();
		if (temp.compareTo(ArrayOne[list.back()])>0){
                        list.append(i);
                }
                else if (temp.compareTo(ArrayOne[list.front()])<0){
                        list.prepend(i);
                }
                else if(list.index() != -1){
                        while(temp.compareTo(ArrayOne[list.get()])>0){
                        //System.out.println("loop");
                        list.moveNext();
                }
        list.insertBefore(i);

	}
     }
       
        for(list.moveFront(); list.index()>=0; list.moveNext()){
            out.println(ArrayOne[list.get()]+" ");
        }
        out.close();
    }


}
