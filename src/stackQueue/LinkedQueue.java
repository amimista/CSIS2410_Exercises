package stackQueue;

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

    public static void main(String[] args) {
        LinkedQueue queue = new LinkedQueue();
        System.out.println("Size: " + queue.size());
        System.out.println("isEmpty: " + queue.isEmpty());

        queue.enqueue("element");
        System.out.println("Size: " + queue.size());

        queue.enqueue("element2");
        System.out.println("Size: " + queue.size());

        queue.enqueue("element3");
        System.out.println("Size: " + queue.size());

        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());
        System.out.println("dequeue: " + queue.dequeue());

    }
}
