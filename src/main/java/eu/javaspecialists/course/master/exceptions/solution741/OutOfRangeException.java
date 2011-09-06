package eu.javaspecialists.course.master.exceptions.solution741;

public class OutOfRangeException extends IllegalArgumentException {
    private final long lower, upper, value;

    public OutOfRangeException(long value, long lower, long upper) {
        super("Out of range: " + value + ", valid range " + "is [" + lower + ","
                + upper + "]");
        this.value = value;
        this.lower = lower;
        this.upper = upper;
    }

    public OutOfRangeException(long value, long lower, long upper, Throwable cause) {
        this(value, lower, upper);
    }

    public long getLower() {
        return lower;
    }

    public long getUpper() {
        return upper;
    }

    public long getValue() {
        return value;
    }
}
