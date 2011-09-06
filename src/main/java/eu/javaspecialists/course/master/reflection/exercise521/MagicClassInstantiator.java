package eu.javaspecialists.course.master.reflection.exercise521;

import java.util.*;

public class MagicClassInstantiator {
    public static void main(String... args) throws Exception {
        System.out.println("Invoked with arguments " + Arrays.toString(args));

        if (args.length < 2) {
            System.err.println("Usage: MagicClassInstantiator className "
                    + "[param1 param2 param3 ...] methodName");
            return;
        }

        // use the first argument to load the class with forName
        // Find correct constructor by parameter count and type
        // Instantiate the object, passing in the parameters
        // Invoke the method
    }
}
