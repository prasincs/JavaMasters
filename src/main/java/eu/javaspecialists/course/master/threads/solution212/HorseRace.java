package eu.javaspecialists.course.master.threads.solution212;

public class HorseRace {
    public static final int LOOP_SIZE = 1000;

    public static void main(String... args) throws InterruptedException {
        Runnable race = new Runnable() {
            public void run() {
                try {
                    synchronized (HorseRace.class) {
                        HorseRace.class.wait();
                    }
                } catch (InterruptedException e) {
                    // won't happen here ...
                }
                System.out.println("STARTED: " + Thread.currentThread());
                for (int i = 0; i < LOOP_SIZE; i++) {
                    for (int j = 0; j < LOOP_SIZE; j++) {
                        for (int k = 0; k < LOOP_SIZE; k++) {
                        }
                    }
                }
                System.out.println("FINISHED: " + Thread.currentThread());
            }
        };
        for (int priority = Thread.MIN_PRIORITY; priority <= Thread.MAX_PRIORITY; priority++) {
            Thread thread = new Thread(race, "horse " + priority);
            thread.setPriority(priority);
            thread.start();
        }
        System.out.println("on your marks ...");
        Thread.sleep(1000);
        System.out.println("get set ...");
        Thread.sleep(1000);
        System.out.println("GO!!!");
        synchronized (HorseRace.class) {
            HorseRace.class.notifyAll();
        }
    }
}