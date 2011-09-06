package eu.javaspecialists.course.master.exceptions.exercise741;

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
     * @see #MAXIMUM_POOL_SIZE
     */
    public ThreadPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            ThreadPool.Worker worker = new ThreadPool.Worker(group, "Worker" + i);
            worker.setDaemon(false);
            worker.start();
        }
    }

    /**
     * @param job
     */
    public void submit(Runnable job) {
        runQueue.add(job);
    }

    private Runnable take() throws InterruptedException {
        return runQueue.take();
    }

    public int getRunQueueLength() {
        return runQueue.size();
    }

    public void shutdown() {
        running = false;
        group.interrupt();
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
        }

        public void run() {
            while (running && !isInterrupted()) {
                try {
                    take().run();
                } catch (InterruptedException e) {
                    interrupt();
                    break;
                }
            }
        }
    }
}
