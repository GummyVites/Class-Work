//Kevin Lee
//1480757
//CMPS 12B
//DictionaryTest
public class DictionaryTest{

   public static void main(String[] args){
      Dictionary A = new Dictionary();
      Dictionary B = new Dictionary();
      Dictionary C = new Dictionary();
      
      A.insert("1","Magic");
      A.insert("2","Slippers");
      A.insert("4","Gums");
      A.insert("5","Ramones");
      
  
      B.insert("log","dog");
      B.insert("sog","fog");
      B.insert("market","economy");
      
      C.insert("41markers","cups");
      C.insert("salmon","oil");
     
     //A.insert("1","Gums");
     //B.insert("economy","flack");
     //B.insert("Bar","log");
     //C.insert("41markers","cups");
     

      
      System.out.println(A.lookup("1"));
      System.out.println(A.lookup("10"));
      System.out.println(B.lookup("economy"));
      System.out.println("");
      System.out.println(C.lookup("economy"));
      System.out.println(C.lookup("oil"));
      

      
      //System.out.println(A);
      //System.out.println(B);
      //System.out.println(C);
      System.out.println(A.toString());
      System.out.println(B.toString());
      System.out.println(C.toString());
      
      //A.makeEmpty();
      //System.out.println("A.makeEmpty()= " + A);
      //B.makeEmpty();
      //System.out.println("B.makeEmpty()= " + B);
      System.out.println(A.toString());
      System.out.println(B.toString());
      //C.makeEmpty();
      System.out.println(C.size());
      System.out.println(A.size());
      //System.out.println("C.makeEmpty()= " + C);

     
      System.out.println("Dictionary A's size is: "+A.size());
      System.out.println("Dictionary B's size is: "+B.size());
      System.out.println("Dictionary C's size is: "+C.size());
     
      
     
      System.out.println("");
      System.out.println(A);
      A.delete("1");
      System.out.println(B.isEmpty());
      //System.out.println(A);
      A.delete("4");
      System.out.println(B);
      //A.delete("5");
      System.out.println(C);
      //A.delete("gums";
      //B.delete("econonomy");
      //C.delete("cups");
      //A.delete("2");
      //B.delete("sog");
      //System.out.println(A);
      //System.out.println(B);
      //System.out.println(C);
   }
}

