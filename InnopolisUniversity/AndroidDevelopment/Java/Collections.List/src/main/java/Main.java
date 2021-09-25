import collections.MArrayList;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        Collections.addAll(arrayList, 1, 2, 3, 4, 5, 6);

        LinkedList<Integer> linkedList = new LinkedList<>(Arrays.asList(1, 2, 3, 4, 5, 6));

        MArrayList<Integer> mArrayList = new MArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));


        System.out.println("ArrayList: ");
        processList(arrayList);
        System.out.println("---------------------------");

        System.out.println("LinkedList: ");
        processList(linkedList);
        System.out.println("---------------------------");

        System.out.println("My implementation of ArrayList: ");
        processList(mArrayList);
        System.out.println("---------------------------");
    }

    private static void processList(List<Integer> list) {
        list.forEach(System.out::println);
        System.out.println("Index of 1: " + list.indexOf(1));
        System.out.println("Element 3 removed...");
        list.remove(Integer.valueOf(3));
        System.out.println(list);
    }

}
