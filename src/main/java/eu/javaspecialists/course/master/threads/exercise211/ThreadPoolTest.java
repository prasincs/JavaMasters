package eu.javaspecialists.course.master.threads.exercise211;

import junit.framework.*;

import java.util.concurrent.*;
import java.util.concurrent.locks.*;
import java.lang.reflect.*;

public class ThreadPoolTest extends TestCase {
    public void testThatRunnablesAreExecutedConcurrently() throws InterruptedException {
        checkStandardThreadPoolFunctionality(new ThreadPool(10));
    }

    public void testSpuriousWakeupsAreHandledCorrectly() throws InterruptedException, IllegalAccessException {
        boolean foundLockField = false;
        boolean foundBlockingQueueField = false;
        boolean foundConditionField = false;
        for (Field field : ThreadPool.class.getDeclaredFields()) {
            if (Lock.class.isAssignableFrom(field.getType())) {
                foundLockField = true;
            } else if (BlockingQueue.class.isAssignableFrom(field.getType())) {
                foundBlockingQueueField = true;
            } else if (Condition.class.isAssignableFrom(field.getType())) {
                foundConditionField = true;
            }
        }
        if (foundBlockingQueueField && !foundLockField) return;
        if (foundBlockingQueueField && foundLockField)
            fail("We don't need a lock if we use a BlockingQueue");
        if (!foundLockField)
            fail("We need a Lock field");
        if (!foundConditionField)
            fail("We need a field of type Condition");

        ThreadPool pool = new ThreadPool(10);
        Thread.sleep(100);
        Lock lock = (Lock) findFieldValue(pool, Lock.class);
        Condition condition = (Condition) findFieldValue(pool, Condition.class);
        for (int i = 0; i < 20; i++) {
            lock.lock();
            try {
                condition.signalAll();
            } finally {
                lock.unlock();
            }
        }
        checkStandardThreadPoolFunctionality(pool);
    }

    private void checkStandardThreadPoolFunctionality(ThreadPool pool) throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(19);
        long time = System.currentTimeMillis();
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
        boolean noTimeout = latch.await(3, TimeUnit.SECONDS);
        assertTrue("timeout occurred - did you start your threads?", noTimeout);
        time = System.currentTimeMillis() - time;
        pool.shutdown();
        if (pool.getRunQueueLength() != 0) {
            throw new AssertionError("Queue was not empty: "
                    + pool.getRunQueueLength());
        }
        assertTrue("Total time exceeded limits", time < 2400);
        assertFalse("Faster than expected", time < 1900);
    }

    private Object findFieldValue(ThreadPool pool, Class<?> fieldType) throws IllegalAccessException {
        for (Field field : pool.getClass().getDeclaredFields()) {
            if (fieldType.isAssignableFrom(field.getType())) {
                field.setAccessible(true);
                return field.get(pool);
            }
        }
        throw new IllegalArgumentException("Field of type " + fieldType + " not found");
    }

    private Thread interrupted = null;

    public void testForBackupBoolean() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(8);
        ThreadPool pool = new ThreadPool(10);
        for (int i = 0; i < 12; i++) {
            pool.submit(new Runnable() {
                public void run() {
                    try {
                        Thread.sleep(1000);
                        latch.countDown();
                    } catch (InterruptedException e) {
                        interrupted = Thread.currentThread();
                    }
                }
            });
        }
        boolean noTimeout = latch.await(2, TimeUnit.SECONDS);
        assertTrue("timeout occurred - did you start your threads?", noTimeout);
        pool.shutdown();
        Thread.sleep(100);
        assertTrue("Did you have a backup boolean?",
                interrupted == null || !interrupted.isAlive());
    }
}