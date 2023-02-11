package stackQueue;

import java.util.Arrays;
import java.util.Stack;

/**
 * Implements stack of Strings
 *
 * @author Marcus
 */
public class ArrayStack {
    private String[] items;
    private int n; // number of elements in stack

    public ArrayStack(int capacity) {
        items = new String[capacity];
    }

    /**
     * Adds item on top of the stack.
     * @param item element to be added on top of the stack.
     */
    public void push(String item) {
        if(n == items.length)
            throw new UnsupportedOperationException("Cant add " + item + "to a full stack.\n");

        items[n++] = item;
    }

    /**
     * Removes the element on top of the stack and returns it.
     * @return element that has been removed.
     */
    public String pop() {
        if(n == 0)
            throw new UnsupportedOperationException("Can't remove at the top element on an empty stack.");

        String result = items[n-1];
        items[--n] = null;
        return result;
    }

    /**
     * Identifies the element on top of the stack and returns it.
     * @return element that will be removed next.
     */
    public String peek() {
        if(n == 0)
            throw new UnsupportedOperationException("Can't peek at the top element on an empty stack.");

        return items[n-1];
    }

    /**
     * @return the number of elements of the stack.
     */
    public int size() {
        return n;
    }

    /**
     * Checks if the ArrayStack is empty.
     * @return true if empty, false if otherwise.
     */
    public boolean isEmpty() {
        return n==0;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "items=" + Arrays.toString(items) +
                ", n=" + n +
                '}';
    }

    //    = = = Test Client = = =
    public static void main(String[] args) {
        ArrayStack stack = new ArrayStack(6);
        System.out.println(stack);
//        System.out.println("Element Popped: " + stack.pop()); // -> EXPECTED EXCEPTION

        stack.push("ape");
        System.out.println(stack);
        stack.push("dog");
        System.out.println(stack);

        System.out.println("Popped element " + stack.pop());
        System.out.println(stack);

        stack.push("cat");
        System.out.println(stack);

        System.out.println(stack.peek());
        System.out.println("Popped element " + stack.pop());


        for (int i = 0; i < stack.size(); i++) {
            stack.pop();
        }

        System.out.println(stack);
//        System.out.println(stack.peek()); // -> EXPECTED EXCEPTION

    }
}
