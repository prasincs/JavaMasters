package eu.javaspecialists.course.master.threads.solution241;

import eu.javaspecialists.course.master.threads.exercise241.*;

public class PrinterClassTest {
    public static void main(String[] args) {
        final PrinterClass pc = new PrinterClass();
        new Thread() {
            {
                start();
            }

            public void run() {
                for (int i = 0; i < 10000000; i++) {
                    pc.print("testing");
                }
            }
        };
        new Thread() {
            {
                start();
            }

            public void run() {
                for (int i = 0; i < 10000000; i++) {
                    pc.setPrintingEnabled(false);
                }
            }
        };
    }
}