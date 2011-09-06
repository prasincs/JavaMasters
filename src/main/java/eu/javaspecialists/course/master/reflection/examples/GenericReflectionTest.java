package eu.javaspecialists.course.master.reflection.examples;

import java.lang.reflect.*;

public class GenericReflectionTest {
    public static void main(String[] args) throws NoSuchMethodException {
        // public abstract class Enum<E extends Enum<E>> ...
        showTypeParameters(Enum.class.getTypeParameters());
        // <T extends Enum<T>> T valueOf( ... )
        Method valueOf = Enum.class.getMethod("valueOf", Class.class, String.class);
        showTypeParameters(valueOf.getTypeParameters());
    }

    private static void showTypeParameters(TypeVariable<?>[] typeVars) {
        for (TypeVariable<?> typeVar : typeVars) {
            System.out.println(typeVar);
            Type[] bounds = typeVar.getBounds();
            for (Type bound : bounds) {
                System.out.println("\tbound to " + bound);
            }
        }
    }
}
