package eu.javaspecialists.course.master.threads.exercise223;

public class CASCounter implements Counter {
    private long count = 0;
    private Thread owner;

    public long getCount() {
        return count;
    }

    public void increment() {
        do {
            while (this.owner != null)
                ; // wait
            this.owner = Thread.currentThread();
            for (int i = 0; i < 6; i++)
                ; // delay
        } while (this.owner != Thread.currentThread());
        this.count++;
        this.owner = null;
    }
}
