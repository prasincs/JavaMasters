package eu.javaspecialists.course.master.threads.solution242;

/*
  My first step was to delete all the commented out code and
  unused variables.

  The problem is race condition in the ProcessTaskManager.  The
  map field is overwritten by every thread with their own
  values.

  By changing that to a local variable, we can solve the problem.

  It wasn't the ThreadLocal that was null, but the race condition
  was in setting up the HashMap.  Thus the HashMap contained
  other values than expected.
 */

public class ProcessTaskCaller {
    public static void main(String[] args) {
        ProcessTaskManager ptm = new ProcessTaskManager();
        for (int i = 0; i < 2000; i++) {
            Thread t = new Thread(ptm);
            t.setName(String.valueOf(i));
            t.start();
        }
    }
}
