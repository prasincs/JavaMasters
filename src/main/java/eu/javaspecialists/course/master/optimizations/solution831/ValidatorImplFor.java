package eu.javaspecialists.course.master.optimizations.solution831;

import eu.javaspecialists.course.master.optimizations.exercise811.*;

// Heinz's preferred solution.  Not the fastest, but clear
public class ValidatorImplFor implements Validator {
    public final boolean checkInteger(String testInteger) {
        if (testInteger == null)
            return false;
        int length = testInteger.length();
        if (length < 2)
            return false;
        if (testInteger.charAt(0) != '3')
            return false;
        if (length > 5)
            return false;

        for (int i = 1; i < length; i++) {
            char c = testInteger.charAt(i);
            if (c < '0' || c > '9')
                return false;
        }
        return true;
    }
}
