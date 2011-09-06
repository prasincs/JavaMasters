package eu.javaspecialists.course.master.threads.solution222;

import java.util.concurrent.atomic.*;

public class BankAccount {
    private final AtomicInteger balance = new AtomicInteger();

    public BankAccount(int initial) {
        balance.set(initial);
    }

    public void deposit(int amount) {
        balance.addAndGet(amount);
    }

    public void withdraw(int amount) {
        deposit(-amount);
    }

    public int getBalance() {
        return balance.get();
    }
}
