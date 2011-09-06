package eu.javaspecialists.course.master.threads.solution222;

import java.util.*;

public class BankAccountTest {
    public static void main(String... args) {
        final BankAccount account = new BankAccount(1000);
        final Runnable depositWithdraw = new Runnable() {
            public void run() {
                while (true) {
                    account.deposit(100);
                    account.withdraw(100);
                }
            }
        };

        Thread thread1 = new Thread(depositWithdraw);
        Thread thread2 = new Thread(depositWithdraw);
        thread1.start();
        thread2.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                System.out.println("account.getBalance() = " + account.getBalance());
            }
        }, 1000, 1000);
    }

}
