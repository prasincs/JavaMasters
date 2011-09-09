package eu.javaspecialists.course.master.reflection.exercise521;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.*;

public class MagicClassInstantiator {
    public static void main(String... args) throws Exception {
        System.out.println("Invoked with arguments " + Arrays.toString(args));

        for (Method method: MagicClassInstantiator.class.getMethods()){
            System.out.println(method.getName()+":"+method.isBridge());
        }

        if (args.length < 2) {
            System.err.println("Usage: MagicClassInstantiator className "
                    + "[param1 param2 param3 ...] methodName");
            return;
        }

        // use the first argument to load the class with forName
        // Find correct constructor by parameter count and type
        // Instantiate the object, passing in the parameters
        // Invoke the method
        Class<?> clazz = Class.forName(args[0]);

        Class<?>[] parameterTypes = new Class<?> [args.length-2];

        Arrays.fill(parameterTypes, String.class);

        Constructor<?> ctor = clazz.getDeclaredConstructor(
                parameterTypes
        );

        ctor.setAccessible(true);
        Object[] parameters = Arrays.copyOfRange(args, 1, args.length-1);

        Object o = ctor.newInstance(parameters);

        Class<?> c2 = o.getClass();

        Method method = c2.getDeclaredMethod(args[args.length -1], (Class<?>[]) null);
        Object result = method.invoke(o);
        if (method.getReturnType() != void.class){
            System.out.println("result = "+ result);
        }
        //System.out.println(method.getName());

        //System.out.println(clazz.isInstance(o));

    }
}
