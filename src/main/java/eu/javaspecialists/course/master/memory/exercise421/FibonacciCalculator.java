package eu.javaspecialists.course.master.memory.exercise421;

import java.math.*;

public class FibonacciCalculator {
    public String f(int n) {
        if (n < 0)
            throw new IllegalArgumentException("" + n);
        if (n <= 1)
            return "" + n;
        BigInteger b1 = new BigInteger(f(n - 1));
        BigInteger b2 = new BigInteger(f(n - 2));
        return b1.add(b2).toString();
    }
}