package eu.javaspecialists.course.master.datastructs.exercise611;

public class BubbleSort {
    public static <T extends Comparable<? super T>> void sort(T[] x) {
        for (int pass = 1; pass < x.length; pass++) {
            for (int i = 0; i < x.length - pass; i++) {
                if (x[i].compareTo(x[i + 1]) > 0) {
                    // exchange elements
                    T temp = x[i];
                    x[i] = x[i + 1];
                    x[i + 1] = temp;
                }
            }
        }
    }
}
