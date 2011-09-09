package eu.javaspecialists.course.master.datastructs.exercise651;

import clojure.lang.PersistentTreeMap;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

public class ThreadPool {
    private final ThreadGroup group = new ThreadGroup("thread pool");
    private final BlockingQueue<PrioritizedJob> runQueue = new PriorityBlockingQueue<PrioritizedJob>();

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
       runQueue.add(new PrioritizedJob(job, priority));

        //throw new UnsupportedOperationException("todo");
    }
    // Probably will starve low priority queues
    private Runnable take() throws InterruptedException {
       PrioritizedJob pj = runQueue.take();
       return pj.job;
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
    
    private static class PrioritizedJob implements Comparable<PrioritizedJob>{
        private static final AtomicLong nextSeq = new AtomicLong(0);
        final long seq;
        Runnable job;
        Priority priority;

        PrioritizedJob(Runnable job, Priority priority) {
            this.job = job;
            this.priority = priority;
            this.seq = nextSeq.getAndIncrement();
        }

        public int compareTo(PrioritizedJob o) {
            int result =  priority.compareTo(o.priority);  //To change body of implemented methods use File | Settings | File Templates.
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