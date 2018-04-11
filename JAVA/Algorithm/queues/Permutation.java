import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.Iterator;

public class Permutation {
	public static void main(String[] args) {
		int k = Integer.parseInt(args[0]);
		RandomizedQueue<String> queue = new RandomizedQueue<String>();
		// Deque<String> queue = new Deque<String>();
		while (!StdIn.isEmpty()) {
			// queue.addFirst(StdIn.readString());
			queue.enqueue(StdIn.readString());
		}
		// StdOut.println(qsize);
		// queue.all();
		// Iterator<String> iter = queue.iterator();
		// while (iter.hasNext()) StdOut.println(iter.next());
		// StdOut.println("---");
		for (int i = 0; i < k; i++) {
			StdOut.println(queue.dequeue());
			// StdOut.println(queue.size());
			// queue.all();
		}
	}
}