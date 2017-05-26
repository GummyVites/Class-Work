//Kevin Lee
//1480757
//CMPS 12B
//Dictionary.java
public class Dictionary implements DictionaryInterface{

    private class Node {
        String key;
        String value;
        Node next;
        Node(String key, String value){
            this.key=key;
            this.value=value;
        }
    } 

    private Node head;
    private int numItems;
    
    public Dictionary(){
      head = null;
      numItems = 0;
   }

    private Node findKey(String key){
        Node N = head;
        while(N != null){
            if (N.key != key){
                N=N.next;
            }else{ 
                return N;
            }
        }
        return null;
    }

    public boolean isEmpty(){
        return (numItems==0);
    }

    public int size(){
        return numItems;
    }

    public String lookup(String key){
        Node N=findKey(key);
        if(N==null){
            return null;
        }else{
            return N.value; 
        }
    }

    public void insert(String key, String value) throws DuplicateKeyException{
        if(lookup(key)!=null){
            throw new DuplicateKeyException("Dictionary Error: insert() "+key+" already exist");   
        }
        if(head==null){
          Node N=new Node(key, value);
          head = N;
          numItems++;
       }else{
          Node N = head;
          for(int i=1;i<numItems;i++){
             N=N.next;
          }
          Node B = new Node(key, value);
          N.next=B;
          numItems++;
       }               
   }

    public void delete(String key) throws KeyNotFoundException{
        if(lookup(key)==null){
            throw new KeyNotFoundException("Dictionary Error: " + key + " not found");
        }
        Node N = head;
        if(N.key == key){
                head = N.next;
                numItems--;
            }else{
                while(N.next.key != key){
                    N = N.next;
                }
                N.next = N.next.next;
                numItems--;
            }
        }
    

    public void makeEmpty(){
        head = null;
        numItems=0;
    }

    public String toString(){
        String s = "";
        Node N = head;
        while( N != null){
            s = s+ N.key + " " + N.value + "\n"; 
            N = N.next;
        }
        return s;

    }
}
