package eu.javaspecialists.course.master.logging.solution1011;

import eu.javaspecialists.course.master.util.*;

import java.util.concurrent.*;
import java.util.logging.*;

public class ThreadPool {
    private final ThreadGroup group = new ThreadGroup("thread pool");
    private final BlockingQueue<Job> runQueue = new LinkedBlockingQueue<Job>();
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
            log.info("Worker thread " + worker + " created");
        }
        log.info("ThreadPool with size " + poolSize + " constructed");
    }

    /**
     * @param job
     * @throws IllegalArgumentException if job is null
     */
    public void submit(Runnable job) {
        if (job == null) {
            throw new IllegalArgumentException("job may not be null");
        }
        if (!running) {
            throw new IllegalStateException("pool shut down - cannot accept new jobs");
        }
        log.info("Submitting job to work queue: " + job);
        runQueue.add(new Job(job));
    }

    private Runnable take() throws InterruptedException {
        Benchmark bm = null;
        if (log.isLoggable(Level.INFO)) {
            bm = new Benchmark();
            bm.start();
        }
        Job job = runQueue.take();
        assert job != null;
        if (log.isLoggable(Level.INFO)) {
            log.info(job + " was in the queue for " + job.getNumberOfSecondsWaiting()
                    + "ms");
            if (bm == null) {
                log.info("Job retrieved from queue: " + job + ", do not know how long we waited");
            } else {
                bm.stop();
                log.info("Job retrieved from queue: " + job + ", " + "waited for " + bm
                        + " for a job to arrive in queue");
            }
        }
        return job.getRunnable();
    }

    public int getRunQueueLength() {
        return runQueue.size();
    }

    public void shutdown() {
        running = false;
        log.info("ThreadPool worker threads being interrupted");
        group.interrupt();
        log.info("ThreadPool shut down");
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
        }

        public void run() {
            log.info("Worker " + this + " started");
            while (running && !isInterrupted()) {
                try {
                    log.info("Worker " + this + " waiting for task");
                    Runnable job = take();
                    log.info("Worker " + this + " starting job " + job);
                    Benchmark bm = new Benchmark();
                    bm.start();
                    job.run();
                    bm.stop();
                    log.info("Worker " + this + " finished job " + job + " in "
                            + bm);
                } catch (InterruptedException e) {
                    interrupt();
                    break;
                }
            }
            assert !running || isInterrupted();
            log.info("Worker " + this + " shut down");
        }
    }

    private static class Job {
        private final long submitTime = System.currentTimeMillis();
        private final Runnable runnable;

        public Job(Runnable runnable) {
            this.runnable = runnable;
        }

        public long getNumberOfSecondsWaiting() {
            return System.currentTimeMillis() - submitTime;
        }

        public Runnable getRunnable() {
            return runnable;
        }

        public String toString() {
            return "Job: " + runnable;
        }
    }
}