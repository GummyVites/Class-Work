//Kevin Lee
//1480757
//Pa4
//Queue.java

public class Queue implements QueueInterface{

    //Node Class
    private class Node{
        Object item;
        Node next;

        Node(Object x){
            item = x;
            next = null;
        }
    }

    private int numItems;
    private Node front;
    private Node back;

    public Queue(){
        numItems = 0; 
        front = back;
        back = null;
    }
    

    //isEmpty()
    //pre: none
    //post: returns true if this Queue is empty, false otherwise
    public boolean isEmpty(){
        return(front==null);
    }

    //length()
    //pre: none
    //post: returns the length of this Queue.
    public int length(){
        int count = 0;
        Node N = front;
        while(N!=back){
        
            N = N.next;
            count ++;
        }
        return count;
    }

    //enqueue()
    //adds newItem to back of this Queue
    //pre: none
    //post: !isEmpty()
    public void enqueue(Object newItem){
        if(numItems==0){
            Node N = new Node(newItem);
            front = N;
            N.next = back;
            numItems++;
        }else{
            Node N = front;
            for(int i =1; i<numItems;i++){
                N=N.next;
            }
            Node P = N.next;
            N.next = new Node(newItem);
            N=N.next;
            N.next=back;
            numItems++;
        }       
    }

    //dequeue()
    //deletes and returns item from front of this Queue
    ///pre: !isEmpty()
    //post: this Queue will have one fewer element
    public Object dequeue() throws QueueEmptyException{
        if(numItems==0){
            throw new QueueEmptyException("cannot dequeue() empty queue");
        }
        Node N = front;
        front = N.next;
        N.next = null;
        numItems--;
        return N.item;
    }

    //peek()
    //pre: !isEmpty()
    //post: returns item at front of Queue
    public Object peek() throws QueueEmptyException{
        if(numItems==0){
            throw new QueueEmptyException("cannot peek() on an empty queue");
        }
        Node N=front;
        return N.item;
    }

    //dequeueAll()
    //sets this Queue to the empty state
    //pre: !isEmpty()
    //post: isEmpty()
    public void dequeueAll() throws QueueEmptyException{
        if(numItems==0){
            throw new QueueEmptyException("cannot dequeueAll() empty queue");
        }
        numItems=0;
        front=back;

    }

    //toString()
    //overrides Object's toString() method
    public String toString(){
        StringBuffer sb = new StringBuffer();
        Node N=front;
        while(N!=back){
            sb.append(N.item).append(" ");
            N=N.next;
        }
        sb.append("\n");
        return new String(sb);
    }
}

