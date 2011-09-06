package eu.javaspecialists.course.master.io.solution313b;

import java.io.*;

public class ComplexClass implements Externalizable {
    private int i;
    private long l;
    private String s;
    private boolean b;
    private Double d;
    private Float f;

    public ComplexClass(int i, long l, String s, boolean b, Double d, float f) {
        this.i = i;
        this.l = l;
        this.s = s;
        this.b = b;
        this.d = d;
        this.f = f;
    }

    public ComplexClass() {
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

    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeInt(i);
        out.writeLong(l);
        out.writeBoolean(s != null);
        if (s != null) {
            out.writeBoolean(s.length() > 0xFFFF);
            if (s.length() > 0xFFFF) {
                out.writeObject(s);
            } else {
                out.writeUTF(s);
            }
        }
        out.writeBoolean(b);
        out.writeBoolean(d != null);
        if (d != null)
            out.writeDouble(d);
        out.writeFloat(f);
    }

    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        i = in.readInt();
        l = in.readLong();
        if (in.readBoolean()) {
            if (in.readBoolean()) {
                s = (String) in.readObject();
            } else {
                s = in.readUTF();
            }
        }
        b = in.readBoolean();
        if (in.readBoolean()) {
            d = in.readDouble();
        }
        f = in.readFloat();
    }

    public String toString() {
        return String.format("ComplexClass\t%d\t%d\t\"%s\"\t%b\t%f\t%f%n",
                i, l, s, b, d, f);
    }
}