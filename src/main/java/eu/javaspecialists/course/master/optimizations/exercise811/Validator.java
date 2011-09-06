package eu.javaspecialists.course.master.optimizations.exercise811;

/**
 * The following sets of specifications define a function check
 * on string data input. The function must check that a given
 * string is
 * <p/>
 * an integer
 * <p/>
 * not the empty string or null
 * <p/>
 * an integer greater than 10
 * <p/>
 * an integer between 2 and 100000 inclusive
 * <p/>
 * That the first digit is 3.
 */
public interface Validator {
    public boolean checkInteger(String testInteger);
}