import java.util.Iterator;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class RandomizedQueue<Item> implements Iterable<Item> {
	private Node first = null;
	private int qsize = 0;
	private class Node {
		Item item;
		Node next;
	}

	public RandomizedQueue() {
    // construct an empty randomized queue

	}                 
	public boolean isEmpty() {                
    // is the randomized queue empty?
		return first == null;
	}
	public int size() {                       
    // return the number of items on the randomized queue
		return qsize;
	}
	public void enqueue(Item item) {          
    // add the item
		if (item == null) {  throw new java.lang.IllegalArgumentException("IllegalArgumentException");  }
		Node temp = first;
		first = new Node();
		first.item = item;
		first.next = temp;
		++qsize;
	}
	public Item dequeue() {                    
    // remove and return a random item
		if(first == null) {  throw new java.util.NoSuchElementException("NoSuchElementException");  }
		int loc = StdRandom.uniform(0,qsize);
		if (loc == 0) {
			Item item = first.item;
			first = first.next;
			--qsize;
			return item;
		}
		Node temp = first;
		for (int i=0; i<loc-1; ++i) {
			temp = temp.next;
		}
		Item item = temp.next.item;
		temp.next = temp.next.next;
		--qsize;
		return item;
	}
	public Item sample() {                    
    // return a random item (but do not remove it)
		if(first == null) {  throw new java.util.NoSuchElementException("NoSuchElementException");  }
		int loc = StdRandom.uniform(0,qsize);
		Node temp = first;
		for (int i=0; i<loc; ++i) {
			temp = temp.next;
		}
		return temp.item;
	}
	public Iterator<Item> iterator() {         
    // return an independent iterator over items in random order
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>
	{
		private int location[] = StdRandom.permutation(qsize);
		private int current = 0;
		public void remove() {  throw new java.lang.UnsupportedOperationException("UnsupportedOperationException");  }
		public boolean hasNext() {  return current != qsize;  }
		public Item next() {
			if(current == qsize) {  throw new java.util.NoSuchElementException("NoSuchElementException");  }
			Node temp = first;
			for (int i=0; i<qsize; ++i) {
				if (i == location[current]) break;
				temp = temp.next;
			}
			++current;
			return temp.item;
		}   	
	}
	// public void all() {                    
	// 	Node temp = first;
	// 	for (int i=0; i<qsize; ++i) {
	// 		System.out.print(temp.item);
	// 		temp = temp.next;
	// 	}
	// 	System.out.print('\n');
	// }
	public static void main(String[] args) {  
    // unit testing (optional)
		
	}
}