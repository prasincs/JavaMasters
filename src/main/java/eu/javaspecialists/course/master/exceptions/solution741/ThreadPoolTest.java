package eu.javaspecialists.course.master.exceptions.solution741;

import junit.framework.*;

import java.util.concurrent.*;

public class ThreadPoolTest extends TestCase {
    public void testJobs() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(19);
        long time = System.currentTimeMillis();
        ThreadPool pool = new ThreadPool(10);
        for (int i = 0; i < 19; i++) {
            pool.submit(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        latch.countDown();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        latch.await();
        time = System.currentTimeMillis() - time;
        assertEquals(0, pool.getRunQueueLength());
        assertTrue(time < 2400);
        pool.shutdown();
    }

    public void testConstructorArguments() {
        try {
            new ThreadPool(-1);
            fail();
        } catch (IllegalArgumentException success) {
        }
        try {
            new ThreadPool(Integer.MIN_VALUE);
            fail();
        } catch (IllegalArgumentException success) {
        }
        try {
            new ThreadPool(0);
            fail();
        } catch (IllegalArgumentException success) {
        }
        new ThreadPool(ThreadPool.MAXIMUM_POOL_SIZE);
        try {
            new ThreadPool(ThreadPool.MAXIMUM_POOL_SIZE + 1);
            fail();
        } catch (IllegalArgumentException success) {
        }
        try {
            new ThreadPool(Integer.MAX_VALUE);
            fail();
        } catch (IllegalArgumentException success) {
        }
    }

    public void testSubmitNull() {
        ThreadPool pool = new ThreadPool(10);
        try {
            pool.submit(null);
            fail();
        } catch (IllegalArgumentException success) {
        } catch (NullPointerException success) {
            assertEquals("Please throw NPE in thread pool",
                    ThreadPool.class.getName(),
                    success.getStackTrace()[0].getClassName());
        }
    }
}
