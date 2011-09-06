package eu.javaspecialists.course.master.threads.exercise213;

public class Greeter  {
  static { new Greeter(); }
  private static void hello() {
    System.out.println("Good morning!");
  }
  private Greeter() {
    Thread t = new Thread() {
      public void run() {
        System.out.println("Let's be friendly...");
        hello();
        System.out.println("that was nice!");
      }
    };
    t.start();
    try { t.join(); } catch (InterruptedException e) { }
  }
  public static void main(String[] args) { }
}