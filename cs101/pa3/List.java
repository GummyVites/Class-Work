//Kevin Lee
//1480757
//pa3
//cmps101
//List.java

class List{
    private class Node{
        Object data;
        Node next;
        Node previous;

        Node(Object data){
            this.data = data;
            next = null;
            previous = null;
        }

        public String toString(){
            return String.valueOf(data);
        }
    }

    private Node front;
    private Node back;
     int length;
    private int indexCursor;
    private Node cursor;

    List(){
        indexCursor = 0;
        front = null;
        back = null;
        cursor = null;
        length = 0;
    }

    int length(){
        return length;
    }

    int index(){
        if (indexCursor >= -1){
            return indexCursor;
        }
        else{ return -1;
        }
    }

    Object front(){
        if (length()>0)
            return front.data;
        else
            throw new RuntimeException("Cannot front() on empty list.");
    }

    Object back(){
        if (length()>0)
            return back.data;
        else
            throw new RuntimeException("Cannot back() on empty list.");
    }

    Object get(){
        if (length()>=0 && indexCursor>=0)
            return cursor.data;
        else
            throw new RuntimeException("Cannot get() on empty list.");
    }

    public boolean equals(Object x){
        boolean bool = false;
        List L;
        Node N, M;

        if(x instanceof List){
            L = (List)x;
            N = this.front;
            M = L.front;
            bool = (this.length==L.length);
            while(bool && N!=null ){
                bool = N.equals(M);
                N  = N.next;
                M  = M.next;
            }
        }
        return bool;
    }


    void clear(){
        front = null;
        back = null;
        indexCursor= 0;
        length = 0;
    }

    void moveFront(){
        if (length>0){
            cursor = front;
            indexCursor = 0;
        }else{ return;
        }
    }

    void moveBack(){
        if(length > 0){
            cursor = back;
            indexCursor = length-1;
        }else{ return;
        }
    }

    void movePrev(){
        if (cursor !=null && indexCursor != 0){
            cursor = cursor.previous;
            indexCursor--;
        }
        else if (indexCursor== 0){
            indexCursor = -1;
            cursor= null;

        }else{
            return;
        }
    }

    void moveNext(){
        if (cursor!= null && cursor!= back){
            cursor = cursor.next;
            indexCursor++;
        }else if (cursor!= null && cursor == back){
            cursor =null;
            indexCursor=-1;
        }
        else{
            return;
        }
    }

    void prepend(Object data){
        Node element = new Node(data);
        if (length==0){
            front = back = element;
            length++;
        }
        else{
            element.next =front;
            front.previous = element;
            front = element;
            indexCursor++;
            length++;
        }
    }

    void append(Object data){
        Node element = new Node(data);
        if(length==0){
            front = back = element;
            length++;
        }
        else{
            back.next = element;
            element.previous = back;
            back = element;
            length++;
        }
    }

    void insertBefore(Object data){
        Node element = new Node(data);
        Node previous = cursor.previous;
        if( length <= 0 || indexCursor< 0){
            throw new RuntimeException( "List Error: insertAfter called on an empty list.");
        }
        if( indexCursor == 0){
            prepend(data);
        }
        else{
            element.previous = previous;
            element.next = cursor;
            previous.next = element;
            cursor.previous= element;
            length++;
            indexCursor++;
        }
    }

    void insertAfter(Object data){
        Node element = new Node(data);
        Node next = cursor.next;
        if( length <= 0 || indexCursor < 0){
            throw new RuntimeException( "List Error: insertAfter called on an empty list.");
        }
        if( indexCursor == length - 1){
            append(data);
        }

        else{
            element.previous = cursor;
            element.next = cursor.next;
            next.previous = element;
            cursor.next= element;
            length++;
            indexCursor++;
        }
    }

    void deleteFront() {
        if(length <= 0){
            throw new RuntimeException("List Error: deleteFront called on an empty list.");
        }
        else{
            front = front.next;
            length--;
        }
    }

    void deleteBack(){
        if(length <= 0){
            throw new RuntimeException("List Error: deleteBack called on an empty list.");
        }
        else{
            cursor = back;
            back = back.previous;
            length--;
        }
    }

    void delete(){
        if(length() <= 0 || indexCursor< 0){
            throw new RuntimeException("List Error: delete called on an empty list.");
        }
        else{
            cursor.previous.next = cursor.next;
            cursor.next = cursor.previous;
            cursor = null;
            indexCursor = -1;
            length--;
        }
    }

    public String toString(){
        String str = "";
        for(Node N=front; N!=null; N=N.next){
            str += N.toString() + " ";
        }
        return str;
    }
}
