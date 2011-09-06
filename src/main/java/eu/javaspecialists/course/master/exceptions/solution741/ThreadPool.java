package eu.javaspecialists.course.master.exceptions.solution741;

import java.util.concurrent.*;

// Using exceptions ...
public class ThreadPool {
    private final ThreadGroup group = new ThreadGroup("thread pool");
    private final BlockingQueue<Runnable> runQueue = new LinkedBlockingQueue<Runnable>();
    public static final int MAXIMUM_POOL_SIZE = 1024;
    private volatile boolean running = true;

    /**
     * @param poolSize the pool size which should be between 1 and
     *                 the maximum allowed
     * @throws OutOfRangeException when the pool size is less than
     *                             1 or larger than the maximum
     *                             allowed
     * @see #MAXIMUM_POOL_SIZE
     */
    public ThreadPool(int poolSize) {
        if (poolSize < 1 || poolSize > MAXIMUM_POOL_SIZE) {
            throw new OutOfRangeException(poolSize, 1, MAXIMUM_POOL_SIZE);
        }
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(group, "Worker" + i);
            worker.setDaemon(false);
            worker.start();
        }
    }

    /**
     * @param job
     * @throws IllegalArgumentException if job is null
     */
    public void submit(Runnable job) {
        if (job == null)
            throw new NullPointerException("job may not be null");
        assert runQueue != null;
        boolean added = runQueue.add(job);
        assert added : "Could not modify the queue";
    }

    private Runnable take() throws InterruptedException {
        assert runQueue != null;
        Runnable job = runQueue.take();
        assert job != null;
        return job;
    }

    public int getRunQueueLength() {
        assert runQueue != null;
        return runQueue.size();
    }

    public void shutdown() {
        assert group != null;
        running = false;
        group.interrupt();
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
            assert group != null;
            assert name != null;
        }

        public void run() {
            assert !isDaemon();
            while (running && !isInterrupted()) {
                try {
                    take().run();
                } catch (InterruptedException e) {
                    interrupt();
                    break;
                }
            }
            assert !running || isInterrupted();
        }
    }
}
