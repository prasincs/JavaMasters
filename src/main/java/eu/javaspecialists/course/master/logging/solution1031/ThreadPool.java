package eu.javaspecialists.course.master.logging.solution1031;

import java.util.concurrent.*;
import java.util.logging.*;

// Using exceptions ...
public class ThreadPool {
    private final ThreadGroup group = new ThreadGroup("thread pool");
    private final BlockingQueue<Runnable> runQueue = new LinkedBlockingQueue<Runnable>();
    public static final int MAXIMUM_POOL_SIZE = 1024;
    private static final Logger log = Logger
            .getLogger(ThreadPool.class.getName());
    private volatile boolean running = true;

    /**
     * @param poolSize the pool size which should be between 1 and
     *                 the maximum allowed
     * @throws IllegalArgumentException when the pool size is less
     *                                  than 1 or larger than the
     *                                  maximum allowed
     * @see #MAXIMUM_POOL_SIZE
     */
    public ThreadPool(int poolSize) {
        if (poolSize < 1 || poolSize > MAXIMUM_POOL_SIZE) {
            throw new IllegalArgumentException("Pool size: " + poolSize + ", "
                    + "must be between 0 and " + MAXIMUM_POOL_SIZE);
        }
        for (int i = 0; i < poolSize; i++) {
            ThreadPool.Worker worker = new ThreadPool.Worker(group, "Worker" + i);
            worker.setDaemon(false);
            worker.start();
            log.fine("Worker thread " + worker + " started");
        }
        log.info("ThreadPool with " + poolSize + " constructed");
    }

    /**
     * @param job
     * @throws IllegalArgumentException if job is null
     */
    public void submit(Runnable job) {
        if (job == null)
            throw new IllegalArgumentException("job may not be null");
        log.fine("Submitting job to work queue: " + job);
        runQueue.add(job);
    }

    private Runnable take() throws InterruptedException {
        long time = System.currentTimeMillis();
        Runnable job = runQueue.take();
        time = System.currentTimeMillis() - time;
        assert job != null;
        log.fine("Job retrieved from queue: " + job + ", " + "waited for " + time
                + "ms for a job to arrive in queue");
        return job;
    }

    public int getRunQueueLength() {
        return runQueue.size();
    }

    public void shutdown() {
        log.finer("ThreadPool worker threads being interrupted");
        running = false;
        group.interrupt();
        log.info("ThreadPool shut down");
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
            log.fine("Worker " + this + " started");
        }

        public void run() {
            while (running && !isInterrupted()) {
                try {
                    log.finer("Worker " + this + " waiting for task");
                    Runnable job = take();
                    log.finer("Worker " + this + " starting job " + job);
                    long time = System.currentTimeMillis();
                    job.run();
                    time = System.currentTimeMillis() - time;
                    log.finer("Worker " + this + " finished job " + job + " " + "in "
                            + time + "ms");
                } catch (InterruptedException e) {
                    interrupt();
                    break;
                }
            }
            assert !running || isInterrupted();
            log.fine("Worker " + this + " shut down");
        }
    }
}
