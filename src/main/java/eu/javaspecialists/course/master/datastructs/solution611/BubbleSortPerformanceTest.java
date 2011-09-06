package eu.javaspecialists.course.master.datastructs.solution611;

import eu.javaspecialists.course.master.datastructs.exercise611.*;
import eu.javaspecialists.course.master.util.*;

import java.util.*;

public class BubbleSortPerformanceTest {
    public static void main(String[] args) {
        testSort(false);
        testSort(false);
        testSort(true);
    }

    private static void testSort(boolean bubble) {
        for (int size = 100; size <= 1000 * 1000; size *= 2) {
            Random random = new Random(0);
            String[] dataset = new String[size];
            for (int i = 0; i < dataset.length; i++) {
                dataset[i] = String.valueOf(random.nextDouble());
            }
            sort(bubble, dataset);
        }
    }

    public static void sort(boolean bubble, String[] dataset) {
        Benchmark mbm = new Benchmark();
        mbm.start();
        if (bubble) {
            BubbleSort.sort(dataset);
        } else {
            Arrays.sort(dataset);
        }
        mbm.stop();
        System.out.printf("%sSort (%d) %s%n", bubble ? "Bubble" : "Merge",
                dataset.length, mbm);
    }
}