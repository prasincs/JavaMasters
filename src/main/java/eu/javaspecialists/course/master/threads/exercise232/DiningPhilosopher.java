package eu.javaspecialists.course.master.threads.exercise232;

import java.util.concurrent.*;

/**
 * The dining philosopher problem solved with semaphores.  In
 * this problem, each philosopher has two forks, each of which
 * is shared with an adjacent philosopher.  The philosopher is
 * either thinking, hungry or eating.  In order to eat, he has
 * to pick up both forks.
 * <p/>
 * The challenge is to avoid a deadlock.  If every philosopher
 * first picks up the right fork, then they could all sit there
 * holding a single fork since the left fork has been picked up
 * by the person on his left.
 * <p/>
 * This code appears to be correct, but there is one little
 * problem that causes two adjacent philosophers to eat at the
 * same time.  Fix it.  (Easier said than done.  First you need
 * to understand what it does.)
 *
 * @author Marco Tedone, used with permission
 * @version 1.0, 2 May 2009
 */
public class DiningPhilosopher implements Runnable {
    // ------------------- Constants/Static variables

    /**
     * The possible states
     */
    enum STATES {
        THINKING, HUNGRY, EATING
    }

    /**
     * The number of philosophers
     */
    private static final int N = 3;

    /**
     * An array of states to keep track of philosophers' states
     */
    private static final STATES[] states = new STATES[N];

    /**
     * A mutex to enter the critical region
     */
    private static final Semaphore mutex = new Semaphore(1);

    /**
     * An array of semaphores - one for each philosopher
     */
    private static final Semaphore[] s = new Semaphore[N];

    /**
     * The philosopher number
     */
    private int i;

    // ------------------- Instance Variables

    // ------------------- Constructors

    public DiningPhilosopher() {
    }

    /**
     * Constructor which accepts the philosopher number
     *
     * @param i The philosopher number
     */
    public DiningPhilosopher(int i) {
        this.i = i;
    }

    // ------------------- Public/Protected Methods

    public static void main(String[] args) {
        DiningPhilosopher main = new DiningPhilosopher();
        main.triggerPhilosophers();
    }

    /**
     * It initialises the data structures and invites all philosopher for lunch
     */
    public void triggerPhilosophers() {
        // It initialises the array of philosophers' semaphores
        for (int i = 0; i < N; i++) {
            s[i] = new Semaphore(1);
        }

        // It invites all philosophers for lunch
        for (int i = 0; i < N; i++) {
            Thread t = new Thread(new DiningPhilosopher(i));
            t.start();
        }
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                // Think a little while
                this.action(i, "Thinking...", 150000);

                // Mmm...Feeling hungry. See if I can eat
                this.take_forks(i);

                // Yum yum...Spaghetti
                this.action(i, "Eating...", 1000);
                if (states[LEFT(i)] == STATES.EATING ||
                        states[RIGHT(i)] == STATES.EATING) {
                    throw new IllegalStateException();
                }

                // Feel full. Let's release the forks
                this.put_forks(i);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // ------------------- Private/Utility methods

    /**
     * A general action
     *
     * @param i The philosopher number
     */
    private void action(int i, String action, long delay) {
        System.out.println("Philosopher: " + i + "\t\t" + action);
        int z = 0;
        for (int j = 0; j < delay; j++) {
            Math.cos(Math.random());
        }
    }

    /**
     * It attempts to take both forks for an hungry philosopher
     *
     * @param i The philosopher number
     * @throws InterruptedException
     */
    private void take_forks(int i) throws InterruptedException {
        // Enters the critical section
        mutex.acquire();
        states[i] = STATES.HUNGRY;
        this.tryToGetForks(i);
        mutex.release();
        // Waits if a philosopher couldn't get both forks
        s[i].acquire();
    }

    /**
     * It releases the forks
     *
     * @param i The philosopher number
     * @throws InterruptedException
     */
    private void put_forks(int i) throws InterruptedException {
        mutex.acquire();
        states[i] = STATES.THINKING;
        System.out.println("Philosopher: " + i + "\t\tReleasing forks...");
        this.tryToGetForks(this.LEFT(i));
        this.tryToGetForks(this.RIGHT(i));
        mutex.release();
    }

    /**
     * Attempts to assign a philosopher both forks
     * <p>
     * It's an all or none business
     * </p>
     *
     * @param i The philosopher number
     */
    private void tryToGetForks(int i) {
        if ((states[i] == STATES.HUNGRY)
                && (states[this.LEFT(i)] != STATES.EATING)
                && (states[this.RIGHT(i)] != STATES.EATING)) {
            states[i] = STATES.EATING;
            System.out.println("Philosopher: " + i +
                    "\t\tYay! Got both forks");
            // This is to counter-balance the down(&s[i]) after the
            // invocation to this method
            s[i].release();
        }
    }

    /**
     * It returns the state of the philosopher to the left of the
     * given one
     *
     * @param i The given philosopher who wants to know the state
     *          of his left neighbour
     * @return the state of the philosopher to the left of the
     *         given one
     */
    private int LEFT(int i) {
        return (i + 1 + N) % N;
    }

    /**
     * It returns the state of the philosopher to the right of the given one
     *
     * @param i The given philosopher who wants to know the state of his right
     *          neighbour
     * @return The state of the philosopher to the right of the given one
     */
    private int RIGHT(int i) {
        return (i - 1 + N) % N;
    }

    // ------------------- Inner classes
}
