import java.util.Iterator;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class Deque<Item> implements Iterable<Item> {
	private Node first = null;
	private Node last = null;
	private int qsize = 0;
	private class Node {
		Item item;
		Node next;
		Node prev;
	}

	public Deque() {
    // construct an empty deque

	}                           
	public boolean isEmpty() {
    // is the deque empty?
		return qsize == 0;
	}                
	public int size() {
    // return the number of items on the deque
		return qsize;
	}                       
	public void addFirst(Item item) {
    // add the item to the front
		if (item == null) {  throw new java.lang.IllegalArgumentException("IllegalArgumentException");  }
		Node temp = first;
		first = new Node();
		first.item = item;
		first.next = temp;
		if(temp != null) temp.prev = first;
		if(qsize == 0) last = first;
		++qsize;
	}         
	public void addLast(Item item) {
    // add the item to the end
		if (item == null) {  throw new java.lang.IllegalArgumentException("IllegalArgumentException");  }
		Node temp = last;
		last = new Node();
		last.item = item;
		last.prev = temp;
		if(temp != null) temp.next = last;
		if(qsize == 0) first = last;
		++qsize;
	}          
	public Item removeFirst() {
    // remove and return the item from the front
		if(first == null) {  throw new java.util.NoSuchElementException("NoSuchElementException");  }
		Item item = first.item;
		first = first.next;
		--qsize;
		if(qsize == 0) last = null;
		return item;
	}               
	public Item removeLast() {
    // remove and return the item from the end
		if(last == null) {  throw new java.util.NoSuchElementException("NoSuchElementException");  }
		Item item = last.item;
		last = last.prev;
		--qsize;
		if(qsize == 0) first = null;
		return item;
	}                
	public Iterator<Item> iterator() {
    // return an iterator over items in order from front to end
		return new ListIterator();
	}
	private class ListIterator implements Iterator<Item>
	{
		private Node current = first;
		private int count = qsize;
		public void remove() {  throw new java.lang.UnsupportedOperationException("UnsupportedOperationException");  }
		public boolean hasNext() {  return count != 0;  }
		public Item next() {
			if(count == 0) {  throw new java.util.NoSuchElementException("NoSuchElementException");  }
			Item item = current.item;
			current = current.next;
			--count;
			return item;
		}   
	}
	// public void all() {                    
 //    // return a random item (but do not remove it)
	// 	Node temp = first;
	// 	for (int i=0; i<qsize; ++i) {
	// 		System.out.print(temp.item);
	// 		temp = temp.next;
	// 	}
	// 	System.out.print('\n');
	// 	temp = last;
	// 	for (int i=qsize; i>0; --i) {
	// 		System.out.print(temp.item);
	// 		temp = temp.prev;
	// 	}
	// 	System.out.print('\n');
	// }     
	public static void main(String[] args) {
    // unit testing (optional)
		// Deque<Integer> deque = new Deque<Integer>();
		// System.out.println(deque.size());
		// System.out.println(deque.isEmpty());
		// deque.addFirst(1);
		// deque.addLast(2);
		// deque.addFirst(3);
		// deque.addFirst(4);
		// deque.addLast(5);		
		// deque.all();
		// System.out.println(deque.size());
		// System.out.println(deque.isEmpty());
		// System.out.println(deque.removeFirst());
		// deque.all();
		// System.out.println(deque.size());
		// System.out.println(deque.isEmpty());
		// System.out.println(deque.removeFirst());
		// deque.all();
		// System.out.println(deque.size());
		// System.out.println(deque.isEmpty());
		// System.out.println(deque.removeLast());
		// deque.all();
		// System.out.println(deque.size());
		// System.out.println(deque.isEmpty());
		// System.out.println(deque.removeLast());
		// deque.all();
		// System.out.println(deque.size());
		// System.out.println(deque.isEmpty());
		// System.out.println(deque.removeLast());
		// deque.all();
		// System.out.println(deque.size());
		// System.out.println(deque.isEmpty());
		// {
		// Iterator<Integer> iter = deque.iterator();
		// while (iter.hasNext()) System.out.print(iter.next());
		// System.out.println("---");}

		// deque.addFirst(1);
		// deque.addLast(2);
		// deque.addFirst(3);
		// deque.addFirst(4);
		// deque.addLast(5);
		// System.out.println(deque.size());
		// {
		// Iterator<Integer> iter = deque.iterator();
		// while (iter.hasNext()) System.out.print(iter.next());
		// System.out.println("---");}

		// deque.all();
		// System.out.println(deque.size());
		// System.out.println(deque.isEmpty());
		// System.out.println(deque.removeFirst());
		// deque.all();
		// System.out.println(deque.size());
		// System.out.println(deque.isEmpty());
		// System.out.println(deque.removeFirst());
		// deque.all();
		// System.out.println(deque.size());
		// System.out.println(deque.isEmpty());
		// System.out.println(deque.removeLast());
		// deque.all();
		// System.out.println(deque.size());
		// System.out.println(deque.isEmpty());
		// System.out.println(deque.removeLast());
		// deque.all();
		// System.out.println(deque.size());
		// System.out.println(deque.isEmpty());
		// System.out.println(deque.removeLast());
		// deque.all();
		// System.out.println(deque.size());
		// System.out.println(deque.isEmpty());
	}  
}