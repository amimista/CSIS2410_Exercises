package playground;

import java.util.LinkedList;

public class Test {
    public static void main(String[] args) {
        LinkedList<String> ll = new LinkedList<>();
        for(int i = 0; i<10; i++) {
            ll.add("element"+i);
        }
        System.out.println(ll.getFirst());
        System.out.println("Done");
    }
}
