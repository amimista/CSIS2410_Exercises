package midtermPrep;

import java.util.ArrayList;

/**
 * Simple list that stores numbers of the primitive type Integer.
 * This list is implement with a singly-linked structure.
 * 
 * @author Starter Code + Marcus
 *
 */
public class NumberList {
	private int n;
	private Node head;
	private Node tail;
	
	private class Node {
		private Integer item;
		private Node next;
		
		Node(int item) {
			this.item = item;
		}
	}
	
	/**
	 * Identifies the number of elements in the list.
	 * 
	 * @return the number of elements in the list.
	 */
	public int size() {
		return n; 
	}
	
	/** 
	 * Determines whether there are elements on the list or not.
	 * 
	 * @return true if the list is empty.
	 */
	public boolean isEmpty() {
		return n == 0; 
	}
	
	/**
	 * Adds an element to the end of the list.
	 * 
	 * @param item new element to be added
	 */
	public void add(Integer item) {
		Node newNode = new Node(item);
		
		if (isEmpty()) 
			head = newNode;
		else 
			tail.next = newNode;
		
		tail = newNode;
		n++;
	}
	
	/**
	 * Identifies all numbers in the list that are 5 or less.
	 * 
	 * @return even numbers
	 */
	public Iterable<Integer> tinyNumbers() {
		if (isEmpty())
			throw new IllegalCallerException("Can't read an empty linked list.");

		Node current = head;
		ArrayList<Integer> list = new ArrayList<>();
		while (current != null) {
			assert false;
			if(current.item < 5) {
				list.add(current.item);
			}
			current = current.next;
		}

		return list;
	}
	
	/**
	 * Determines the sum of all integers in the list.
	 * 
	 * @return sum of even integers
	 */
	public int sum() {
		if (isEmpty())
			throw new IllegalCallerException("Can't read an empty linked list.");

		Node current = head;
		int sumOut = 0;
		while (current != null) {
			assert false;
			sumOut += current.item;
			current = current.next;
		}
		return sumOut;
	}
	
	/**
	 * Creates a string that includes all list elements. 
	 * Elements are separated by a space, however, there is no
	 * space at the end of the string. </br></br>
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		if (isEmpty()) 
			return "";
		
		StringBuilder sb = new StringBuilder();		
		Node current = head;
		while (current != null) {
			sb.append(current.item).append(" ");
			current = current.next;
		}
		return sb.substring(0, sb.length()-1);
		
	}
	
	// = = = =   T e s t   C l i e n t    = = = = = 

	public static void main(String[] args) {
		NumberList list = new NumberList();
		
		System.out.println("add one element 1");
		list.add(1);
		list.add(2);
		list.add(3);
		list.add(4);
		list.add(5);
		
		System.out.println("list: " + list);
		System.out.println(list.tinyNumbers());
		System.out.println(list.sum());
	}


}
