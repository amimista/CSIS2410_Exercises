package midtermPrep;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Simple list that stores characters of type Character.
 * This list is implement with an array.
 *
 * @author Starter Code + .............
 */
public class CharList {
    private Character[] items;
    private int n;

    public CharList(int capacity) {
        items = new Character[capacity];
        n = 0;
    }

    /**
     * Adds the element <code>item</code> to the end of the list.
     *
     * @param item element to add to the list
     * @throws UnsupportedOperationException if list reached its capacity
     */
    public void add(Character item) {
        if (n == items.length)
            throw new UnsupportedOperationException("Can't add an item to a full list.");

        items[n] = item;
        n++;
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
     * Returns all characters that are digits (0 - 9) in the order in which they were added to the list.
     *
     * @return all digits
     */
    public Iterable<Character> digits() {
        if (isEmpty())
            throw new IllegalCallerException("Can't read an empty linked list.");

        ArrayList<Character> out = new ArrayList<>();

        for (Character element:
                items) {
            if (Character.isDigit(element)) {
                out.add(element);
            }
        }

        return out;
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
     * <p>Builds a string that includes all list elements followed by a single space.
     * </p>
     * <p>
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(items[i]).append(" ");
        }
        return sb.substring(0, sb.length() - 1);
    }
// = = = =   T e s t   C l i e n t    = = = = =

    public static void main(String[] args) {
        CharList list = new CharList(9); // was 6
        list.add('2');
        list.add('a');
        list.add('B');
        list.add('c');
        list.add('1');
        list.add('D');
        list.add('3');
        list.add('4');
        list.add('e');
        System.out.println("list: " + list);
        System.out.println(list.digits());

    }


}
