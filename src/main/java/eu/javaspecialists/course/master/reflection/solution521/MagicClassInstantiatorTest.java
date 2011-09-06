package eu.javaspecialists.course.master.reflection.solution521;

public class MagicClassInstantiatorTest {
    public static void main(String[] args) throws Exception {
        MagicClassInstantiator
                .main(
                        "eu.javaspecialists.course.master.reflection.exercise521.MagicTestClass",
                        "howdie", "print");
        MagicClassInstantiator
                .main(
                        "eu.javaspecialists.course.master.reflection.exercise521.MagicTestClass",
                        "howdie", "there", "print");
        MagicClassInstantiator
                .main(
                        "eu.javaspecialists.course.master.reflection.exercise521.MagicTestClass",
                        "howdie", "there", "partner", "print");
        MagicClassInstantiator.main("java.lang.String", "heinz Kabutz",
                "toUpperCase");
        MagicClassInstantiator.main("java.util.ArrayList", "size");
        MagicClassInstantiator.main("java.util.ArrayList", "clear");
    }
}
