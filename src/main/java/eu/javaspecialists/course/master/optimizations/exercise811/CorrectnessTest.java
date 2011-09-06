package eu.javaspecialists.course.master.optimizations.exercise811;

public class CorrectnessTest {
    public static void check(Validator[] vals) {
        for (Validator validator : vals) {
            check(validator);
        }
    }

    public static void check(Validator val) {
        assertFalse(val, "");
        assertFalse(val, null);
        assertFalse(val, "0");
        assertFalse(val, "1");
        assertFalse(val, "2");
        assertFalse(val, "3");
        assertFalse(val, "4");
        assertFalse(val, "5");
        assertFalse(val, "6");
        assertFalse(val, "7");
        assertFalse(val, "8");
        assertFalse(val, "9");
        assertFalse(val, "123456");
        assertFalse(val, "40000");
        assertFalse(val, "30A00");
        assertFalse(val, "Hello");
        assertFalse(val, "Hello world");
        for (int i = 30; i <= 39; i++) {
            assertTrue(val, Integer.toString(i));
        }
        for (int i = 300; i <= 399; i++) {
            assertTrue(val, Integer.toString(i));
        }
        for (int i = 3000; i <= 3999; i++) {
            assertTrue(val, Integer.toString(i));
        }
        for (int i = 30000; i <= 39999; i++) {
            assertTrue(val, Integer.toString(i));
        }
    }

    private static void assertFalse(Validator val, String s) {
        if (val.checkInteger(s))
            throw new AssertionError(val.getClass().getSimpleName()
                    + " should not be true with String: \"" + s + "\"");
    }

    private static void assertTrue(Validator val, String s) {
        if (!val.checkInteger(s))
            throw new AssertionError(val.getClass().getSimpleName()
                    + " should not be false with String: \"" + s + "\"");
    }
}
