package eu.javaspecialists.course.master.logging.exercise1011;

import eu.javaspecialists.course.master.util.Benchmark;
import sun.rmi.runtime.Log;

import java.util.concurrent.*;
import java.util.logging.*;

// Using logging ...
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
            Worker worker = new Worker(group, "Worker" + i);
            worker.setDaemon(false);
            worker.start();
            log.log(Level.INFO, "worker thread was created "+ "Worker "+ i);

        }
        log.log(Level.INFO, "thread pool was created of size "+ group.activeCount());

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
        log.log(Level.INFO, "Job was submitted "+ job.toString());
        runQueue.add(job);
    }

    private Runnable take() throws InterruptedException {
        assert runQueue != null;
        Benchmark bm = new Benchmark();
        bm.start();
        Runnable job = runQueue.take();
        bm.stop();
        assert job != null;

        // todo: log that the job was dequeued, printing out the job, wait time
        log.log(Level.INFO, "Job was dequeued "+ job + " waiting time: "+ bm.toString());
        // of
        // todo: job in the queue, wait time of calling take()
        return job;
    }

    public int getRunQueueLength() {
        assert runQueue != null;
        return runQueue.size();
    }

    public void shutdown() {
        running = false;
        // todo: log that we are going to interrupt the group
        group.interrupt();
        // todo: log that we have interrupted the group
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
            log.log(Level.INFO, "Worker "+ name+ "has been started");
            // todo: log that the worker has been started
        }



        public void run() {
            while (running && !isInterrupted()) {
                try {
                    // todo: log that the worker (with name) is waiting for task
                    log.log(Level.INFO, "worker "+ this.getName()+ " has been waiting for task");
                    Runnable job = take();
                    log.log(Level.INFO, "job "+ this.getName()+ " is about to be executed");
                    // todo: log that job is about to be executed
                    Benchmark bm = new Benchmark();
                    bm.start();
                    job.run();
                    bm.stop();
                    log.log(Level.INFO, "job was completed successfully "+ bm.toString() );
                    // todo: log that the job was completed successfully and its
                    // time
                } catch (InterruptedException e) {
                    interrupt();
                    break;
                }
            }
            assert !running || isInterrupted();
            log.log(Level.INFO, "worker "+ this.getName() + " was shut down!");
            // todo: log that the worker (with name) was shut down
        }
    }
}
