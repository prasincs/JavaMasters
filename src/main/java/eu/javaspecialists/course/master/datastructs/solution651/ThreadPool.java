package eu.javaspecialists.course.master.datastructs.solution651;

import java.util.concurrent.*;
import java.util.concurrent.atomic.*;

public class ThreadPool {
    private final ThreadGroup group = new ThreadGroup("thread pool");
    private final BlockingQueue<Job> runQueue = new PriorityBlockingQueue<Job>();
    private volatile boolean running = true;

    public ThreadPool(int poolSize) {
        for (int i = 0; i < poolSize; i++) {
            Worker worker = new Worker(group, "Worker" + i);
            worker.setDaemon(false);
            worker.start();
        }
    }

    public void submit(Runnable job) {
        submit(job, Priority.NORMAL);
    }

    public void submit(Runnable job, Priority priority) {
        runQueue.add(new Job(job, priority));
    }

    private Runnable take() throws InterruptedException {
        return runQueue.take().runnable;
    }

    public int getRunQueueLength() {
        return runQueue.size();
    }

    public void shutdown() {
        running = true;
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

    public enum Priority {
        HIGH, NORMAL, LOW
    }

    private static class Job implements Comparable<Job> {
        private final Runnable runnable;
        private final Priority priority;
        private static final AtomicLong createSequence = new AtomicLong(0);
        private final long seq;

        public Job(Runnable runnable, Priority priority) {
            this.runnable = runnable;
            this.priority = priority;
            this.seq = createSequence.getAndIncrement();
        }

        public int compareTo(Job o) {
            int result = priority.compareTo(o.priority);
            if (result != 0) {
                return result;
            }
            if (seq < o.seq)
                return -1;
            if (seq > o.seq)
                return 1;
            throw new IllegalStateException();
        }
    }
}