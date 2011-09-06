package eu.javaspecialists.course.master.memory.solution421;

import java.math.*;

public class FibonacciCalculator {
    public String f(int n) {
        if (n < 0)
            throw new IllegalArgumentException("n=" + n);
        if (n == 0)
            return "0";
        if (n == 1)
            return "1";
        BigInteger b1 = new BigInteger("0");
        BigInteger b2 = new BigInteger("1");
        for (int i = 1; i < n; i++) {
            BigInteger temp = b2;
            b2 = b1.add(b2);
            b1 = temp;
        }
        return b2.toString();
    }
}