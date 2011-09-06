package eu.javaspecialists.course.master.io.solution313d;

import java.io.*;

public class ComplexClass {
    private final int i;
    private final long l;
    private final String s;
    private final boolean b;
    private final Double d;
    private final Float f;

    public ComplexClass(int i, long l, String s, boolean b, Double d, float f) {
        this.i = i;
        this.l = l;
        this.s = s;
        this.b = b;
        this.d = d;
        this.f = f;
    }

    public void writeRaw(DataOutput out) throws IOException {
        out.writeInt(i);
        out.writeLong(l);
        out.writeBoolean(s != null);
        if (s != null) {
            out.writeUTF(s);
        }
        out.writeBoolean(b);
        out.writeBoolean(d != null);
        if (d != null)
            out.writeDouble(d);
        out.writeFloat(f);
    }

    public static ComplexClass readRaw(DataInput in) throws IOException,
            ClassNotFoundException {
        int i = in.readInt();
        long l = in.readLong();
        String s = in.readBoolean() ? in.readUTF() : null;
        boolean b = in.readBoolean();
        Double d = in.readBoolean() ? in.readDouble() : null;
        float f = in.readFloat();
        return new ComplexClass(i, l, s, b, d, f);
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        ComplexClass that = (ComplexClass) o;

        if (b != that.b)
            return false;
        if (i != that.i)
            return false;
        if (l != that.l)
            return false;
        if (d != null ? !d.equals(that.d) : that.d != null)
            return false;
        if (f != null ? !f.equals(that.f) : that.f != null)
            return false;
        if (s != null ? !s.equals(that.s) : that.s != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result;
        result = i;
        result = 31 * result + (int) (l ^ (l >>> 32));
        result = 31 * result + (s != null ? s.hashCode() : 0);
        result = 31 * result + (b ? 1 : 0);
        result = 31 * result + (d != null ? d.hashCode() : 0);
        result = 31 * result + (f != null ? f.hashCode() : 0);
        return result;
    }
}