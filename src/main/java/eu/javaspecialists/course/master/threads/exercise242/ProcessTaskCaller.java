package eu.javaspecialists.course.master.threads.exercise242;

/*
  Here is the email that I received from Bala.  Verify that it is
  also a problem in your version of Java.  Then find out why the
  ThreadLocals are still set to null and propose a solution.

  Hi Heinz,

  I am writing this mail as i am getting null values randomly in
  ThreadLocal get when using HashMap as threadlocal value.

  The code and results are attached and i used Java1.4 and 1.5
  (Sun Java)

  I taught you might have an answer already or you will be
  interested in  finding one.

  I guess i need not explain about the code much as these are
  simple classes and the main class is ProcessTaskCaller

  I am setting a for a threadlocal in a thread and accessing the
  value in a different class (I am trying to access additional
  values in the ProcessTaskProxy class that are not passed via
  method)

  Thanks and Regards
  Bala
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
