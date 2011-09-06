package eu.javaspecialists.course.master.datastructs.solution621;

import eu.javaspecialists.course.master.threads.solution231.*;

import java.util.*;
import java.util.concurrent.*;

public class ListPerformanceTest {
    private static final boolean COPY_ON_WRITE = false;
    private static final int NUM_THREADS = 1;
    private static final int NUM_TESTS = 100;
    private static final int NUM_ELEMENTS = 100;
    private static final int NUM_ITERATIONS = 1000 * 1000 * 1000 / NUM_ELEMENTS
            / NUM_THREADS;

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
        final Collection<String> data = new ArrayList<String>();
        for (int i = 0; i < NUM_ELEMENTS; i++) {
            data.add(String.valueOf(i));
        }
        ThreadPool pool = new ThreadPool(NUM_THREADS);
        for (int i = 0; i < NUM_TESTS; i++) {
            test(pool, data);
        }
        pool.shutdown();
    }

    private static void test(ThreadPool pool, final Collection<String> data)
            throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(NUM_THREADS);
        final CopyOnWriteArrayList<String> cowStrings = new CopyOnWriteArrayList<String>(
                data);
        final ArrayList<String> alStrings = new ArrayList<String>(data);
        long time = System.currentTimeMillis();
        for (int i = 0; i < NUM_THREADS; i++) {
            pool.submit(new Runnable() {
                public void run() {
                    if (COPY_ON_WRITE) {
                        for (int i = 0; i < NUM_ITERATIONS; i++) {
                            testList(cowStrings);
                        }
                    } else {
                        for (int i = 0; i < NUM_ITERATIONS; i++) {
                            testList(alStrings);
                        }
                    }
                    latch.countDown();
                }
            });
        }
        latch.await();
        time = System.currentTimeMillis() - time;
        System.out.println(time);
    }
}