package eu.javaspecialists.course.master.threads.exercise222;

import java.util.Timer;
import java.util.TimerTask;

public class BankAccountTest {

    public static void main(String... args) {
        // create a BankAccount instance
        // create a Runnable anonymous inner class
        // in the run() method, call repeatedly:
        // account.deposit(100);
        // account.withdraw(100);
        // create two thread instances, both pointing at
        // your Runnable
        // create a timer task to once a second prints
        // out the balance
        //
        // Balance should be 1000, 1100 or 1200, nothing else

        final BankAccount account = new BankAccount(1000);

        Runnable husbandWife = new Runnable() {
            public void run() {
                while(true){
                    account.deposit(100);
                    account.withdraw(100);

                }
            }
        };

        Thread t = new Thread(husbandWife);
        Thread t2 = new Thread(husbandWife);
        t.start();
        t2.start();

        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println(account.getBalance());

            }
        }, 1000, 1000);


    }
}
