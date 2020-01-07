package Playground;

import Data_Structures.LinkedList;

public class Playground {
    public static void main(String[] args) {
        java.util.LinkedList<Integer> javall = new java.util.LinkedList<>();
        LinkedList<Integer> ll = new LinkedList<>();

        javall.add(1);
        javall.add(4);

//        System.out.println(javall.remove(1));
//        System.out.println(javall.getFirst());

//        javall.

//        javall.add(6, 7);

//        System.out.println(javall.get(0));

        ll.add(4);
        ll.add(7);
        ll.add(18);
        System.out.println(ll.size());
        System.out.println(ll.remove(2));
        System.out.println(ll.get(2));
        System.out.println(ll.size());

//

//        System.out.println(ll.get(1));
//        System.out.println(ll.get(2));
//        System.out.println(ll.get(3));
//
//        System.out.println(ll.size());
    }
}
