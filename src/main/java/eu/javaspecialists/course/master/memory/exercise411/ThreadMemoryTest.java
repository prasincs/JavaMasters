package eu.javaspecialists.course.master.memory.exercise411;

import eu.javaspecialists.course.master.threads.solution231.*;

public class ThreadMemoryTest {
    public static void main(String... args) throws InterruptedException {
        final ThreadPool pool = new ThreadPool(10);
        while (true) {
            pool.submit(new Runnable() {
                byte[] data = new byte[20 * 1024];

                public void run() {
                    try {
                        Thread.sleep(1000);
                        System.out.println(Thread.currentThread());
                        System.out.println(pool.getRunQueueLength());
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            });
            Thread.sleep(10);
        }
    }
}

/*
 * # -XX:+UseSerialGC is "Serial" + "Serial Old" # -XX:+UseParNewGC is "ParNew"
 * + "Serial Old" # -XX:+UseConcMarkSweepGC is "ParNew" + "CMS" + "Serial Old".
 * "CMS" is used most # of the time to collect the tenured generation.
 * "Serial Old" is used when a # concurrent mode failure occurs. #
 * -XX:+UseParallelGC is "Parallel Scavenge" + "Serial Old" #
 * -XX:+UseParallelOldGC is "Parallel Scavenge" + "Parallel Old"
 */