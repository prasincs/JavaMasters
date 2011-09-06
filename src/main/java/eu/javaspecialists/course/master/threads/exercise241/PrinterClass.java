package eu.javaspecialists.course.master.threads.exercise241;

public class PrinterClass {
    private static final boolean OUTPUT_TO_SCREEN = false;
    private boolean printingEnabled = OUTPUT_TO_SCREEN;

    private synchronized static void print(PrinterClass pc, String s) {
        if (pc.isPrintingEnabled()) {
            System.out.println("Printing: " + s);
        }
    }

    public void print(String s) {
        print(this, s);
    }

    public synchronized boolean isPrintingEnabled() {
        return printingEnabled;
    }

    public synchronized void setPrintingEnabled(boolean printingEnabled) {
        if (!printingEnabled) {
            print(this, "Printing turned off!");
        }
        this.printingEnabled = printingEnabled;
    }
}