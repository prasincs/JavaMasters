package eu.javaspecialists.course.master.datastructs.exercise611;

import java.util.*;

public class StringBubbleSortTest {
    public static void main(String[] args) {
        String[] names = {"Heinz", "John", "Anton", "Zach", "Richard"};
        BubbleSort.sort(names);
        System.out.println(Arrays.toString(names));
    }
}
