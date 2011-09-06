package eu.javaspecialists.course.master.reflection.solution521;

import java.lang.reflect.*;
import java.util.*;

public class MagicClassInstantiator {
    public static void main(String... args) throws Exception {
        System.out.println("Invoked with arguments " + Arrays.toString(args));

        if (args.length < 2) {
            System.err.println("Usage: MagicClassInstantiator className "
                    + "[param1 param2 param3 ...] methodName");
            return;
        }

        Class<?> clazz = Class.forName(args[0]);
        Object[] parameters = new Object[args.length - 2];
        System.arraycopy(args, 1, parameters, 0, args.length - 2);
        Class<?>[] parameterTypes = new Class[parameters.length];
        for (int i = 0; i < parameterTypes.length; i++) {
            parameterTypes[i] = String.class;
        }
        Constructor<?> constr = clazz.getDeclaredConstructor(parameterTypes);
        constr.setAccessible(true);
        Object o = constr.newInstance(parameters);
        Method method = clazz.getDeclaredMethod(args[args.length - 1]);
        method.setAccessible(true);
        Object result = method.invoke(o);
        if (method.getReturnType() != void.class) {
            System.out.println(result);
        }
    }
}
