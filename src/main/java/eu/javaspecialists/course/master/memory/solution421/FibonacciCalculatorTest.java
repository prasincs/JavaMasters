package eu.javaspecialists.course.master.memory.solution421;

import junit.framework.*;

public class FibonacciCalculatorTest extends TestCase {
    private final FibonacciCalculator fc = new FibonacciCalculator();

    public void test5() {
        assertEquals("5", fc.f(5));
    }

    public void test20() {
        assertEquals("6765", fc.f(20));
    }

    public void test40() {
        assertEquals("102334155", fc.f(40));
    }

    public void test100() {
        assertEquals("354224848179261915075", fc.f(100));
    }

    public void test120() {
        assertEquals("5358359254990966640871840", fc.f(120));
    }
}