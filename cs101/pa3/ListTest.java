//Kevin Lee
//1480757
//pa3
//cmps101
//ListTest.java

public class ListTest{
    public static void main(String[] args){
        List A = new List();
        List B = new List();

        for(int i=1; i<=20; i++){
            A.append(i);
            B.prepend(i);
        }
        
        System.out.println(A);
        System.out.println(B);
        A.length();
        B.length();
        A.front();
        B.front();
        A.back();
        B.back();

        for(A.moveFront(); A.index()>=0; A.moveNext()){
            System.out.print(A.get()+" ");
        }


        System.out.println(A.equals(B));
        System.out.println(B.equals(A));
        System.out.println();
        System.out.println();

        A.insertBefore(-1);
        System.out.println(A);
        A.insertAfter(-7);
        System.out.println(A);
        A.get();
        B.get();

        A.delete();

        A.insertAfter(10);
        System.out.println(A);
        A.insertAfter(0);
        A.insertAfter(16);
        A.insertAfter(27);
        A.insertBefore(0);
        A.insertBefore(9);
        A.insertBefore(99);
        System.out.println(A);

        A.insertAfter(100);
        System.out.println(A);

        System.out.println(A.length());
        A.deleteBack();
        System.out.println(A.length());
          
        A.clear();

        System.out.println(A.length());
        A.deleteFront();
        A.deleteBack();
        System.out.println(A.front());
        System.out.println(A.back());

 
    }
}
