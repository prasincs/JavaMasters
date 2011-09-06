package eu.javaspecialists.course.master.threads.exercise211;

public class ThreadPool {
    // Create a thread group field
    // Create a Lock field (ReentrantLock)
    // Create a Condition field
    // Create a LinkedList field containing Runnable

    public ThreadPool(int poolSize) {
        // create several Worker threads in the thread group
    }

    private Runnable take() throws InterruptedException {
        // lock the lock.
        // if the LinkedList is empty, we await on the condition
        //
        // remove the first job from the LinkedList and return it
        throw new UnsupportedOperationException("not implemented");
    }

    public void submit(Runnable job) {
        // lock the lock.
        // Add the job to the LinkedList and signal the condition
    }

    public int getRunQueueLength() {
        // return the length of the LinkedList
        // remember to also lock the lock!
        throw new UnsupportedOperationException("not implemented");
    }

    public void shutdown() {
        // this should stop the ThreadGroup
    }

    private class Worker extends Thread {
        public Worker(ThreadGroup group, String name) {
            super(group, name);
        }

        public void run() {
            // we run in an infinite loop:
            // remove the next job from the linked list using take()
            // we then call the run() method on the job
        }
    }
}