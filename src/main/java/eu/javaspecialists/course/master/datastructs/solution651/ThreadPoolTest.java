package eu.javaspecialists.course.master.datastructs.solution651;

import java.util.concurrent.*;

public class ThreadPoolTest {
    private static final CountDownLatch latch = new CountDownLatch(20);
    private static int nextSequence = 0;

    private static class MyRunnable implements Runnable {
        private final int expectedSequence;

        private MyRunnable(int expectedSequence) {
            this.expectedSequence = expectedSequence;
        }

        public void run() {
            try {
                Thread.sleep(100);
                latch.countDown();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            if (expectedSequence != nextSequence++) {
                throw new IllegalStateException("Job " + expectedSequence
                        + " out of sequence");
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ThreadPool pool = new ThreadPool(1);
        // submitting normal priority jobs
        pool.submit(new MyRunnable(0), ThreadPool.Priority.NORMAL);
        Thread.sleep(50);

        pool.submit(new MyRunnable(6), ThreadPool.Priority.NORMAL);
        pool.submit(new MyRunnable(7), ThreadPool.Priority.NORMAL);
        pool.submit(new MyRunnable(8), ThreadPool.Priority.NORMAL);
        pool.submit(new MyRunnable(9), ThreadPool.Priority.NORMAL);
        pool.submit(new MyRunnable(10), ThreadPool.Priority.NORMAL);

        pool.submit(new MyRunnable(15), ThreadPool.Priority.LOW);
        pool.submit(new MyRunnable(16), ThreadPool.Priority.LOW);
        pool.submit(new MyRunnable(17), ThreadPool.Priority.LOW);
        pool.submit(new MyRunnable(18), ThreadPool.Priority.LOW);

        pool.submit(new MyRunnable(1), ThreadPool.Priority.HIGH);

        pool.submit(new MyRunnable(19), ThreadPool.Priority.LOW);

        pool.submit(new MyRunnable(11), ThreadPool.Priority.NORMAL);
        pool.submit(new MyRunnable(12), ThreadPool.Priority.NORMAL);
        pool.submit(new MyRunnable(13), ThreadPool.Priority.NORMAL);
        pool.submit(new MyRunnable(14), ThreadPool.Priority.NORMAL);

        pool.submit(new MyRunnable(2), ThreadPool.Priority.HIGH);
        pool.submit(new MyRunnable(3), ThreadPool.Priority.HIGH);
        pool.submit(new MyRunnable(4), ThreadPool.Priority.HIGH);
        pool.submit(new MyRunnable(5), ThreadPool.Priority.HIGH);

        latch.await();
        System.out.println("All is well!");
        pool.shutdown();
    }
}