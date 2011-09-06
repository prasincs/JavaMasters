package eu.javaspecialists.course.master.datastructs.exercise621;

import java.util.*;
import java.util.concurrent.*;

public class ListPerformanceTest {
    private final static boolean COPY_ON_WRITE = true;

    public static void testList(CopyOnWriteArrayList<String> list) {
        for (String s : list) {
        }
    }

    public static void testList(ArrayList<String> list) {
        synchronized (list) {
            for (String s : list) {
            }
        }
    }

    public static void main(String... args) throws InterruptedException {
        // create an initial collection with 100 values

        // use your thread pool to create 10 threads

        // test either the copy on write list or the array list, iterating
        // 1000000 times in a thread safe manner

        // Once all of the threads are finished, measure the time it took

        // Shut down the pool
    }
}
