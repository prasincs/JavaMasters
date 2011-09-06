package eu.javaspecialists.course.master.threads.solution223;

import eu.javaspecialists.course.master.threads.exercise223.*;

public class CASCounterTest {
    public static void main(String[] args) throws InterruptedException {
        final CASCounter cas = new CASCounter();
        Thread[] threads = new Thread[3];
        for (int i = 0; i < threads.length; i++) {
            threads[i] = new Thread() {
                public void run() {
                    for (int i = 0; i < 1000 * 1000; i++) {
                        cas.increment();
                    }
                }
            };
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
        System.out.println("cas.getCount() = " + cas.getCount());
    }
}
