package stackQueue;

import java.util.Arrays;
import java.util.Iterator;

/**
 * Implements stack of Strings
 *
 * @author Marcus
 */
public class ArrayStack<E> implements Iterable<E> {
    private E[] items;
    private int n; // number of elements in stack

    public ArrayStack(int capacity) {
        items = (E[]) new Object[capacity];
    }

    /**
     * Adds item on top of the stack.
     *
     * @param item element to be added on top of the stack.
     */
    public void push(E item) {
        if (n == items.length)
            throw new UnsupportedOperationException("Cant add " + item + "to a full stack.\n");

        items[n++] = item;
    }

    /**
     * Removes the element on top of the stack and returns it.
     *
     * @return element that has been removed.
     */
    public E pop() {
        if (n == 0)
            throw new UnsupportedOperationException("Can't remove at the top element on an empty stack.");

        E result = items[n - 1];
        items[--n] = null;
        return result;
    }

    /**
     * Identifies the element on top of the stack and returns it.
     *
     * @return element that will be removed next.
     */
    public E peek() {
        if (n == 0)
            throw new UnsupportedOperationException("Can't peek at the top element on an empty stack.");

        return items[n - 1];
    }

    /**
     * @return the number of elements of the stack.
     */
    public int size() {
        return n;
    }

    /**
     * Checks if the ArrayStack is empty.
     *
     * @return true if empty, false if otherwise.
     */
    public boolean isEmpty() {
        return n == 0;
    }

    @Override
    public String toString() {
        return "Stack{" +
                "items=" + Arrays.toString(items) +
                ", n=" + n +
                '}';
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayStackIterator();
    }

    private class ArrayStackIterator implements Iterator<E> {

        private int index = n--;

        @Override
        public boolean hasNext() {
            if ((index - 1) < 0) {
                return false;
            }   else {
                return items[--index] != null;
            }
//            return (!(index-- < 0) || items[--index] != null);
        }

        @Override
        public E next() {
            --index;
            return items[++index];
        }
    }

    //    = = = Test Client = = =
    public static void main(String[] args) {
//        **********************
//        STRING STACK
//        **********************
//        ArrayStack<String> stringStack = new ArrayStack<>(6);
//        System.out.println(stringStack);
//
//        stringStack.push("ape");
//        System.out.println(stringStack);
//        stringStack.push("dog");
//        System.out.println(stringStack);
//
//        System.out.println("Popped element " + stringStack.pop());
//        System.out.println(stringStack);
//
//        stringStack.push("cat");
//        System.out.println(stringStack);
//
//        System.out.println(stringStack.peek());
//        System.out.println("Popped element " + stringStack.pop());
//
//
//        for (int i = 0; i < stringStack.size(); i++) {
//            stringStack.pop();
//        }
//
//        System.out.println(stringStack);
//        System.out.println();

//        **********************
//        INTEGER STACK
//        **********************

//        ArrayStack<Integer> intStack = new ArrayStack<>(6);
//        System.out.println(intStack);
//
//        intStack.push(1);
//        System.out.println(intStack);
//        intStack.push(2);
//        System.out.println(intStack);
//
//        System.out.println("Popped element " + intStack.pop());
//        System.out.println(intStack);
//
//        intStack.push(3);
//        System.out.println(intStack);
//
//        System.out.println(intStack.peek());
//        System.out.println("Popped element " + intStack.pop());
//
//
//        for (int i = 0; i < intStack.size(); i++) {
//            intStack.pop();
//        }
//
//        System.out.println(intStack);

        ArrayStack<String> stringStack = new ArrayStack<>(4);
        ArrayStack<Integer> integerStack = new ArrayStack<>(4);

        stringStack.push("ape");
        stringStack.push("cat");
        stringStack.push("dog");
        stringStack.push("dove");

        stringStack.pop();

        integerStack.push(11);
        integerStack.push(22);
        integerStack.push(33);
        integerStack.push(44);

        Iterator<String> stringIterator = stringStack.iterator();
        Iterator<Integer> integerIterator = integerStack.iterator();

//        for (String element:
//             stringIterator) {
//            String element = it.next();
//            System.out.println(element + " ");
//        }

        for (Iterator<String> it = stringIterator; it.hasNext(); ) {
            String element = it.next();
            System.out.println(element + " ");
        }

        System.out.println();

        for (Iterator<Integer> it = integerIterator; it.hasNext(); ) {
            int element = it.next();
            System.out.println(element + " ");
        }

    }
}
