package eu.javaspecialists.course.master.optimizations.exercise831;

import eu.javaspecialists.course.master.optimizations.exercise811.*;

public class FastValidatorImpl implements Validator {
    public boolean checkInteger(String testInteger) {
        /*
        * The function must check that a given string is
        *
        * an integer
        * not the empty string or null
        * an integer greater than 10
        * an integer between 2 and 100000 inclusive
        * That the first digit is 3.
        */
        if (testInteger == null) return false;
        if (testInteger.equals("")) return false;
        if (testInteger.charAt(0) != '3' ) return false;
        if (testInteger.length() > 5) return false;
        try {
            // fails if not a number
            //Integer theInteger = new Integer(testInteger);
            //int theInteger = Integer.parseInt(testInteger);
            //final String str = theInteger.toString();
            int theInteger = Integer.parseInt(testInteger);
            return theInteger > 10;
            //return ((theInteger > 10) && (theInteger <= 100000));

//            return (!theInteger.toString().equals("")) && // not empty
//                    (theInteger > 10) && // greater than ten
//                    ((theInteger >= 2) && (theInteger <= 100000)) && // 2>=X<=100000
//                    // first digit is 3
//                    (theInteger.toString().charAt(0) == '3');
        } catch (NumberFormatException err) {
            return false;
        }
    }
}
