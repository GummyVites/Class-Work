//Kevin Lee
//1480757
//Pa4
//QueueTest
public class QueueTest {
	public static void main (String[] args) {
		Queue A = new Queue();

		System.out.println(A.isEmpty());
		A.enqueue((int)10);
		A.enqueue((int)20);
		A.enqueue((int)30);
		A.enqueue((int)40);

		System.out.println(A.isEmpty());
		System.out.println("Length: " + A.length());
		System.out.println(A); 

		A.dequeue();
		A.dequeue();
		System.out.println(A);
		System.out.println("Length: " + A.length());
		System.out.println("Front: " + A.peek());

		
		A.dequeueAll();
		System.out.println(A.isEmpty());
		System.out.println("Length: " + A.length());
		
}
}
