package stackQueue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;

public class LinkedQueue {
    private int n; // number of elements in queue
    private Node head; // first element or null
    private Node tail; // lest element or null

    private class Node {
        private String data;
        private Node next;

        public Node(String data) {
            this.data = data;
        }
    }

    public int size() {
        return n;
    }

    public boolean isEmpty() {
        return n == 0;
    }

    public void enqueue(String element) {
        Node newNode = new Node(element);
        if (isEmpty())
            head = newNode;
        else
            tail.next = newNode;
        tail = newNode;

        n++;

    }

    public String dequeue() {
        if (isEmpty())
            throw new NoSuchElementException("Can't remove element that doesn't exist in the queue.");

        String result = head.data;
        head = head.next;
        n--;

        if(isEmpty()) {
            tail = null;
        }

        return result;
    }

    public String peek() {
        if (isEmpty())
            throw new NoSuchElementException("Can't look up element that doesn't exist in the queue.");
        else
            return head.data;
    }

    /**
     * Returns the first k elements in the list.
     * If k is greater than the number of elements in the list
     * an InvalidArgumentException is thrown.
     * if k is zero, an empty array of length 0 is returned.
     *
     * @param k number of elements to return
     * @return first <code>k</code> elements of list
     */
    public String[] take(int k) {
        if (k > n)
            throw new IllegalArgumentException("Can't take more elements that are available.");
        if (k == 0)
            return new String[0];

        String[] out = new String[k];
        Node current = head;
        int index = 0;
        while (current != null) {
            out[index++] = current.data;
            if (index == out.length) break;
            current = current.next;
        }

        return out;
    }

    /**
     * Creates a string that includes all elements of the list
     *  <p></p>
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        Node current = head;
        while (current != null) {
            assert false;
            out.append(current.data).append(" ");
            current = current.next;
        }
        return out.toString();
    }

//    = = = = TEST CLIENT = = = =

    public static void main(String[] args) {
        ArrayList<String> l = new ArrayList<>();
        LinkedQueue queue = new LinkedQueue();
        System.out.println("Size: " + queue.size());
        System.out.println("isEmpty: " + queue.isEmpty());

        System.out.println("[" + queue + "]");

        queue.enqueue("element");
        System.out.println("[" + queue + "]");
        queue.enqueue("element2");
        System.out.println("[" + queue + "]");
        queue.enqueue("element3");
        System.out.println("[" + queue + "]");
        queue.enqueue("element4");
        System.out.println("[" + queue + "]");
        queue.enqueue("element5");
        System.out.println("[" + queue + "]");
        System.out.println();

        for (int i = 1; i < 4; i++) {
            System.out.println("take " + i + ": " + Arrays.toString(queue.take(i)));
        }

        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());

    }
}
