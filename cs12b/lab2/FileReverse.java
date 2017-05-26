//Kevin Lee
//1480757
//12B
//Takes inputs trims and splits them then reverses them
//FileReverse.java
import java.io.*;
import java.util.Scanner;
class FileReverse{
    public static void main(String[] args) throws IOException{
        if(args.length < 2){
            System.out.println("Usage: FileCopy <input file> <output file>");
            System.exit(1);
        }

        Scanner in = new Scanner(new File(args[0]));
        PrintWriter out = new PrintWriter(new FileWriter(args[1]));
        while( in.hasNextLine() ){
            String line = in.nextLine().trim() + " "; //trims spaces
            String[] token = line.split("\\s+"); // splits into lines
            int n = token.length;
            for(int i=0; i<n; i++){
		int k = token[i].length(); 
                out.println(stringReverse(token[i],k-1));
            }
        }
	// close files
        in.close();
        out.close();
    }
    public static String stringReverse(String s, int n){
	if (n<0)
	    return "";
	else{
            return  s.charAt(n) + stringReverse(s,n-1); 
        }
    }
}
