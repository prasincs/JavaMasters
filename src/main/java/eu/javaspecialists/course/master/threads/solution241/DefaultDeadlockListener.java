package eu.javaspecialists.course.master.threads.solution241;

public class DefaultDeadlockListener implements ThreadDeadlockDetector.Listener {
    public void deadlockDetected(Thread[] threads) {
        System.err.println("Deadlocked Threads:");
        System.err.println("-------------------");
        for (Thread thread : threads) {
            System.err.println(thread);
            for (StackTraceElement ste : thread.getStackTrace()) {
                System.err.println("\t" + ste);
            }
        }
    }
}
