package collection;

import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {
	public static void main(String[] args) {
		Queue<String> q = new LinkedList<>();
		
		q.offer("둘리");
		q.offer("마이콜");
		q.offer("도우너");
		
		while(!q.isEmpty()) {
			String string = q.poll();
			System.out.println(string);
		}
		
		System.out.println(q.poll());
		System.out.println(q.peek());
		System.out.println(q.poll());
		
	}
}
