//Kevin Lee
//CMPS 12B
//1480757
//Lab6
//ListTest.java
public class ListTest{
  public static void main(String[] args){
    List<String> A = new List<String>();
    List<Integer> B = new List<Integer>();
    
    A.add(1, "frog");
    A.add(2, "shoe");
    A.add(3, "pencil");
    A.add(4, "laptop");
    A.add(5, "phone");
    A.add(6, "hands");
    A.add(7,"exitsign");
    System.out.println("A: "+A);
    
    
    B.add(1, 10);
    B.add(2, 20);
    B.add(3, 30);
    B.add(4, 400);
    B.add(5, 5000);
    B.add(6, 60000);
    System.out.println("B: "+B);

    
    A.remove(2);
    A.remove(1);
    B.remove(4);

    System.out.println("A.size() is "+A.size());
    System.out.println("B.size() is "+B.size());
    System.out.println("B.get(1) is "+B.get(1));
    
    try{
         System.out.println(A.get(1000));
      }catch(ListIndexOutOfBoundsException e){
         System.out.println("Caught Exception: ");
         System.out.println(e);
         System.out.println("Continuing without interuption");
      }
    try{
         System.out.println(B.get(4000));
      }catch(ListIndexOutOfBoundsException e){
         System.out.println("Caught Exception: ");
         System.out.println(e);
         System.out.println("Continuing without interuption");
      }

    System.out.println(A.equals(B));
    System.out.println("A:" + A);
    System.out.println("B:"+ B);
    System.out.println("A.get(5) is "+A.get(5));
    System.out.println("B.get(1) is "+B.get(1));
    
  }
}
