package eu.javaspecialists.course.master.reflection.exercise521;

public class MagicTestClass {
    private final String s1;
    private final String s2;
    private final String s3;

    private MagicTestClass(String s1) {
        this(s1, null);
    }

    public MagicTestClass(String s1, String s2) {
        this(s1, s2, null);
    }

    protected MagicTestClass(String s1, String s2, String s3) {
        this.s1 = s1;
        this.s2 = s2;
        this.s3 = s3;
    }

    public void print() {
        if (s1 != null)
            System.out.println(s1);
        if (s2 != null)
            System.out.println(s2);
        if (s3 != null)
            System.out.println(s3);
    }
}
