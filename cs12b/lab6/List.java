//Kevin Lee
//CMPS12B
//Lab6
//1480757
//List.java
//Array based implementation of List ADT (with array doubling)

@SuppressWarnings("overrides")
class List<T> implements ListInterface<T>{
 
    private static final int INITIAL_SIZE = 1;
    private int arraySize;
    private T[] item;
    private int numItems;

   // IntegerList()
   // Constructor for the IntegerList class 
   @SuppressWarnings("unchecked")
   public List(){
      arraySize = INITIAL_SIZE;
      item = (T[])new Object[arraySize];
      numItems = 0;
   }

   // arrayIndex()
   // transforms a List index to an Array index
   private int arrayIndex(int listIndex){
      return listIndex-1;
   }

   // doubleItemArray()
   // doubles the physical size of the underlying array item[]
   @SuppressWarnings("unchecked")
   private void doubleItemArray(){
      arraySize *=2;
      T[] newArray = (T[]) new Object[arraySize];
      for(int i=0; i<numItems; i++){
         newArray[i] = item[i];
      }
      item = newArray;
   }

   // isEmpty()
   // pre: none
   // post: returns true if this IntgerList is empty, false otherwise
   public boolean isEmpty(){
   return(numItems == 0);
   }

   // size()
   // pre: none
   // post: returns the number of elements in this IntegerList
   public int size(){
   return numItems;
   }

   // get()
   // pre: 1 <= index <= size()
   // post: returns item at position index
   public T get(int index) throws ListIndexOutOfBoundsException {
      
      if( index<1 || index>numItems ){
         throw new ListIndexOutOfBoundsException(
             "get() called on invalid index " +index);
      }
      return item[arrayIndex(index)];
   }

   // add()
   // inserts newItem in this IntegerList at position index
   // pre: 1 <= index <= size()+1
   // post: !isEmpty(), items to the right of newItem are renumbered
   public void add(int index, T newItem) 
      throws ListIndexOutOfBoundsException{
      
      if( index<1 || index>(numItems+1) ){
         throw new ListIndexOutOfBoundsException(
            "IntegerList Error: add() called on invalid index");
      }
      
      if( numItems == arraySize ) {
         doubleItemArray();
      }
      
      for(int i=numItems; i>=index; i--) {
         item[arrayIndex(i+1)] = item[arrayIndex(i)];
      }
      item[arrayIndex(index)] = newItem;
      numItems++;
   }


   // remove()
   // deletes item from position index
   // pre: 1 <= index <= size()
   // post: items to the right of deleted item are renumbered
   public void remove(int index)
      throws ListIndexOutOfBoundsException{
         
      if( index<1 || index>numItems ){
         throw new ListIndexOutOfBoundsException(
            "IntegerList Error: remove() called on invalid index");
      }
      
      for(int i=index+1; i<=numItems; i++){
         item[arrayIndex(i-1)] = item[arrayIndex(i)];
      }
      numItems--;
   }
   // removeAll()
   // pre: none
   // post: isEmpty()
   public void removeAll(){
      numItems = 0;
   }

   // toString()
   // pre: none
   // post:  prints current state to stdout
   // Overrides Object's toString() method
   public String toString(){
      int i;
      String s = "";

      for(i=0; i<numItems; i++) s += item[i] + " ";
      return s;
   }

   // equals()
   // pre: none
   // post: returns true if this IntegerList matches rhs, false otherwise
   // Overrides Object's equals() method
   @SuppressWarnings("unchecked")
   public boolean equals(Object rhs){
      int i = 0;
      boolean eq = false;
      List<T> R = null;

      if(rhs instanceof List){
         R = (List<T>)rhs;
         eq = (this.numItems == R.numItems);
         while(eq && i<numItems){
            eq = (this.item[i] == R.item[i]);
            i++;
         }
      }
      return eq;
   }

}

